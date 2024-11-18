package org.example.project.data.database.tables

import org.jetbrains.exposed.dao.id.IntIdTable

object MedicinalFormReportTable : IntIdTable("MedicinalForm_Report") {
    val reportNumber = reference("ReportNumber", ReportTable)
    val reportMedicalOfficerId = reference("ReportMedicalOfficerID", MedicalOfficerTable)
    val medicinalFormId = reference("MedicinalFormID", MedicinalFormTable)
}
