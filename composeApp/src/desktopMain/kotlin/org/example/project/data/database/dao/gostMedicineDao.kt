package org.example.project.data.database.dao

import org.example.project.data.database.tables.GOSTMedicineTable
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class GOSTMedicineDao(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<GOSTMedicineDao>(GOSTMedicineTable)

    var gostDao by GOSTDao referencedOn GOSTMedicineTable.gostNumber
    var medicineDao by MedicineDao referencedOn GOSTMedicineTable.medicineId
}
