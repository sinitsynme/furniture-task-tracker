package ru.sinitsynme.furnituretasktracker.furniture.configuration

import ru.sinitsynme.furnituretasktracker.furniture.model.FurnitureModelResponse
import java.math.BigDecimal

data class FurnitureConfigurationRequest(
    val name: String,
    val price: BigDecimal,
    val modelId: Long,
)

data class FurnitureConfigurationResponse(
    val configId: Long,
    val name: String,
    val price: BigDecimal,
    val model: FurnitureModelResponse,
)