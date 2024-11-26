package org.example.project.data.repositories

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.transactions.transaction

abstract class BaseRepository {
    protected suspend fun <T> safeDbCall(action:  () -> T): Result<T> = withContext(Dispatchers.IO) {
        return@withContext try {
            transaction { Result.success(action()) }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}