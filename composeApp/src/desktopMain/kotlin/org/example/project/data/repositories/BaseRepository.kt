package org.example.project.data.repositories

import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction

abstract class BaseRepository {
    protected suspend fun <T> safeDbCall(action: suspend () -> T): Result<T> {
        return try {
            newSuspendedTransaction { Result.success(action()) }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}