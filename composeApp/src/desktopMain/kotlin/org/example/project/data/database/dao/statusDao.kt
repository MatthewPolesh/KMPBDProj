package org.example.project.data.database.dao

import org.example.project.data.database.tables.StatusTable
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class StatusDao(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<StatusDao>(StatusTable)

    var startData by StatusTable.startData
    var endData by StatusTable.endData
    var reasonOfChange by StatusTable.reasonOfChange
}
