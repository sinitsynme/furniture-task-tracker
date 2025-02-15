package ru.sinitsynme.furnituretasktracker.task

import jakarta.transaction.Transactional
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import ru.sinitsynme.furnituretasktracker.exception.EntityNotFoundException
import ru.sinitsynme.furnituretasktracker.furniture.configuration.FurnitureConfigurationService
import ru.sinitsynme.furnituretasktracker.task.TaskStatus.PENDING
import ru.sinitsynme.furnituretasktracker.worker.WorkerService
import java.math.BigDecimal
import java.time.Clock
import java.time.LocalDateTime
import java.util.UUID

@Service
class TaskService(
    private val furnitureConfigurationService: FurnitureConfigurationService,
    private val workerService: WorkerService,
    private val taskRepository: TaskRepository,
    private val taskStatusProcessor: TaskStatusProcessor,
    private val clock: Clock,
) {

    fun findById(id: UUID): Task = taskRepository.findById(id)
        .orElseThrow { EntityNotFoundException("Task not found with id: $id") }

    fun findPageable(pageable: Pageable) = taskRepository.findAll(pageable)

    @Transactional
    fun createTask(taskRequestDto: TaskRequestDto): Task {
        val furnitureConfiguration = furnitureConfigurationService.findById(taskRequestDto.furnitureConfigurationId)
        val worker = workerService.findById(taskRequestDto.workerId)

        val task = Task(
            taskRequestDto.quantity,
            furnitureConfiguration.price * BigDecimal(taskRequestDto.quantity),
            PENDING,
            LocalDateTime.now(clock),
            furnitureConfiguration,
            worker
        )

        return taskRepository.save(task)
    }

    @Transactional
    fun updateTask(taskRequestDto: TaskRequestDto, taskId: UUID): Task {
        val taskFromDb = findById(taskId)
        val furnitureConfiguration = furnitureConfigurationService.findById(taskRequestDto.furnitureConfigurationId)
        val worker = workerService.findById(taskRequestDto.workerId)

        taskFromDb.quantity = taskRequestDto.quantity
        taskFromDb.finalCost = furnitureConfiguration.price * BigDecimal(taskRequestDto.quantity)
        taskFromDb.furnitureConfiguration = furnitureConfiguration
        taskFromDb.worker = worker

        return taskRepository.save(taskFromDb)
    }

    @Transactional
    fun updateTaskStatus(taskUpdateStatusRequestDto: TaskUpdateStatusRequestDto, taskId: UUID): Task {
        val taskFromDb = findById(taskId)
        val taskToBeSaved = taskStatusProcessor.processStatusChange(taskFromDb, taskUpdateStatusRequestDto.status)

        return taskRepository.save(taskToBeSaved)
    }

    @Transactional
    fun createChildTask(taskCreateChildTaskRequestDto: TaskCreateChildTaskRequestDto): Task {
        val parentTask = findById(taskCreateChildTaskRequestDto.parentTaskId)
        val nextWorker = workerService.findById(taskCreateChildTaskRequestDto.nextWorkerId)

        val task = Task(
            parentTask.quantity,
            parentTask.finalCost,
            PENDING,
            LocalDateTime.now(clock),
            parentTask.furnitureConfiguration,
            nextWorker,
            parentTask = parentTask
        )

        return taskRepository.save(task)
    }

    fun generateReport() {
        //TODO
    }
}