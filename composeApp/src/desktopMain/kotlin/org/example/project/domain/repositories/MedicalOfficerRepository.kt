package org.example.project.domain.repositories

import org.example.project.domain.entities.MedicalOfficer

interface MedicalOfficerRepository {
    suspend fun getAll(): Result<List<MedicalOfficer>>
    suspend fun getById(id: Int): Result<MedicalOfficer?>
    suspend fun add(medicalOfficer: MedicalOfficer): Result<MedicalOfficer>
    suspend fun update(medicalOfficer: MedicalOfficer): Result<Boolean>
    suspend fun delete(id: Int): Result<Boolean>

}