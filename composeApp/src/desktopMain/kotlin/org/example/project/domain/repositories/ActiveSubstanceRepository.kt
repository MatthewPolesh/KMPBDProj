package org.example.project.domain.repositories

import org.example.project.domain.entities.ActiveSubstance
import org.example.project.domain.filters.ActiveSubstanceFilter

interface ActiveSubstanceRepository {
    suspend fun getAll(): Result<List<ActiveSubstance>>
    suspend fun getById(id: Int): Result<ActiveSubstance?>
    suspend fun add(activeSubstance: ActiveSubstance): Result<ActiveSubstance>
    suspend fun update(activeSubstance: ActiveSubstance): Result<Boolean>
    suspend fun delete(id: Int): Result<Boolean>
    suspend fun getActiveSubstaces(filter: ActiveSubstanceFilter): Result<List<ActiveSubstance>>
}