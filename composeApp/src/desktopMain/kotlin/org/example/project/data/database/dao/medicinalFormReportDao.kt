package org.example.project.data.database.dao

import org.example.project.data.database.tables.MedicinalFormReportTable
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class MedicinalFormReportDao(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<MedicinalFormReportDao>(MedicinalFormReportTable)

    var reportDao by ReportDao referencedOn MedicinalFormReportTable.reportNumber
    var medicalOfficerDao by MedicalOfficerDao referencedOn MedicinalFormReportTable.reportMedicalOfficerId
    var medicinalFormDao by MedicinalFormDao referencedOn MedicinalFormReportTable.medicinalFormId
}
