package ru.sinitsynme.furnituretasktracker.furniture.configuration

import org.springframework.stereotype.Service
import ru.sinitsynme.furnituretasktracker.exception.EntityNotFoundException
import ru.sinitsynme.furnituretasktracker.furniture.model.FurnitureModelService

@Service
class FurnitureConfigurationService(
    private val configRepository: FurnitureConfigurationRepository,
    private val modelService: FurnitureModelService,
) {

    fun create(request: FurnitureConfigurationRequest): FurnitureConfiguration {
        val model = modelService.findById(request.modelId)

        val newConfig = FurnitureConfiguration(
            name = request.name,
            price = request.price,
            model = model
        )

        return configRepository.save(newConfig)
    }

    fun findAll(): List<FurnitureConfiguration> =
        configRepository.findAll().map { it }

    fun findById(id: Long): FurnitureConfiguration =
        configRepository.findById(id)
            .orElseThrow { EntityNotFoundException("FurnitureConfiguration not found with id: $id") }


    fun update(id: Long, request: FurnitureConfigurationRequest): FurnitureConfiguration {
        val existingConfig = configRepository.findById(id)
            .orElseThrow { EntityNotFoundException("FurnitureConfiguration not found with id: $id") }

        val modelFromDb = modelService.findById(request.modelId)

        existingConfig.apply {
            name = request.name
            price = request.price
            model = modelFromDb
        }

        return configRepository.save(existingConfig)
    }

    fun delete(id: Long) {
        if (!configRepository.existsById(id)) {
            throw EntityNotFoundException("FurnitureConfiguration not found with id: $id")
        }
        configRepository.deleteById(id)
    }
}