package org.example.project.domain.repositories

import org.example.project.domain.entities.Report
import org.example.project.domain.filters.ReportFilter


interface ReportRepository {
    suspend fun getAll(): Result<List<Report>>
    suspend fun getById(id: Int): Result<Report?>
    suspend fun add(report: Report): Result<Report>
    suspend fun update(report: Report): Result<Boolean>
    suspend fun delete(id: Int): Result<Boolean>
    suspend fun getReports(filter: ReportFilter): Result<List<Report>>

}