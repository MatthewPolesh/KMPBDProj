package org.example.project.data.database.tables

import org.jetbrains.exposed.dao.id.IdTable

object MedicinalFormTable : IdTable<Int>("MedicinalForm") {
    override val id = integer("MedicinalFormID").autoIncrement().entityId()
    val composition = varchar("Composition", 255)
    val name = varchar("Name", 255)
    val medicalOfficerId = reference("MedicalOfficerID", MedicalOfficerTable)
    override val primaryKey = PrimaryKey(id, name = "PK_MedicinalForm_ID")
}
