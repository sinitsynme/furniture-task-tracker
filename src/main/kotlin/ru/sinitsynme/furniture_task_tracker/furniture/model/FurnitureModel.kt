package ru.sinitsynme.furniture_task_tracker.furniture.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.SequenceGenerator

@Entity(name = "furniture_model")
class FurnitureModel(
    var name: String,
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "furniture_model_pk_seq")
    @SequenceGenerator(name = "furniture_model_pk_seq", initialValue = 1, allocationSize = 1)
    var modelId: Long?,
)