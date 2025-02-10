package ru.sinitsynme.furnituretasktracker.furniture.configuration

import ru.sinitsynme.furnituretasktracker.furniture.model.FurnitureModelResponseDto
import java.math.BigDecimal

data class FurnitureConfigurationRequestDto(
    val name: String,
    val price: BigDecimal,
    val modelId: Long,
)

data class FurnitureConfigurationResponseDto(
    val configId: Long,
    val name: String,
    val price: BigDecimal,
    val model: FurnitureModelResponseDto,
)

data class FurnitureConfigurationUpdatePriceRequestDto(
    val price: BigDecimal,
)