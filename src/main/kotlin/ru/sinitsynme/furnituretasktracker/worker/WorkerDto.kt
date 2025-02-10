package ru.sinitsynme.furnituretasktracker.worker

import java.util.*

data class WorkerRequestDto(val fullName: String)

data class WorkerResponseDto(
    val workerId: UUID,
    val fullName: String
)
