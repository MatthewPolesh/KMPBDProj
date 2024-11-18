package org.example.project.domain.repositories

import org.example.project.domain.entities.MedicinalForm


interface MedicinalFormRepository {
    suspend fun getAll(): Result<List<MedicinalForm>>
    suspend fun getById(id: Int): Result<MedicinalForm?>
    suspend fun add(medicinalForm: MedicinalForm): Result<MedicinalForm>
    suspend fun update(medicinalForm: MedicinalForm): Result<Boolean>
    suspend fun delete(id: Int): Result<Boolean>
}