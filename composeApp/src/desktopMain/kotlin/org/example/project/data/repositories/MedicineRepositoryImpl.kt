package org.example.project.data.repositories

import org.example.project.data.database.dao.ActiveSubstanceDao
import org.example.project.data.database.dao.MedicinalFormDao
import org.example.project.data.database.dao.MedicineDao
import org.example.project.data.database.dao.StandardDao
import org.example.project.data.database.toDomain
import org.example.project.domain.entities.Medicine
import org.example.project.domain.repositories.MedicineRepository

class MedicineRepositoryImpl : BaseRepository(), MedicineRepository {

    override suspend fun getAll(): Result<List<Medicine>> = safeDbCall {
        MedicineDao.all().map { it.toDomain() }
    }

    override suspend fun getById(id: Int): Result<Medicine?> = safeDbCall {
        MedicineDao.findById(id)?.toDomain()
    }

    override suspend fun add(medicine: Medicine): Result<Medicine> = safeDbCall {
        val activeSubstanceDao = ActiveSubstanceDao.findById(medicine.activeSubstanceId)
            ?: throw Exception("Active Substance not found")
        val medicinalFormDao = MedicinalFormDao.findById(medicine.medicinalFormId)
            ?: throw Exception("Medicinal Form not found")
        val standardDao = StandardDao.findById(medicine.standardId)
            ?: throw Exception("Standard not found")

        val dao = MedicineDao.new {
            producer = medicine.producer
            name = medicine.name
            dateProduce = medicine.dateProduce
            this.activeSubstanceDao = activeSubstanceDao
            this.medicinalFormDao = medicinalFormDao
            this.standardDao = standardDao
        }
        dao.toDomain()
    }

    override suspend fun update(medicine: Medicine): Result<Boolean> = safeDbCall {
        val dao = MedicineDao.findById(medicine.id)
        if (dao != null) {
            dao.producer = medicine.producer
            dao.name = medicine.name
            dao.dateProduce = medicine.dateProduce

            val activeSubstanceDao = ActiveSubstanceDao.findById(medicine.activeSubstanceId)
                ?: throw Exception("Active Substance not found")
            val medicinalFormDao = MedicinalFormDao.findById(medicine.medicinalFormId)
                ?: throw Exception("Medicinal Form not found")
            val standardDao = StandardDao.findById(medicine.standardId)
                ?: throw Exception("Standard not found")

            dao.activeSubstanceDao = activeSubstanceDao
            dao.medicinalFormDao = medicinalFormDao
            dao.standardDao = standardDao

            true
        } else {
            false
        }
    }

    override suspend fun delete(id: Int): Result<Boolean> = safeDbCall {
        val dao = MedicineDao.findById(id)
        if (dao != null) {
            dao.delete()
            true
        } else {
            false
        }
    }
}