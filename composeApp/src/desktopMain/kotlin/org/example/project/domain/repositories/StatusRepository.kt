package org.example.project.domain.repositories

import org.example.project.domain.entities.Status

interface StatusRepository {
    suspend fun getAll(): Result<List<Status>>
    suspend fun getById(id: Int): Result<Status?>
    suspend fun add(status: Status): Result<Status>
    suspend fun update(status: Status): Result<Boolean>
    suspend fun delete(id: Int): Result<Boolean>
}