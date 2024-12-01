package org.example.project.domain.repositories

import org.example.project.domain.entities.Medicine
import org.example.project.domain.filters.MedicineFilter

interface MedicineRepository {
    suspend fun getAll(): Result<List<Medicine>>
    suspend fun getById(id: Int): Result<Medicine?>
    suspend fun add(medicine: Medicine): Result<Medicine>
    suspend fun update(medicine: Medicine): Result<Boolean>
    suspend fun delete(id: Int): Result<Boolean>
    suspend fun getMedicines(filter: MedicineFilter): Result<List<Medicine>>
}