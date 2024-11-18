package org.example.project.data.repositories

import org.example.project.data.database.dao.MedicalOfficerDao
import org.example.project.data.database.dao.SpecialityDao
import org.example.project.data.database.toDomain
import org.example.project.domain.entities.MedicalOfficer
import org.example.project.domain.repositories.MedicalOfficerRepository

class MedicalOfficerRepositoryImpl : BaseRepository(), MedicalOfficerRepository {

    override suspend fun getAll(): Result<List<MedicalOfficer>> = safeDbCall {
        MedicalOfficerDao.all().map { it.toDomain() }
    }

    override suspend fun getById(id: Int): Result<MedicalOfficer?> = safeDbCall {
        MedicalOfficerDao.findById(id)?.toDomain()
    }

    override suspend fun add(medicalOfficer: MedicalOfficer): Result<MedicalOfficer> = safeDbCall {
        val specialityDao = SpecialityDao.findById(medicalOfficer.specialityId)
            ?: throw Exception("Speciality not found")

        val dao = MedicalOfficerDao.new {
            firstName = medicalOfficer.firstName
            lastName = medicalOfficer.lastName
            surname = medicalOfficer.surname
            email = medicalOfficer.email
            workExperience = medicalOfficer.workExperience
            this.specialityDao = specialityDao
        }
        dao.toDomain()
    }

    override suspend fun update(medicalOfficer: MedicalOfficer): Result<Boolean> = safeDbCall {
        val dao = MedicalOfficerDao.findById(medicalOfficer.id)
        if (dao != null) {
            dao.firstName = medicalOfficer.firstName
            dao.lastName = medicalOfficer.lastName
            dao.surname = medicalOfficer.surname
            dao.email = medicalOfficer.email
            dao.workExperience = medicalOfficer.workExperience

            val specialityDao = SpecialityDao.findById(medicalOfficer.specialityId)
                ?: throw Exception("Speciality not found")
            dao.specialityDao = specialityDao

            true
        } else {
            false
        }
    }

    override suspend fun delete(id: Int): Result<Boolean> = safeDbCall {
        val dao = MedicalOfficerDao.findById(id)
        if (dao != null) {
            dao.delete()
            true
        } else {
            false
        }
    }
}