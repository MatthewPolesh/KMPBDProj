package org.example.project.domain.repositories

import org.example.project.domain.entities.Standard

interface StandardRepository {
    suspend fun getAll(): Result<List<Standard>>
    suspend fun getById(id: Int): Result<Standard?>
    suspend fun add(standard: Standard): Result<Standard>
    suspend fun update(standard: Standard): Result<Boolean>
    suspend fun delete(id: Int): Result<Boolean>
}