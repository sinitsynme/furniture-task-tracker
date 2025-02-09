package ru.sinitsynme.furnituretasktracker.furniture.configuration

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface FurnitureConfigurationRepository : JpaRepository<FurnitureConfiguration, Long>