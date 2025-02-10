package ru.sinitsynme.furnituretasktracker.worker

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType.AUTO
import jakarta.persistence.Id
import java.util.*

@Entity(name = "worker")
class Worker(
    var fullName: String,
    @Id
    @GeneratedValue(strategy = AUTO)
    var workerId: UUID? = null,
)