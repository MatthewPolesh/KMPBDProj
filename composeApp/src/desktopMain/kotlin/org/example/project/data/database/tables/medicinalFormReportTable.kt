package org.example.project.data.database.tables

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.ReferenceOption

object MedicinalFormReportTable : IntIdTable("MedicinalForm_Report") {
    val reportNumber = reference("ReportNumber", ReportTable, onDelete = ReferenceOption.CASCADE)
    val reportMedicalOfficerId = reference("ReportMedicalOfficerID", MedicalOfficerTable, onDelete = ReferenceOption.CASCADE)
    val medicinalFormId = reference("MedicinalFormID", MedicinalFormTable, onDelete = ReferenceOption.CASCADE)
}
