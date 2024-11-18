package org.example.project.data.repositories

import org.example.project.data.database.dao.StandardDao
import org.example.project.data.database.toDomain
import org.example.project.domain.entities.Standard
import org.example.project.domain.repositories.StandardRepository

class StandardRepositoryImpl : BaseRepository(), StandardRepository {

    override suspend fun getAll(): Result<List<Standard>> = safeDbCall {
        StandardDao.all().map { it.toDomain() }
    }

    override suspend fun getById(id: Int): Result<Standard?> = safeDbCall {
        StandardDao.findById(id)?.toDomain()
    }

    override suspend fun add(standard: Standard): Result<Standard> = safeDbCall {
        val dao = StandardDao.new {
            name = standard.name
            components = standard.components
        }
        dao.toDomain()
    }

    override suspend fun update(standard: Standard): Result<Boolean> = safeDbCall {
        val dao = StandardDao.findById(standard.id)
        if (dao != null) {
            dao.name = standard.name
            dao.components = standard.components
            true
        } else {
            false
        }
    }

    override suspend fun delete(id: Int): Result<Boolean> = safeDbCall {
        val dao = StandardDao.findById(id)
        if (dao != null) {
            dao.delete()
            true
        } else {
            false
        }
    }
}