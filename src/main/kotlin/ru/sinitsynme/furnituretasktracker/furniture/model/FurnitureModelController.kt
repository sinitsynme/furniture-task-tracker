package ru.sinitsynme.furnituretasktracker.furniture.model

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.HttpStatus.NO_CONTENT
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/furniture-model")
@Tag(name = "Модели")
class FurnitureModelController(
    private val modelService: FurnitureModelService,
) {
    @PostMapping
    @ResponseStatus(CREATED)
    @Operation(summary = "Создать")
    fun createModel(@RequestBody request: FurnitureModelRequest): FurnitureModelResponse =
        modelService.create(request).toResponse()

    @GetMapping
    @Operation(summary = "Получить все")
    fun getAllModels(): List<FurnitureModelResponse> = modelService.findAll().map { it.toResponse() }

    @GetMapping("/{id}")
    @Operation(summary = "Получить")
    fun getModel(@PathVariable id: Long): FurnitureModelResponse = modelService.findById(id).toResponse()

    @PutMapping("/{id}")
    @Operation(summary = "Обновить")
    fun updateModel(
        @PathVariable id: Long,
        @RequestBody request: FurnitureModelRequest
    ): FurnitureModelResponse = modelService.update(id, request).toResponse()

    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить")
    @ResponseStatus(NO_CONTENT)
    fun deleteModel(@PathVariable id: Long) = modelService.delete(id)
}

fun FurnitureModel.toResponse() = FurnitureModelResponse(
    modelId = modelId!!,
    name = name
)
