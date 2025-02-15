package ru.sinitsynme.furnituretasktracker.task

import jakarta.persistence.*
import jakarta.persistence.EnumType.STRING
import jakarta.persistence.FetchType.EAGER
import jakarta.persistence.FetchType.LAZY
import jakarta.persistence.GenerationType.AUTO
import ru.sinitsynme.furnituretasktracker.furniture.configuration.FurnitureConfiguration
import ru.sinitsynme.furnituretasktracker.worker.Worker
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.UUID

@Entity(name = "task")
class Task(
    var quantity: Int,
    var finalCost: BigDecimal,
    @Enumerated(STRING)
    var status: TaskStatus,
    var registrationDate: LocalDateTime,
    @ManyToOne(fetch = EAGER) @JoinColumn(name = "config_id", nullable = false)
    var furnitureConfiguration: FurnitureConfiguration,
    @ManyToOne(fetch = EAGER) @JoinColumn(name = "worker_id", nullable = false)
    var worker: Worker,
    @Id
    @GeneratedValue(strategy = AUTO)
    var taskId: UUID? = null,
    var completionDate: LocalDateTime? = null,
    @ManyToOne(fetch = LAZY) @JoinColumn(name = "parent_task_id", nullable = true)
    var parentTask: Task? = null,
)