package org.example.project.data.database.tables

import org.jetbrains.exposed.dao.id.IntIdTable

object ReportMedicineTable : IntIdTable("Report_Medicine") {
    val reportNumber = reference("ReportNumber", ReportTable)
    val reportMedicalOfficerId = reference("ReportMedicalOfficerID", MedicalOfficerTable)
    val medicineId = reference("MedicineID", MedicineTable)
}
