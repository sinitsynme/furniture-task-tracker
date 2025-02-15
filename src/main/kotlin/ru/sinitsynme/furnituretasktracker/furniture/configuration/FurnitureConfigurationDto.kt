package ru.sinitsynme.furnituretasktracker.furniture.configuration

import ru.sinitsynme.furnituretasktracker.furniture.model.FurnitureModelResponseDto
import ru.sinitsynme.furnituretasktracker.furniture.model.toResponse
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

fun FurnitureConfiguration.toResponse(): FurnitureConfigurationResponseDto = FurnitureConfigurationResponseDto(
    configId = configId!!,
    name = name,
    price = price,
    model = model.toResponse()
)