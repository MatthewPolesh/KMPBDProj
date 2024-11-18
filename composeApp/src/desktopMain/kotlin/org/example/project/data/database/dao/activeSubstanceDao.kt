package org.example.project.data.database.dao

import org.example.project.data.database.tables.ActiveSubstanceTable
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class ActiveSubstanceDao(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<ActiveSubstanceDao>(ActiveSubstanceTable)

    var composition by ActiveSubstanceTable.composition
    var appointment by ActiveSubstanceTable.appointment
    var name by ActiveSubstanceTable.name
    var medicalOfficerDao by MedicalOfficerDao referencedOn ActiveSubstanceTable.medicalOfficerId
}
