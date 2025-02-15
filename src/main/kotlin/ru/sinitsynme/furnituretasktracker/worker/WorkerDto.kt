package ru.sinitsynme.furnituretasktracker.worker

import java.util.UUID

data class WorkerRequestDto(val fullName: String)

data class WorkerResponseDto(
    val workerId: UUID,
    val fullName: String
)

fun Worker.toResponse() = WorkerResponseDto(
    workerId = workerId!!,
    fullName = fullName
)
