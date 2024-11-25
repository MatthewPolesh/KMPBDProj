package org.example.project.data.database.dao

import org.example.project.data.database.tables.StatusTable
import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.id.EntityID

class StatusDao(id: EntityID<Int>) : Entity<Int>(id) {
    companion object : EntityClass<Int, StatusDao>(StatusTable)

    var startData by StatusTable.startData
    var endData by StatusTable.endData
    var reasonOfChange by StatusTable.reasonOfChange
}
