package ru.sinitsynme.furnituretasktracker.worker

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.HttpStatus.CREATED
import org.springframework.web.bind.annotation.*
import java.util.UUID

@RestController
@RequestMapping("/api/v1/worker")
@Tag(name = "Работники")
class WorkerController(
    private val workerService: WorkerService,
) {
    @PostMapping
    @ResponseStatus(CREATED)
    @Operation(summary = "Создать")
    fun createWorker(@Valid @RequestBody request: WorkerRequestDto): WorkerResponseDto =
        workerService.create(request).toResponse()

    @GetMapping
    @Operation(summary = "Получить всех")
    fun getAllWorkers(): List<WorkerResponseDto> = workerService.findAll().map { it.toResponse() }

    @GetMapping("/{id}")
    @Operation(summary = "Получить")
    fun getWorker(@PathVariable id: UUID): WorkerResponseDto = workerService.findById(id).toResponse()

    @PutMapping("/{id}")
    @Operation(summary = "Обновить")
    fun updateWorker(@PathVariable id: UUID, @Valid @RequestBody request: WorkerRequestDto): WorkerResponseDto =
        workerService.update(id, request).toResponse()
}

