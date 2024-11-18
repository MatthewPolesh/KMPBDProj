package org.example.project.data.repositories

import org.example.project.data.database.dao.ActiveSubstanceDao
import org.example.project.data.database.dao.ActiveSubstanceReportDao
import org.example.project.data.database.dao.MedicalOfficerDao
import org.example.project.data.database.dao.ReportDao
import org.example.project.data.database.toDomain
import org.example.project.domain.entities.ActiveSubstanceReport
import org.example.project.domain.repositories.ActiveSubstanceReportRepository


class ActiveSubstanceReportRepositoryImpl : BaseRepository(), ActiveSubstanceReportRepository {

    override suspend fun getAll(): Result<List<ActiveSubstanceReport>> = safeDbCall {
        ActiveSubstanceReportDao.all().map { it.toDomain() }
    }

    override suspend fun getById(id: Int): Result<ActiveSubstanceReport?> = safeDbCall {
        ActiveSubstanceReportDao.findById(id)?.toDomain()
    }

    override suspend fun add(activeSubstanceReport: ActiveSubstanceReport): Result<ActiveSubstanceReport> = safeDbCall {
        val activeSubstanceDao = ActiveSubstanceDao.findById(activeSubstanceReport.activeSubstanceId)
            ?: throw Exception("Active Substance not found")
        val reportDao = ReportDao.findById(activeSubstanceReport.reportId)
            ?: throw Exception("Report not found")
        val medicalOfficerDao = MedicalOfficerDao.findById(activeSubstanceReport.reportMedicalOfficerId)
            ?: throw Exception("Medical Officer not found")

        val dao = ActiveSubstanceReportDao.new {
            this.activeSubstanceDao = activeSubstanceDao
            this.reportDao = reportDao
            this.medicalOfficerDao = medicalOfficerDao
        }
        dao.toDomain()
    }

    override suspend fun update(activeSubstanceReport: ActiveSubstanceReport): Result<Boolean> = safeDbCall {
        val dao = ActiveSubstanceReportDao.findById(activeSubstanceReport.id)
        if (dao != null) {
            val activeSubstanceDao = ActiveSubstanceDao.findById(activeSubstanceReport.activeSubstanceId)
                ?: throw Exception("Active Substance not found")
            val reportDao = ReportDao.findById(activeSubstanceReport.reportId)
                ?: throw Exception("Report not found")
            val medicalOfficerDao = MedicalOfficerDao.findById(activeSubstanceReport.reportMedicalOfficerId)
                ?: throw Exception("Medical Officer not found")

            dao.activeSubstanceDao = activeSubstanceDao
            dao.reportDao = reportDao
            dao.medicalOfficerDao = medicalOfficerDao

            true
        } else {
            false
        }
    }

    override suspend fun delete(id: Int): Result<Boolean> = safeDbCall {
        val dao = ActiveSubstanceReportDao.findById(id)
        if (dao != null) {
            dao.delete()
            true
        } else {
            false
        }
    }
}
