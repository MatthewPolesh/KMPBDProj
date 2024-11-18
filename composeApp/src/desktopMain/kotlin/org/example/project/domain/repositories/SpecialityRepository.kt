package org.example.project.domain.repositories

import org.example.project.domain.entities.Speciality

interface SpecialityRepository {
    suspend fun getAll(): Result<List<Speciality>>
    suspend fun getById(id: Int): Result<Speciality?>
    suspend fun add(speciality: Speciality): Result<Speciality>
    suspend fun update(speciality: Speciality): Result<Boolean>
    suspend fun delete(id: Int): Result<Boolean>
}