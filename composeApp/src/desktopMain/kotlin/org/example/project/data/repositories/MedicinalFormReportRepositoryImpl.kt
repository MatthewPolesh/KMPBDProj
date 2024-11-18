package org.example.project.data.repositories

import org.example.project.data.database.dao.MedicalOfficerDao
import org.example.project.data.database.dao.MedicinalFormDao
import org.example.project.data.database.dao.MedicinalFormReportDao
import org.example.project.data.database.dao.ReportDao
import org.example.project.data.database.toDomain
import org.example.project.domain.entities.MedicinalFormReport
import org.example.project.domain.repositories.MedicinalFormReportRepository

class MedicinalFormReportRepositoryImpl : BaseRepository(), MedicinalFormReportRepository {

    override suspend fun getAll(): Result<List<MedicinalFormReport>> = safeDbCall {
        MedicinalFormReportDao.all().map { it.toDomain() }
    }

    override suspend fun getById(id: Int): Result<MedicinalFormReport?> = safeDbCall {
        MedicinalFormReportDao.findById(id)?.toDomain()
    }

    override suspend fun add(medicinalFormReport: MedicinalFormReport): Result<MedicinalFormReport> = safeDbCall {
        val medicinalFormDao = MedicinalFormDao.findById(medicinalFormReport.medicinalFormId)
            ?: throw Exception("Medicinal Form not found")
        val reportDao = ReportDao.findById(medicinalFormReport.reportId)
            ?: throw Exception("Report not found")
        val medicalOfficerDao = MedicalOfficerDao.findById(medicinalFormReport.reportMedicalOfficerId)
            ?: throw Exception("Medical Officer not found")

        val dao = MedicinalFormReportDao.new {
            this.medicinalFormDao = medicinalFormDao
            this.reportDao = reportDao
            this.medicalOfficerDao = medicalOfficerDao
        }
        dao.toDomain()
    }

    override suspend fun update(medicinalFormReport: MedicinalFormReport): Result<Boolean> = safeDbCall {
        val dao = MedicinalFormReportDao.findById(medicinalFormReport.id)
        if (dao != null) {
            val medicinalFormDao = MedicinalFormDao.findById(medicinalFormReport.medicinalFormId)
                ?: throw Exception("Medicinal Form not found")
            val reportDao = ReportDao.findById(medicinalFormReport.reportId)
                ?: throw Exception("Report not found")
            val medicalOfficerDao = MedicalOfficerDao.findById(medicinalFormReport.reportMedicalOfficerId)
                ?: throw Exception("Medical Officer not found")

            dao.medicinalFormDao = medicinalFormDao
            dao.reportDao = reportDao
            dao.medicalOfficerDao = medicalOfficerDao

            true
        } else {
            false
        }
    }

    override suspend fun delete(id: Int): Result<Boolean> = safeDbCall {
        val dao = MedicinalFormReportDao.findById(id)
        if (dao != null) {
            dao.delete()
            true
        } else {
            false
        }
    }
}