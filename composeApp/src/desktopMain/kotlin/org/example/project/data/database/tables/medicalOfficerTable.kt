package org.example.project.data.database.tables

import org.jetbrains.exposed.dao.id.IdTable

object MedicalOfficerTable : IdTable<Int>("MedicalOfficer") {
    override val id = integer("OfficerID").autoIncrement().entityId()
    val firstName = varchar("FirstName", 255)
    val lastName = varchar("LastName", 255)
    val surname = varchar("Surname", 255)
    val email = varchar("Email", 255)
    val numberChild = integer("Number_Child").nullable()
    val age = integer("age").nullable()
    val workExperience = integer("WorkExperience").nullable()
    val specialityId = reference("SpecialityID", SpecialityTable)

    override val primaryKey = PrimaryKey(id, name = "PK_MedicalOfficer_ID")
}
