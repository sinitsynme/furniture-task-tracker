package ru.sinitsynme.furnituretasktracker.worker

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface WorkerRepository : JpaRepository<Worker, UUID>