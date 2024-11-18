package org.example.project.domain.repositories

import org.example.project.domain.entities.MedicinalFormReport

interface MedicinalFormReportRepository {
    suspend fun getAll(): Result<List<MedicinalFormReport>>
    suspend fun getById(id: Int): Result<MedicinalFormReport?>
    suspend fun add(medicinalFormReport: MedicinalFormReport): Result<MedicinalFormReport>
    suspend fun update(medicinalFormReport: MedicinalFormReport): Result<Boolean>
    suspend fun delete(id: Int): Result<Boolean>
}