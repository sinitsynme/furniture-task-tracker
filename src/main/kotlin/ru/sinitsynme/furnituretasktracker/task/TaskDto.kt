package ru.sinitsynme.furnituretasktracker.task

import ru.sinitsynme.furnituretasktracker.furniture.configuration.FurnitureConfigurationResponseDto
import ru.sinitsynme.furnituretasktracker.furniture.configuration.toResponse
import ru.sinitsynme.furnituretasktracker.worker.WorkerResponseDto
import ru.sinitsynme.furnituretasktracker.worker.toResponse
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.UUID

data class TaskRequestDto(
    val quantity: Int,
    val furnitureConfigurationId: Long,
    val workerId: UUID,
)

data class TaskResponseDto(
    val taskId: UUID,
    val quantity: Int,
    val finalCost: BigDecimal,
    val status: TaskStatus,
    val registrationDate: LocalDateTime,
    val furnitureConfigurationResponseDto: FurnitureConfigurationResponseDto,
    val worker: WorkerResponseDto,
    val completionDate: LocalDateTime? = null,
    val parentTaskId: UUID? = null,
)

data class TaskUpdateStatusRequestDto(
    val status: TaskStatus,
)

data class TaskCreateChildTaskRequestDto(
    val parentTaskId: UUID,
    val nextWorkerId: UUID,
)

fun Task.toResponse(): TaskResponseDto = TaskResponseDto(
    taskId = taskId!!,
    quantity = quantity,
    finalCost = finalCost,
    status = status,
    registrationDate = registrationDate,
    furnitureConfigurationResponseDto = furnitureConfiguration.toResponse(),
    worker = worker.toResponse(),
    parentTaskId = parentTask?.taskId,
    completionDate = completionDate,
)