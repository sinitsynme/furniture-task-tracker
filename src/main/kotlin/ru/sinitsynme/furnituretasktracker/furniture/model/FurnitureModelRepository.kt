package ru.sinitsynme.furnituretasktracker.furniture.model

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface FurnitureModelRepository : JpaRepository<FurnitureModel, Long>