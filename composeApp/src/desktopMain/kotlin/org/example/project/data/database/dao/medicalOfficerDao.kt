package org.example.project.data.database.dao

import org.example.project.data.database.tables.MedicalOfficerTable
import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.id.EntityID

class MedicalOfficerDao(id: EntityID<Int>) : Entity<Int>(id) {
    companion object : EntityClass<Int, MedicalOfficerDao>(MedicalOfficerTable)

    var firstName by MedicalOfficerTable.firstName
    var lastName by MedicalOfficerTable.lastName
    var surname by MedicalOfficerTable.surname
    var email by MedicalOfficerTable.email
    var age by MedicalOfficerTable.age
    var numberChild by MedicalOfficerTable.numberChild
    var workExperience by MedicalOfficerTable.workExperience
    var specialityDao by SpecialityDao referencedOn MedicalOfficerTable.specialityId
}
