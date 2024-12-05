package org.example.project.data.repositories

import org.example.project.data.database.dao.MedicalOfficerDao
import org.example.project.data.database.dao.ReportDao
import org.example.project.data.database.tables.MedicalOfficerTable
import org.example.project.data.database.tables.ReportTable
import org.example.project.data.database.toDomain
import org.example.project.domain.entities.Report
import org.example.project.domain.filters.ReportFilter
import org.example.project.domain.repositories.ReportRepository
import org.jetbrains.exposed.dao.with
import org.jetbrains.exposed.sql.JoinType
import org.jetbrains.exposed.sql.andWhere
import org.jetbrains.exposed.sql.selectAll


class ReportRepositoryImpl : BaseRepository(), ReportRepository {

    override suspend fun getAll(): Result<List<Report>> = safeDbCall {
        ReportDao.all()
            .with(ReportDao::medicalOfficerDao)
            .map { it.toDomain() }
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
            dao.toDomain()
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
    override suspend fun getReports(filter: ReportFilter): Result<List<Report>> = safeDbCall {
        val query = ReportTable
            .join(MedicalOfficerTable, JoinType.INNER, ReportTable.medicalOfficerId, MedicalOfficerTable.id)
            .selectAll()

        if (filter.id != null) {
            query.andWhere { ReportTable.id eq filter.id }
        }
        if (!filter.name.isNullOrBlank()) {
            query.andWhere { ReportTable.name eq filter.name }
        }
        if (filter.date != null) {
            query.andWhere { ReportTable.date eq filter.date }
        }
        if (filter.medicalOfficerId != null) {
            query.andWhere { ReportTable.medicalOfficerId eq filter.medicalOfficerId }
        }

        if (!filter.medicalOfficerName.isNullOrBlank()) {
            val medicalOfficerFIO = filter.medicalOfficerName.split(" ")
            query.andWhere { MedicalOfficerTable.surname eq medicalOfficerFIO[0] }
            query.andWhere { MedicalOfficerTable.firstName eq medicalOfficerFIO[1] }
            query.andWhere { MedicalOfficerTable.lastName eq medicalOfficerFIO[2] }
        }


        query.map { row ->
            val id = row[ReportTable.id].value
            val name = row[ReportTable.name]
            val date = row[ReportTable.date]
            val medicalOfficerId = row[ReportTable.medicalOfficerId].value
            val medicalOfficerSurname = row[MedicalOfficerTable.surname]
            val medicalOfficerFirstName = row[MedicalOfficerTable.firstName]
            val medicalOfficerLastName = row[MedicalOfficerTable.lastName]

            Report(
                id = id,
                name = name,
                date = date,
                medicalOfficerId = medicalOfficerId,
                medicalOfficerName = "$medicalOfficerSurname $medicalOfficerFirstName $medicalOfficerLastName"
            )
        }
    }

}