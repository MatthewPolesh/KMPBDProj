package org.example.project.data.database.dao

import org.example.project.data.database.tables.MedicinalFormTable
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class MedicinalFormDao(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<MedicinalFormDao>(MedicinalFormTable)

    var composition by MedicinalFormTable.composition
    var name by MedicinalFormTable.name
    var medicalOfficerDao by MedicalOfficerDao referencedOn MedicinalFormTable.medicalOfficerId
}
