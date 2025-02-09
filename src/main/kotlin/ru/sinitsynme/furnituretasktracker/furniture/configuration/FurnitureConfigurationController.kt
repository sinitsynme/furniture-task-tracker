package ru.sinitsynme.furnituretasktracker.furniture.configuration

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.HttpStatus.NO_CONTENT
import org.springframework.web.bind.annotation.*
import ru.sinitsynme.furnituretasktracker.furniture.model.toResponse

@RestController
@RequestMapping("/api/v1/furniture-configuration")
@Tag(name = "Конфигурации")
class FurnitureConfigurationController(
    private val configService: FurnitureConfigurationService

) {
    @PostMapping
    @ResponseStatus(CREATED)
    @Operation(summary = "Создать")
    fun createConfiguration(@RequestBody request: FurnitureConfigurationRequest): FurnitureConfigurationResponse =
        configService.create(request).toResponse()

    @GetMapping
    @Operation(summary = "Получить все")
    fun getAllConfigurations(): List<FurnitureConfigurationResponse> =
        configService.findAll().map { it.toResponse() }

    @GetMapping("/{id}")
    @Operation(summary = "Получить")
    fun getConfiguration(@PathVariable id: Long): FurnitureConfigurationResponse =
        configService.findById(id).toResponse()

    @PutMapping("/{id}")
    @Operation(summary = "Обновить")
    fun updateConfiguration(
        @PathVariable id: Long,
        @RequestBody request: FurnitureConfigurationRequest
    ): FurnitureConfigurationResponse = configService.update(id, request).toResponse()

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    @Operation(summary = "Удалить")
    fun deleteConfiguration(@PathVariable id: Long) {
        configService.delete(id)
    }
}


fun FurnitureConfiguration.toResponse() = FurnitureConfigurationResponse(
    configId = configId!!,
    name = name,
    price = price,
    model = model.toResponse()
)