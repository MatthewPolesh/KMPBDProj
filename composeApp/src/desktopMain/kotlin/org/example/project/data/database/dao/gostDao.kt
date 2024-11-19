package org.example.project.data.database.dao

import org.example.project.data.database.tables.GOSTTable
import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.id.EntityID

class GOSTDao(id: EntityID<Int>) : Entity<Int>(id) {
    companion object : EntityClass<Int, GOSTDao>(GOSTTable)

    var name by GOSTTable.name
}
