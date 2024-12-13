package org.example.project.data.repositories

import org.example.project.data.database.dao.ActiveSubstanceDao
import org.example.project.data.database.dao.MedicalOfficerDao
import org.example.project.data.database.tables.ActiveSubstanceTable
import org.example.project.data.database.tables.MedicalOfficerTable
import org.example.project.data.database.toDomain
import org.example.project.domain.entities.ActiveSubstance
import org.example.project.domain.filters.ActiveSubstanceFilter
import org.example.project.domain.repositories.ActiveSubstanceRepository
import org.jetbrains.exposed.sql.JoinType
import org.jetbrains.exposed.sql.andWhere
import org.jetbrains.exposed.sql.selectAll


class ActiveSubstanceRepositoryImpl : BaseRepository(), ActiveSubstanceRepository {

    override suspend fun getAll(): Result<List<ActiveSubstance>> = safeDbCall {
        ActiveSubstanceDao.all().map { it.toDomain() }
    }

    override suspend fun getById(id: Int): Result<ActiveSubstance?> = safeDbCall {
        ActiveSubstanceDao.findById(id)?.toDomain()
    }

    override suspend fun add(activeSubstance: ActiveSubstance): Result<ActiveSubstance> = safeDbCall {
        val medicalOfficerDao = MedicalOfficerDao.findById(activeSubstance.medicalOfficerId)
            ?: throw Exception("Такой сотрудник не найден")

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
                ?: throw Exception("Такой сотрудник не найден")
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

    override suspend fun getActiveSubstaces(filter: ActiveSubstanceFilter): Result<List<ActiveSubstance>> = safeDbCall{
        val query = ActiveSubstanceTable
            .join(MedicalOfficerTable, JoinType.INNER, ActiveSubstanceTable.medicalOfficerId, MedicalOfficerTable.id)
            .selectAll()


        if (filter.id != null) {
            query.andWhere { ActiveSubstanceTable.id eq filter.id }
        }
        if (filter.name != "") {
            query.andWhere { ActiveSubstanceTable.name eq filter.name }
        }
        if (filter.composition != "") {
            query.andWhere { ActiveSubstanceTable.composition eq filter.composition }
        }
        if (filter.appointment != "") {
            query.andWhere { ActiveSubstanceTable.appointment eq filter.appointment }
        }
        if (filter.medicalOfficerId != null) {
            query.andWhere { ActiveSubstanceTable.medicalOfficerId eq filter.medicalOfficerId }
        }

        query.map { row ->
            val activeSubstanceId = row[ActiveSubstanceTable.id].value
            val activeSubstanceName = row[ActiveSubstanceTable.name]
            val activeSubstanceComposition = row[ActiveSubstanceTable.composition]
            val activeSubstanceAppointment = row[ActiveSubstanceTable.appointment]
            val medicalOfficerId = row[ActiveSubstanceTable.medicalOfficerId].value

            ActiveSubstance(
                id = activeSubstanceId,
                name = activeSubstanceName,
                composition = activeSubstanceComposition,
                appointment = activeSubstanceAppointment,
                medicalOfficerId = medicalOfficerId,
                medicalOfficerName = ""
            )
        }
    }
}
