package ru.sinitsynme.furnituretasktracker.config

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.reflect.MethodSignature
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.util.concurrent.TimeUnit

@Aspect
@Component
class RestControllerLoggingAspect {

    @Around("@within(org.springframework.web.bind.annotation.RestController)")
    fun logRestControllerMethods(joinPoint: ProceedingJoinPoint): Any? {
        val methodSignature = joinPoint.signature as MethodSignature
        val method = methodSignature.method
        val className = method.declaringClass.simpleName
        val methodName = method.name

        val parameterNames = method.parameters.map { it.name }
        val params = parameterNames.zip(joinPoint.args).joinToString { (name, value) ->
            "$name = ${value?.toString()?.take(MAX_PARAM_LENGTH) ?: "null"}"
        }

        logger.info("Entering $className.$methodName() with params: [$params]")

        val startNs = System.nanoTime()
        try {
            val result = joinPoint.proceed()

            val executionTimeMs = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startNs)
            val resultStr = result?.toString()?.take(MAX_RESULT_LENGTH) ?: "null"
            logger.info("Exiting $className.$methodName() with result: [$resultStr] (execution: ${executionTimeMs}ms)")
            return result
        } catch (ex: Exception) {

            val executionTimeMs = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startNs)
            logger.warn("Exception in $className.$methodName() after ${executionTimeMs}ms: ${ex.message}")
            throw ex
        }
    }

    companion object {
        private val logger = LoggerFactory.getLogger(RestControllerLoggingAspect::class.java)
        private const val MAX_PARAM_LENGTH = 100
        private const val MAX_RESULT_LENGTH = 400
    }
}
