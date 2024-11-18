package org.example.project.domain.repositories

import org.example.project.domain.entities.ActiveSubstanceReport

interface ActiveSubstanceReportRepository {
    suspend fun getAll(): Result<List<ActiveSubstanceReport>>
    suspend fun getById(id: Int): Result<ActiveSubstanceReport?>
    suspend fun add(activeSubstanceReport: ActiveSubstanceReport): Result<ActiveSubstanceReport>
    suspend fun update(activeSubstanceReport: ActiveSubstanceReport): Result<Boolean>
    suspend fun delete(id: Int): Result<Boolean>
}