package ru.sinitsynme.furnituretasktracker.furniture.model

data class FurnitureModelRequestDto(
    val name: String
)

data class FurnitureModelResponseDto(
    val modelId: Long,
    val name: String
)