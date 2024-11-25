package org.example.project.data.repositories

import org.example.project.data.database.dao.MedicalOfficerDao
import org.example.project.data.database.dao.ReportDao
import org.example.project.data.database.toDomain
import org.example.project.domain.entities.Report
import org.example.project.domain.repositories.ReportRepository

class ReportRepositoryImpl : BaseRepository(), ReportRepository {

    override suspend fun getAll(): Result<List<Report>> = safeDbCall {
        ReportDao.all().map { it.toDomain() }
    }

    override suspend fun getById(id: Int): Result<Report?> = safeDbCall {
        ReportDao.findById(id)?.toDomain()
    }

    override suspend fun add(report: Report): Result<Report> = safeDbCall {
        val medicalOfficerDao = MedicalOfficerDao.findById(report.medicalOfficerId)
            ?: throw Exception("Medical Officer not found")

        val dao = ReportDao.new {
            name = report.name
            date = report.date
            this.medicalOfficerDao = medicalOfficerDao
        }
        dao.toDomain()
    }

    override suspend fun update(report: Report): Result<Boolean> = safeDbCall {
        val dao = ReportDao.findById(report.id)
        if (dao != null) {
            dao.name = report.name
            dao.date = report.date

            val medicalOfficerDao = MedicalOfficerDao.findById(report.medicalOfficerId)
                ?: throw Exception("Medical Officer not found")
            dao.medicalOfficerDao = medicalOfficerDao
            true
        } else {
            false
        }
    }

    override suspend fun delete(id: Int): Result<Boolean> = safeDbCall {
        val dao = ReportDao.findById(id)
        if (dao != null) {
            dao.delete()
            true
        } else {
            false
        }
    }
}