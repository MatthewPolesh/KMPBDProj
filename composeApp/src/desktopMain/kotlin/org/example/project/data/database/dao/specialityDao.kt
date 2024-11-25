package org.example.project.data.database.dao

import org.example.project.data.database.tables.SpecialityTable
import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.id.EntityID

class SpecialityDao(id: EntityID<Int>) : Entity<Int>(id) {
    companion object : EntityClass<Int,SpecialityDao>(SpecialityTable)

    var name by SpecialityTable.name
    var duties by SpecialityTable.duties
}
