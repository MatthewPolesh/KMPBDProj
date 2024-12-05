package org.example.project.data.repositories

import org.example.project.data.database.dao.ActiveSubstanceDao
import org.example.project.data.database.dao.MedicinalFormDao
import org.example.project.data.database.dao.MedicineDao
import org.example.project.data.database.dao.StandardDao
import org.example.project.data.database.tables.ActiveSubstanceTable
import org.example.project.data.database.tables.MedicinalFormTable
import org.example.project.data.database.tables.MedicineTable
import org.example.project.data.database.tables.StandardTable
import org.example.project.data.database.toDomain
import org.example.project.domain.entities.Medicine
import org.example.project.domain.filters.MedicineFilter
import org.example.project.domain.repositories.MedicineRepository
import org.jetbrains.exposed.sql.JoinType
import org.jetbrains.exposed.sql.andWhere
import org.jetbrains.exposed.sql.selectAll

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
    override suspend fun getMedicines(filter: MedicineFilter): Result<List<Medicine>> = safeDbCall {
        val query = MedicineTable
            .join(ActiveSubstanceTable, JoinType.INNER, MedicineTable.activeSubstanceId, ActiveSubstanceTable.id)
            .join(MedicinalFormTable, JoinType.INNER, MedicineTable.medicinalFormId, MedicinalFormTable.id)
            .join(StandardTable, JoinType.INNER, MedicineTable.standardId, StandardTable.id)
            .selectAll()

        if (filter.id != null) {
            query.andWhere { MedicineTable.id eq filter.id }
        }
        if (!filter.producer.isNullOrBlank()) {
            query.andWhere { MedicineTable.producer eq filter.producer }
        }
        if (!filter.name.isNullOrBlank()) {
            query.andWhere { MedicineTable.name eq  filter.name }
        }
        if (filter.dosage != null) {
            query.andWhere { MedicineTable.dosage eq filter.dosage }
        }
        if (filter.dateProduce != null) {
            query.andWhere { MedicineTable.dateProduce eq filter.dateProduce }
        }
        if (filter.activeSubstanceId != null) {
            query.andWhere { MedicineTable.activeSubstanceId eq filter.activeSubstanceId }
        }
        if (!filter.activeSubstanceName.isNullOrBlank()) {
            query.andWhere { ActiveSubstanceTable.name eq filter.activeSubstanceName }
        }
        if (filter.medicinalFormId != null) {
            query.andWhere { MedicineTable.medicinalFormId eq filter.medicinalFormId }
        }
        if (!filter.medicinalFormName.isNullOrBlank()) {
            query.andWhere { MedicinalFormTable.name eq filter.medicinalFormName }
        }
        if (filter.standardId != null) {
            query.andWhere { MedicineTable.standardId eq filter.standardId }
        }
        if (!filter.standardName.isNullOrBlank()) {
            query.andWhere { StandardTable.name eq filter.standardName }
        }

        query.map { row ->
            val medicineId = row[MedicineTable.id].value
            val producer = row[MedicineTable.producer]
            val name = row[MedicineTable.name]
            val dosage = row[MedicineTable.dosage]
            val dateProduce = row[MedicineTable.dateProduce]
            val activeSubstanceId = row[MedicineTable.activeSubstanceId].value
            val activeSubstanceName = row[ActiveSubstanceTable.name]
            val medicinalFormId = row[MedicineTable.medicinalFormId].value
            val medicinalFormName = row[MedicinalFormTable.name]
            val standardId = row[MedicineTable.standardId].value
            val standardName = row[StandardTable.name]

            Medicine(
                id = medicineId,
                producer = producer,
                name = name,
                dosage = dosage,
                dateProduce = dateProduce,
                activeSubstanceId = activeSubstanceId,
                activeSubstanceName = activeSubstanceName,
                medicinalFormId = medicinalFormId,
                medicinalFormName = medicinalFormName,
                standardId = standardId,
                standardName = standardName
            )
        }
    }

}