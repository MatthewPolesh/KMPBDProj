package org.example.project.data.database.tables

import org.jetbrains.exposed.dao.id.IntIdTable

object GOSTMedicineTable : IntIdTable("GOST_Medicine") {
    val gostNumber = reference("GOSTNumber", GOSTTable)
    val medicineId = reference("MedicineID", MedicineTable)
}
