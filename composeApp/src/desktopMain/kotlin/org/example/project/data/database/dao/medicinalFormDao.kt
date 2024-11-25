package org.example.project.data.database.dao

import org.example.project.data.database.tables.MedicinalFormTable
import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.id.EntityID

class MedicinalFormDao(id: EntityID<Int>) : Entity<Int>(id) {
    companion object : EntityClass<Int, MedicinalFormDao>(MedicinalFormTable)

    var composition by MedicinalFormTable.composition
    var name by MedicinalFormTable.name
    var medicalOfficerDao by MedicalOfficerDao referencedOn MedicinalFormTable.medicalOfficerId
}
