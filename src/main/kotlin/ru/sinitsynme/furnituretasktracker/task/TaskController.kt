package ru.sinitsynme.furnituretasktracker.task

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus.CREATED
import org.springframework.web.bind.annotation.*
import java.util.UUID

@RestController
@RequestMapping("/api/v1/task")
@Tag(name = "Задачи")
class TaskController(
    private val taskService: TaskService,
) {

    @GetMapping("/{id}")
    @Operation(summary = "Получить по ID")
    fun getTaskById(@PathVariable id: UUID): TaskResponseDto {
        return taskService.findById(id).toResponse()
    }

    @GetMapping
    @Operation(summary = "Получить")
    fun getTasks(pageable: Pageable): Page<TaskResponseDto> {
        return taskService.findPageable(pageable).map { it.toResponse() }
    }

    @PostMapping
    @ResponseStatus(CREATED)
    @Operation(summary = "Создать")
    fun createTask(@RequestBody @Valid taskRequestDto: TaskRequestDto): TaskResponseDto {
        return taskService.createTask(taskRequestDto).toResponse()
    }

    @PutMapping("/{id}")
    @Operation(summary = "Обновить")
    fun updateTask(@PathVariable id: UUID, @RequestBody @Valid taskRequestDto: TaskRequestDto): TaskResponseDto {
        return taskService.updateTask(taskRequestDto, id).toResponse()
    }

    @PatchMapping("/{id}/status")
    @Operation(summary = "Сменить статус")
    fun updateTaskStatus(
        @PathVariable id: UUID,
        @RequestBody @Valid taskUpdateStatusRequestDto: TaskUpdateStatusRequestDto
    ): TaskResponseDto {
        return taskService.updateTaskStatus(taskUpdateStatusRequestDto, id).toResponse()
    }

    @PostMapping("/child")
    @ResponseStatus(CREATED)
    @Operation(summary = "Создать дочернюю задачу-копию")
    fun createChildTask(
        @RequestBody @Valid taskCreateChildTaskRequestDto: TaskCreateChildTaskRequestDto,
    ): TaskResponseDto {
        return taskService.createChildTask(taskCreateChildTaskRequestDto).toResponse()
    }

    fun getReport() {
        //TODO
    }
}