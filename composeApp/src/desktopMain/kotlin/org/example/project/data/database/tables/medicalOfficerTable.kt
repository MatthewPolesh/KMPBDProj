package org.example.project.data.database.tables

import org.jetbrains.exposed.dao.id.IntIdTable

object MedicalOfficerTable : IntIdTable("MedicalOfficer") {
    val firstName = varchar("FirstName", 255)
    val lastName = varchar("LastName", 255)
    val surname = varchar("Surname", 255)
    val email = varchar("Email", 255)
    val workExperience = integer("WorkExperience")
    val specialityId = reference("SpecialityID", SpecialityTable)
}
