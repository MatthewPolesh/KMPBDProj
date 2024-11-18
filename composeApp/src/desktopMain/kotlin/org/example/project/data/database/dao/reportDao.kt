package org.example.project.data.database.dao

import org.example.project.data.database.tables.ReportTable
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class ReportDao(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<ReportDao>(ReportTable)

    var name by ReportTable.name
    var date by ReportTable.date
    var medicalOfficerDao by MedicalOfficerDao referencedOn ReportTable.medicalOfficerId
}
