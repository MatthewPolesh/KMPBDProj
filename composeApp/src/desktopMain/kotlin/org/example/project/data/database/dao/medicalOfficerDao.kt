package org.example.project.data.database.dao

import org.example.project.data.database.tables.MedicalOfficerTable
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class MedicalOfficerDao(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<MedicalOfficerDao>(MedicalOfficerTable)

    var firstName by MedicalOfficerTable.firstName
    var lastName by MedicalOfficerTable.lastName
    var surname by MedicalOfficerTable.surname
    var email by MedicalOfficerTable.email
    var workExperience by MedicalOfficerTable.workExperience
    var specialityDao by SpecialityDao referencedOn MedicalOfficerTable.specialityId
}
