package org.example.project.data.database.tables

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.ReferenceOption

object ActiveSubstanceReportTable : IntIdTable("ActiveSubstance_Report") {
    val activeSubstanceId = reference("ActiveSubstanceID", ActiveSubstanceTable, onDelete = ReferenceOption.CASCADE)
    val reportNumber = reference("ReportNumber", ReportTable, onDelete = ReferenceOption.CASCADE)
    val reportMedicalOfficerId = reference("ReportMedicalOfficerID", MedicalOfficerTable, onDelete = ReferenceOption.CASCADE)
}
