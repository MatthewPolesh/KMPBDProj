package org.example.project.domain.repositories

import org.example.project.domain.entities.ReportMedicine

interface ReportMedicineRepository {
    suspend fun getAll(): Result<List<ReportMedicine>>
    suspend fun getById(id: Int): Result<ReportMedicine?>
    suspend fun add(reportMedicine: ReportMedicine): Result<ReportMedicine>
    suspend fun update(reportMedicine: ReportMedicine): Result<Boolean>
    suspend fun delete(id: Int): Result<Boolean>
}