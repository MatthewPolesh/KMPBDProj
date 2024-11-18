package org.example.project.data.database.dao

import org.example.project.data.database.tables.GOSTTable
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class GOSTDao(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<GOSTDao>(GOSTTable)

    var name by GOSTTable.name
}
