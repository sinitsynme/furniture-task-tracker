package ru.sinitsynme.furnituretasktracker.furniture.model

import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import ru.sinitsynme.furnituretasktracker.exception.EntityNotFoundException
import ru.sinitsynme.furnituretasktracker.exception.IllegalActionException

@Service
class FurnitureModelService(
    private val modelRepository: FurnitureModelRepository,
) {

    fun create(request: FurnitureModelRequestDto): FurnitureModel {
        val newModel = FurnitureModel(name = request.name)
        return modelRepository.save(newModel)
    }

    fun findAll(): List<FurnitureModel> =
        modelRepository.findAll()

    fun findById(id: Long): FurnitureModel =
        modelRepository.findById(id)
            .orElseThrow { EntityNotFoundException("Model not found with id: $id") }

    @Transactional
    fun update(id: Long, request: FurnitureModelRequestDto): FurnitureModel {
        val existingModel = modelRepository.findById(id)
            .orElseThrow { EntityNotFoundException("Model not found with id: $id") }
        existingModel.name = request.name
        return modelRepository.save(existingModel)
    }

    @Transactional
    fun delete(id: Long) {
        val model = modelRepository.findById(id)
            .orElseThrow { EntityNotFoundException("Model not found with id: $id") }

        if (model.configurations.orEmpty().isNotEmpty()) throw IllegalActionException(
            "Model with id $id cannot be deleted, it has present configurations"
        )
        modelRepository.delete(model)
    }
}