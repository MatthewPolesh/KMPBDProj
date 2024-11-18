package org.example.project.data.database.tables

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.kotlin.datetime.date

object ReportTable : IntIdTable("Report") {
    val name = varchar("Name", 255)
    val date = date("Date")
    val medicalOfficerId = reference("MedicalOfficerID", MedicalOfficerTable)
}
