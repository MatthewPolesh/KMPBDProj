package org.example.project.domain.repositories

import org.example.project.domain.entities.GOST

interface GOSTRepository {
    suspend fun getAll(): Result<List<GOST>>
    suspend fun getById(id: Int): Result<GOST?>
    suspend fun add(gost: GOST): Result<GOST>
    suspend fun update(gost: GOST): Result<Boolean>
    suspend fun delete(id: Int): Result<Boolean>
}