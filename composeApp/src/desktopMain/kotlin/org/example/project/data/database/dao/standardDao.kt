package org.example.project.data.database.dao

import org.example.project.data.database.tables.StandardTable
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class StandardDao(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<StandardDao>(StandardTable)

    var name by StandardTable.name
    var components by StandardTable.components
}
