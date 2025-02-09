package ru.sinitsynme.furnituretasktracker.furniture.model

import jakarta.persistence.*
import jakarta.persistence.FetchType.LAZY
import jakarta.persistence.GenerationType.SEQUENCE
import ru.sinitsynme.furnituretasktracker.furniture.configuration.FurnitureConfiguration

@Entity(name = "furniture_model")
class FurnitureModel(
    var name: String,
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "furniture_model_pk_seq")
    @SequenceGenerator(name = "furniture_model_pk_seq", initialValue = 1, allocationSize = 1)
    var modelId: Long? = null,
    @OneToMany(mappedBy = "model", fetch = LAZY, targetEntity = FurnitureConfiguration::class)
    var configurations: List<FurnitureConfiguration>? = mutableListOf()
)