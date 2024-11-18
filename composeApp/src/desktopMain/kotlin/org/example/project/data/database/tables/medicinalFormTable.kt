package org.example.project.data.database.tables

import org.jetbrains.exposed.dao.id.IntIdTable

object MedicinalFormTable : IntIdTable("MedicinalForm") {
    val composition = varchar("Composition", 255)
    val name = varchar("Name", 255)
    val medicalOfficerId = reference("MedicalOfficerID", MedicalOfficerTable)
}
