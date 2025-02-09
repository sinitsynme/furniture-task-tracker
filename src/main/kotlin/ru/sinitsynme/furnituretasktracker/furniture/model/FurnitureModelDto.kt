package ru.sinitsynme.furnituretasktracker.furniture.model

data class FurnitureModelRequest(
    val name: String
)

data class FurnitureModelResponse(
    val modelId: Long,
    val name: String
)