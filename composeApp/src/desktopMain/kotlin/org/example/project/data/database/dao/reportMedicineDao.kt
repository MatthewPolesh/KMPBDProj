package org.example.project.data.database.dao

import org.example.project.data.database.tables.ReportMedicineTable
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class ReportMedicineDao(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<ReportMedicineDao>(ReportMedicineTable)

    var reportDao by ReportDao referencedOn ReportMedicineTable.reportNumber
    var medicalOfficerDao by MedicalOfficerDao referencedOn ReportMedicineTable.reportMedicalOfficerId
    var medicineDao by MedicineDao referencedOn ReportMedicineTable.medicineId
}
