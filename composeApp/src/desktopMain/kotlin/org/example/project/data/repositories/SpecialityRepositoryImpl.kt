package org.example.project.data.repositories

import org.example.project.data.database.dao.SpecialityDao
import org.example.project.data.database.toDomain
import org.example.project.domain.entities.Speciality
import org.example.project.domain.repositories.SpecialityRepository

class SpecialityRepositoryImpl : BaseRepository(), SpecialityRepository {

    override suspend fun getAll(): Result<List<Speciality>> = safeDbCall {
        SpecialityDao.all().map { it.toDomain() }
    }

    override suspend fun getById(id: Int): Result<Speciality?> = safeDbCall {
        SpecialityDao.findById(id)?.toDomain()
    }

    override suspend fun add(speciality: Speciality): Result<Speciality> = safeDbCall {
        val dao = SpecialityDao.new {
            name = speciality.name
            duties = speciality.duties
        }
        dao.toDomain()
    }

    override suspend fun update(speciality: Speciality): Result<Boolean> = safeDbCall {
        val dao = SpecialityDao.findById(speciality.id)
        if (dao != null) {
            dao.name = speciality.name
            dao.duties = speciality.duties
            true
        } else {
            false
        }
    }

    override suspend fun delete(id: Int): Result<Boolean> = safeDbCall {
        val dao = SpecialityDao.findById(id)
        if (dao != null) {
            dao.delete()
            true
        } else {
            false
        }
    }
}