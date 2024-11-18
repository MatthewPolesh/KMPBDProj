package org.example.project.data.repositories

import org.example.project.data.database.dao.ActiveSubstanceDao
import org.example.project.data.database.dao.MedicalOfficerDao
import org.example.project.data.database.toDomain
import org.example.project.domain.entities.ActiveSubstance
import org.example.project.domain.repositories.ActiveSubstanceRepository


class ActiveSubstanceRepositoryImpl : BaseRepository(), ActiveSubstanceRepository {

    override suspend fun getAll(): Result<List<ActiveSubstance>> = safeDbCall {
        ActiveSubstanceDao.all().map { it.toDomain() }
    }

    override suspend fun getById(id: Int): Result<ActiveSubstance?> = safeDbCall {
        ActiveSubstanceDao.findById(id)?.toDomain()
    }

    override suspend fun add(activeSubstance: ActiveSubstance): Result<ActiveSubstance> = safeDbCall {
        val medicalOfficerDao = MedicalOfficerDao.findById(activeSubstance.medicalOfficerId)
            ?: throw Exception("Medical Officer not found")

        val dao = ActiveSubstanceDao.new {
            this.name = activeSubstance.name
            this.composition = activeSubstance.composition
            this.appointment = activeSubstance.appointment
            this.medicalOfficerDao = medicalOfficerDao
        }
        dao.toDomain()
    }

    override suspend fun update(activeSubstance: ActiveSubstance): Result<Boolean> = safeDbCall {
        val dao = ActiveSubstanceDao.findById(activeSubstance.id)
        if (dao != null) {
            dao.name = activeSubstance.name
            dao.composition = activeSubstance.composition
            dao.appointment = activeSubstance.appointment

            val medicalOfficerDao = MedicalOfficerDao.findById(activeSubstance.medicalOfficerId)
                ?: throw Exception("Medical Officer not found")
            dao.medicalOfficerDao = medicalOfficerDao

            true
        } else {
            false
        }
    }

    override suspend fun delete(id: Int): Result<Boolean> = safeDbCall {
        val dao = ActiveSubstanceDao.findById(id)
        if (dao != null) {
            dao.delete()
            true
        } else {
            false
        }
    }
}
