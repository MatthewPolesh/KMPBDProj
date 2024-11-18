package org.example.project.data.database.tables

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.kotlin.datetime.date

object MedicineTable : IntIdTable("Medicine") {
    val producer = varchar("Producer", 255)
    val name = varchar("Name", 255)
    val dateProduce = date("DateProduce")
    val activeSubstanceId = reference("ActiveSubstanceID", ActiveSubstanceTable)
    val medicinalFormId = reference("MedicinalFormID", MedicinalFormTable)
    val standardId = reference("StandardID", StandardTable)
}
