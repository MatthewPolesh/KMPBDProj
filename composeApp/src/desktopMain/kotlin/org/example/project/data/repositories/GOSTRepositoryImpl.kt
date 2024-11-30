package org.example.project.data.repositories

import org.example.project.data.database.dao.GOSTDao
import org.example.project.data.database.toDomain
import org.example.project.domain.entities.GOST
import org.example.project.domain.repositories.GOSTRepository

class GOSTRepositoryImpl : BaseRepository(), GOSTRepository {

    override suspend fun getAll(): Result<List<GOST>> = safeDbCall {
        GOSTDao.all().map { it.toDomain() }
    }

    override suspend fun getById(id: Int): Result<GOST?> = safeDbCall {
        GOSTDao.findById(id)?.toDomain()
    }

    override suspend fun add(gost: GOST): Result<GOST> = safeDbCall {
        val dao = GOSTDao.new {
            name = gost.name
        }
        dao.toDomain()
    }

    override suspend fun update(gost: GOST): Result<Boolean> = safeDbCall {
        val dao = GOSTDao.findById(gost.id)
        if (dao != null) {
            dao.name = gost.name
            true
        } else {
            false
        }
    }

    override suspend fun delete(id: Int): Result<Boolean> = safeDbCall {
        val dao = GOSTDao.findById(id)
        if (dao != null) {
            dao.delete()
            true
        } else {
            false
        }
    }
}