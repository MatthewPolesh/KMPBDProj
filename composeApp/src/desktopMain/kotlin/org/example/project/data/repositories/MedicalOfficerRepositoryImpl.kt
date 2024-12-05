package org.example.project.data.repositories

import org.example.project.data.database.dao.MedicalOfficerDao
import org.example.project.data.database.dao.SpecialityDao
import org.example.project.data.database.toDomain
import org.example.project.domain.entities.MedicalOfficer
import org.example.project.domain.repositories.MedicalOfficerRepository
import org.jetbrains.exposed.sql.IntegerColumnType
import org.jetbrains.exposed.sql.transactions.TransactionManager


class MedicalOfficerRepositoryImpl : BaseRepository(), MedicalOfficerRepository {

    override suspend fun getAll(): Result<List<MedicalOfficer>> = safeDbCall {

        MedicalOfficerDao.all().map {
            val bonus = TransactionManager.current().exec(
                stmt = "CALL bonus_account(?)",
                args = listOf(Pair(IntegerColumnType(), it.workExperience)),
                transform = {
                        rs ->
                    if (rs.next()) {
                        rs.getInt("bonus")
                    } else {
                        throw Exception("Не удалось получить бонус из процедуры")
                    }
                }
            )
            if (bonus != null)
                it.toDomain(bonus)
            else
                throw Exception("Не удалось получить бонус из процедуры")

        }

    }

    override suspend fun getById(id: Int): Result<MedicalOfficer?> = safeDbCall {
        MedicalOfficerDao.findById(id)?.toDomain()
    }

    override suspend fun add(medicalOfficer: MedicalOfficer): Result<MedicalOfficer> = safeDbCall {
        val specialityDao = SpecialityDao.findById(medicalOfficer.specialityId)
            ?: throw Exception("Speciality not found")

        val dao = MedicalOfficerDao.new {
            firstName = medicalOfficer.firstName
            lastName = medicalOfficer.lastName
            surname = medicalOfficer.surname
            email = medicalOfficer.email
            numberChild = medicalOfficer.numberChild
            age = medicalOfficer.age
            workExperience = medicalOfficer.workExperience
            this.specialityDao = specialityDao
        }
        dao.toDomain()
    }

    override suspend fun update(medicalOfficer: MedicalOfficer): Result<Boolean> = safeDbCall {
        val dao = MedicalOfficerDao.findById(medicalOfficer.id)
        if (dao != null) {
            dao.firstName = medicalOfficer.firstName
            dao.lastName = medicalOfficer.lastName
            dao.surname = medicalOfficer.surname
            dao.email = medicalOfficer.email
            dao.workExperience = medicalOfficer.workExperience
            dao.numberChild = medicalOfficer.numberChild
            dao.age = medicalOfficer.age

            val specialityDao = SpecialityDao.findById(medicalOfficer.specialityId)
                ?: throw Exception("Speciality not found")
            dao.specialityDao = specialityDao

            true
        } else {
            false
        }
    }

    override suspend fun delete(id: Int): Result<Boolean> = safeDbCall {
        val dao = MedicalOfficerDao.findById(id)
        if (dao != null) {
            dao.delete()
            true
        } else {
            false
        }
    }
}

