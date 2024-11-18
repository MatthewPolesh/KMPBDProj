package org.example.project.data.database.dao

import org.example.project.data.database.tables.MedicineTable
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class MedicineDao(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<MedicineDao>(MedicineTable)

    var producer by MedicineTable.producer
    var name by MedicineTable.name
    var dateProduce by MedicineTable.dateProduce
    var activeSubstanceDao by ActiveSubstanceDao referencedOn MedicineTable.activeSubstanceId
    var medicinalFormDao by MedicinalFormDao referencedOn MedicineTable.medicinalFormId
    var standardDao by StandardDao referencedOn MedicineTable.standardId
}
