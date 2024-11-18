package org.example.project.data.database.dao

import org.example.project.data.database.tables.SpecialityTable
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class SpecialityDao(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<SpecialityDao>(SpecialityTable)

    var name by SpecialityTable.name
    var duties by SpecialityTable.duties
}
