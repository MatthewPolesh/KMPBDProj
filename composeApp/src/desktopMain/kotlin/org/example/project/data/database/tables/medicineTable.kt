package org.example.project.data.database.tables

import org.jetbrains.exposed.dao.id.IdTable
import org.jetbrains.exposed.sql.kotlin.datetime.date

object MedicineTable : IdTable<Int>("Medicine") {
    override val id = integer("MedicineID").autoIncrement().entityId()
    val producer = varchar("Producer", 255)
    val name = varchar("Name", 255)
    val dateProduce = date("DataProduce")
    val dosage = integer("Dosage")
    val activeSubstanceId = reference("ActiveSubstanceID", ActiveSubstanceTable)
    val medicinalFormId = reference("MedicinalFormID", MedicinalFormTable)
    val standardId = reference("StandardID", StandardTable)
    override val primaryKey = PrimaryKey(id, name = "PK_Medicine_ID")
}
