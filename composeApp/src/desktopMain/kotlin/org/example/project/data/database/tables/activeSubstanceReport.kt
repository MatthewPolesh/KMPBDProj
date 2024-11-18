package org.example.project.data.database.tables

import org.jetbrains.exposed.dao.id.IntIdTable

object ActiveSubstanceReportTable : IntIdTable("ActiveSubstance_Report") {
    val activeSubstanceId = reference("ActiveSubstanceID", ActiveSubstanceTable)
    val reportNumber = reference("ReportNumber", ReportTable)
    val reportMedicalOfficerId = reference("ReportMedicalOfficerID", MedicalOfficerTable)
}
