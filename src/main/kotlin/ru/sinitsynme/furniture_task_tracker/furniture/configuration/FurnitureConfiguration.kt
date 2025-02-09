package ru.sinitsynme.furniture_task_tracker.furniture.configuration

import jakarta.persistence.*
import ru.sinitsynme.furniture_task_tracker.furniture.model.FurnitureModel
import java.math.BigDecimal

@Entity(name = "furniture_config")
class FurnitureConfiguration(
    var name: String,
    var price: BigDecimal,
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "furniture_config_pk_seq")
    @SequenceGenerator(name = "furniture_config_pk_seq", initialValue = 1, allocationSize = 1)
    var configId: Long,
    @ManyToOne @JoinColumn(name = "model_id") var model: FurnitureModel,
)