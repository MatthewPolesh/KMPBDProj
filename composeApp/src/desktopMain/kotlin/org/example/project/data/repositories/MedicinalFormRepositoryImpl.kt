package org.example.project.data.repositories

import org.example.project.data.database.dao.MedicalOfficerDao
import org.example.project.data.database.dao.MedicinalFormDao
import org.example.project.data.database.toDomain
import org.example.project.domain.entities.MedicinalForm
import org.example.project.domain.repositories.MedicinalFormRepository

class MedicinalFormRepositoryImpl : BaseRepository(), MedicinalFormRepository {

    override suspend fun getAll(): Result<List<MedicinalForm>> = safeDbCall {
        MedicinalFormDao.all().map { it.toDomain() }
    }

    override suspend fun getById(id: Int): Result<MedicinalForm?> = safeDbCall {
        MedicinalFormDao.findById(id)?.toDomain()
    }

    override suspend fun add(medicinalForm: MedicinalForm): Result<MedicinalForm> = safeDbCall {
        val medicalOfficerDao = MedicalOfficerDao.findById(medicinalForm.medicalOfficerId)
            ?: throw Exception("Medical Officer not found")

        val dao = MedicinalFormDao.new {
            name = medicinalForm.name
            composition = medicinalForm.composition
            this.medicalOfficerDao = medicalOfficerDao
        }
        dao.toDomain()
    }

    override suspend fun update(medicinalForm: MedicinalForm): Result<Boolean> = safeDbCall {
        val dao = MedicinalFormDao.findById(medicinalForm.id)
        if (dao != null) {
            dao.name = medicinalForm.name
            dao.composition = medicinalForm.composition

            val medicalOfficerDao = MedicalOfficerDao.findById(medicinalForm.medicalOfficerId)
                ?: throw Exception("Medical Officer not found")
            dao.medicalOfficerDao = medicalOfficerDao

            true
        } else {
            false
        }
    }

    override suspend fun delete(id: Int): Result<Boolean> = safeDbCall {
        val dao = MedicinalFormDao.findById(id)
        if (dao != null) {
            dao.delete()
            true
        } else {
            false
        }
    }
}