package org.example.project.data.repositories

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction

abstract class BaseRepository {
    protected suspend fun <T> safeDbCall(action: suspend () -> T): Result<T> = withContext(Dispatchers.IO) {
        return@withContext try {
            newSuspendedTransaction { Result.success(action()) }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}