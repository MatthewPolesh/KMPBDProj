package org.example.project.data.database.dao

import org.example.project.data.database.tables.StandardTable
import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.id.EntityID

class StandardDao(id: EntityID<Int>) : Entity<Int>(id) {
    companion object : EntityClass<Int, StandardDao>(StandardTable)

    var name by StandardTable.name
    var components by StandardTable.components
}
