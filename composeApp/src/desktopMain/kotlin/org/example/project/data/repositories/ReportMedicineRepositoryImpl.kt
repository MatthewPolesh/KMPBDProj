package org.example.project.data.repositories

import org.example.project.data.database.dao.MedicalOfficerDao
import org.example.project.data.database.dao.MedicineDao
import org.example.project.data.database.dao.ReportDao
import org.example.project.data.database.dao.ReportMedicineDao
import org.example.project.data.database.toDomain
import org.example.project.domain.entities.ReportMedicine
import org.example.project.domain.repositories.ReportMedicineRepository

class ReportMedicineRepositoryImpl : BaseRepository(), ReportMedicineRepository {

    override suspend fun getAll(): Result<List<ReportMedicine>> = safeDbCall {
        ReportMedicineDao.all().map { it.toDomain() }
    }

    override suspend fun getById(id: Int): Result<ReportMedicine?> = safeDbCall {
        ReportMedicineDao.findById(id)?.toDomain()
    }

    override suspend fun add(reportMedicine: ReportMedicine): Result<ReportMedicine> = safeDbCall {
        val reportDao = ReportDao.findById(reportMedicine.reportId)
            ?: throw Exception("Report not found")
        val medicineDao = MedicineDao.findById(reportMedicine.medicineId)
            ?: throw Exception("Medicine not found")
        val medicalOfficerDao = MedicalOfficerDao.findById(reportMedicine.reportMedicalOfficerId)
            ?: throw Exception("Medical Officer not found")

        val dao = ReportMedicineDao.new {
            this.reportDao = reportDao
            this.medicineDao = medicineDao
            this.medicalOfficerDao = medicalOfficerDao
        }
        dao.toDomain()
    }

    override suspend fun update(reportMedicine: ReportMedicine): Result<Boolean> = safeDbCall {
        val dao = ReportMedicineDao.findById(reportMedicine.id)
        if (dao != null) {
            val reportDao = ReportDao.findById(reportMedicine.reportId)
                ?: throw Exception("Report not found")
            val medicineDao = MedicineDao.findById(reportMedicine.medicineId)
                ?: throw Exception("Medicine not found")
            val medicalOfficerDao = MedicalOfficerDao.findById(reportMedicine.reportMedicalOfficerId)
                ?: throw Exception("Medical Officer not found")

            dao.reportDao = reportDao
            dao.medicineDao = medicineDao
            dao.medicalOfficerDao = medicalOfficerDao

            true
        } else {
            false
        }
    }

    override suspend fun delete(id: Int): Result<Boolean> = safeDbCall {
        val dao = ReportMedicineDao.findById(id)
        if (dao != null) {
            dao.delete()
            true
        } else {
            false
        }
    }
}