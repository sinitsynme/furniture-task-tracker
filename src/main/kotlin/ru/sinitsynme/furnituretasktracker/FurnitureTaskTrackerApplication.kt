package ru.sinitsynme.furnituretasktracker

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class FurnitureTaskTrackerApplication

fun main(args: Array<String>) {
    runApplication<FurnitureTaskTrackerApplication>(*args)
}