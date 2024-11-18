package org.example.project.data.repositories

import org.example.project.data.database.dao.GOSTDao
import org.example.project.data.database.dao.GOSTMedicineDao
import org.example.project.data.database.dao.MedicineDao
import org.example.project.data.database.toDomain
import org.example.project.domain.entities.GOSTMedicine
import org.example.project.domain.repositories.GOSTMedicineRepository

class GOSTMedicineRepositoryImpl : BaseRepository(), GOSTMedicineRepository {

    override suspend fun getAll(): Result<List<GOSTMedicine>> = safeDbCall {
        GOSTMedicineDao.all().map { it.toDomain() }
    }

    override suspend fun getById(id: Int): Result<GOSTMedicine?> = safeDbCall {
        GOSTMedicineDao.findById(id)?.toDomain()
    }

    override suspend fun add(gostMedicine: GOSTMedicine): Result<GOSTMedicine> = safeDbCall {
        val gostDao = GOSTDao.findById(gostMedicine.gostId)
            ?: throw Exception("GOST not found")
        val medicineDao = MedicineDao.findById(gostMedicine.medicineId)
            ?: throw Exception("Medicine not found")

        val dao = GOSTMedicineDao.new {
            this.gostDao = gostDao
            this.medicineDao = medicineDao
        }
        dao.toDomain()
    }

    override suspend fun update(gostMedicine: GOSTMedicine): Result<Boolean> = safeDbCall {
        val dao = GOSTMedicineDao.findById(gostMedicine.id)
        if (dao != null) {
            val gostDao = GOSTDao.findById(gostMedicine.gostId)
                ?: throw Exception("GOST not found")
            val medicineDao = MedicineDao.findById(gostMedicine.medicineId)
                ?: throw Exception("Medicine not found")

            dao.gostDao = gostDao
            dao.medicineDao = medicineDao

            true
        } else {
            false
        }
    }

    override suspend fun delete(id: Int): Result<Boolean> = safeDbCall {
        val dao = GOSTMedicineDao.findById(id)
        if (dao != null) {
            dao.delete()
            true
        } else {
            false
        }
    }
}