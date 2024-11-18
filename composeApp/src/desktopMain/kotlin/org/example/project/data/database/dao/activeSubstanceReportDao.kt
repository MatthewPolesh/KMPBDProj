package org.example.project.data.database.dao

import org.example.project.data.database.tables.ActiveSubstanceReportTable
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class ActiveSubstanceReportDao(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<ActiveSubstanceReportDao>(ActiveSubstanceReportTable)

    var activeSubstanceDao by ActiveSubstanceDao referencedOn ActiveSubstanceReportTable.activeSubstanceId
    var reportDao by ReportDao referencedOn ActiveSubstanceReportTable.reportNumber
    var medicalOfficerDao by MedicalOfficerDao referencedOn ActiveSubstanceReportTable.reportMedicalOfficerId
}
