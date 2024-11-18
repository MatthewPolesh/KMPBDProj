package org.example.project.domain.repositories

import org.example.project.domain.entities.GOSTMedicine

interface GOSTMedicineRepository {
    suspend fun getAll(): Result<List<GOSTMedicine>>
    suspend fun getById(id: Int): Result<GOSTMedicine?>
    suspend fun add(gostMedicine: GOSTMedicine): Result<GOSTMedicine>
    suspend fun update(gostMedicine: GOSTMedicine): Result<Boolean>
    suspend fun delete(id: Int): Result<Boolean>
}