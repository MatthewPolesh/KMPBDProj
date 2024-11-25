package org.example.project.data.database.dao

import org.example.project.data.database.tables.ActiveSubstanceTable
import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.id.EntityID

class ActiveSubstanceDao(id: EntityID<Int>) : Entity<Int>(id) {
    companion object : EntityClass<Int, ActiveSubstanceDao>(ActiveSubstanceTable)

    var composition by ActiveSubstanceTable.composition
    var appointment by ActiveSubstanceTable.appointment
    var name by ActiveSubstanceTable.name
    var medicalOfficerDao by MedicalOfficerDao referencedOn ActiveSubstanceTable.medicalOfficerId
}
