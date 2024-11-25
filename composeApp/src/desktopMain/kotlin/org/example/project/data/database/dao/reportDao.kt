package org.example.project.data.database.dao

import org.example.project.data.database.tables.ReportTable
import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.id.EntityID

class ReportDao(id: EntityID<Int>) : Entity<Int>(id) {
    companion object : EntityClass<Int, ReportDao>(ReportTable)

    var name by ReportTable.name
    var date by ReportTable.date
    var medicalOfficerDao by MedicalOfficerDao referencedOn ReportTable.medicalOfficerId

}
