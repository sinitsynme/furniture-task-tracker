package ru.sinitsynme.furnituretasktracker.task

import org.springframework.stereotype.Component
import ru.sinitsynme.furnituretasktracker.task.TaskStatus.DONE
import java.time.Clock
import java.time.LocalDateTime

@Component
class TaskStatusProcessor(
    private val clock: Clock
) {
    fun processStatusChange(task: Task, status: TaskStatus): Task {
        task.status = status
        if (status == DONE) {
            task.completionDate = LocalDateTime.now(clock)
        }
        return task
    }
}