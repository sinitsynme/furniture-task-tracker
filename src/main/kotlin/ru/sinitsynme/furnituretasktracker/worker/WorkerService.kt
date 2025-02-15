package ru.sinitsynme.furnituretasktracker.worker

import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import ru.sinitsynme.furnituretasktracker.exception.EntityNotFoundException
import java.util.*

@Service
class WorkerService(
    private val workerRepository: WorkerRepository,
) {
    fun create(request: WorkerRequestDto): Worker {
        val worker = Worker(fullName = request.fullName)
        return workerRepository.save(worker)
    }

    fun findAll(): List<Worker> = workerRepository.findAll()

    fun findById(id: UUID): Worker =
        workerRepository.findById(id)
            .orElseThrow { EntityNotFoundException("Worker not found with id: $id") }

    @Transactional
    fun update(id: UUID, request: WorkerRequestDto): Worker {
        val worker = workerRepository.findById(id)
            .orElseThrow { EntityNotFoundException("Worker not found with id: $id") }
        worker.fullName = request.fullName
        return workerRepository.save(worker)
    }
}