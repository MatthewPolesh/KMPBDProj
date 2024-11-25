package org.example.project.data.database.tables

import org.jetbrains.exposed.dao.id.IdTable

object ActiveSubstanceTable : IdTable<Int>("ActiveSubstance") {
    override val id = integer("ActiveSubstanceID").autoIncrement().entityId()
    val composition = varchar("Composition", 255)
    val appointment = varchar("Appointment", 255)
    val name = varchar("Name", 255)
    val medicalOfficerId = reference("MedicalOfficerID", MedicalOfficerTable)
    override val primaryKey = PrimaryKey(id, name = "PK_ActiveSubstance_ID")
}
