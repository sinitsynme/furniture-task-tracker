package ru.sinitsynme.furnituretasktracker.worker

import jakarta.validation.Valid
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.HttpStatus.NO_CONTENT
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/v1/worker")
class WorkerController(
    private val workerService: WorkerService,
) {
    @PostMapping
    @ResponseStatus(CREATED)
    fun createWorker(@Valid @RequestBody request: WorkerRequestDto): WorkerResponseDto =
        workerService.create(request).toResponse()

    @GetMapping
    fun getAllWorkers(): List<WorkerResponseDto> = workerService.findAll().map { it.toResponse() }

    @GetMapping("/{id}")
    fun getWorker(@PathVariable id: UUID): WorkerResponseDto = workerService.findById(id).toResponse()

    @PutMapping("/{id}")
    fun updateWorker(@PathVariable id: UUID, @Valid @RequestBody request: WorkerRequestDto): WorkerResponseDto =
        workerService.update(id, request).toResponse()

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    fun deleteWorker(@PathVariable id: UUID) = workerService.delete(id)
}

fun Worker.toResponse() = WorkerResponseDto(
    workerId = workerId!!,
    fullName = fullName
)