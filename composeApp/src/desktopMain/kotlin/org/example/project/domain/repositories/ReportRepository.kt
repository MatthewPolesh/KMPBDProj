package org.example.project.domain.repositories

import org.example.project.domain.entities.Report


interface ReportRepository {
    suspend fun getAll(): Result<List<Report>>
    suspend fun getById(id: Int): Result<Report?>
    suspend fun add(report: Report): Result<Report>
    suspend fun update(report: Report): Result<Boolean>
    suspend fun delete(id: Int): Result<Boolean>

}