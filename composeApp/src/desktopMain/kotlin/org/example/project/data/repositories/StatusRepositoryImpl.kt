package org.example.project.data.repositories

import org.example.project.data.database.dao.StatusDao
import org.example.project.data.database.toDomain
import org.example.project.domain.entities.Status
import org.example.project.domain.repositories.StatusRepository

class StatusRepositoryImpl : BaseRepository(), StatusRepository {

    override suspend fun getAll(): Result<List<Status>> = safeDbCall {
        StatusDao.all().map { it.toDomain() }
    }

    override suspend fun getById(id: Int): Result<Status?> = safeDbCall {
        StatusDao.findById(id)?.toDomain()
    }

    override suspend fun add(status: Status): Result<Status> = safeDbCall {
        val dao = StatusDao.new {
            startData = status.startData
            endData = status.endData
            reasonOfChange = status.reasonOfChange
        }
        dao.toDomain()
    }

    override suspend fun update(status: Status): Result<Boolean> = safeDbCall {
        val dao = StatusDao.findById(status.id)
        if (dao != null) {
            dao.startData = status.startData
            dao.endData = status.endData
            dao.reasonOfChange = status.reasonOfChange
            true
        } else {
            false
        }
    }

    override suspend fun delete(id: Int): Result<Boolean> = safeDbCall {
        val dao = StatusDao.findById(id)
        if (dao != null) {
            dao.delete()
            true
        } else {
            false
        }
    }
}