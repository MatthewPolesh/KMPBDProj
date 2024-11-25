package org.example.project.data.database.tables

import org.jetbrains.exposed.dao.id.IdTable
import org.jetbrains.exposed.sql.kotlin.datetime.date

object ReportTable : IdTable<Int>("Report") {
    override val id = ReportTable.integer("Number").autoIncrement().entityId()
    val name = varchar("Name", 255)
    val date = date("Date")
    val medicalOfficerId = reference("MedicalOfficerID", MedicalOfficerTable)
    override val primaryKey = PrimaryKey(GOSTTable.id, name = "PK_Report_Number")
}
