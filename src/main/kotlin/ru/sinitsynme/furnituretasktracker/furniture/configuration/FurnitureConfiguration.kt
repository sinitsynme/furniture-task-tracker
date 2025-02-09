package ru.sinitsynme.furnituretasktracker.furniture.configuration

import jakarta.persistence.*
import jakarta.persistence.FetchType.EAGER
import jakarta.persistence.GenerationType.SEQUENCE
import ru.sinitsynme.furnituretasktracker.furniture.model.FurnitureModel
import java.math.BigDecimal

@Entity(name = "furniture_config")
class FurnitureConfiguration(
    var name: String,
    var price: BigDecimal,
    @ManyToOne(fetch = EAGER) @JoinColumn(name = "model_id", nullable = false)
    var model: FurnitureModel,
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "furniture_config_pk_seq")
    @SequenceGenerator(name = "furniture_config_pk_seq", initialValue = 1, allocationSize = 1)
    var configId: Long? = null,
)