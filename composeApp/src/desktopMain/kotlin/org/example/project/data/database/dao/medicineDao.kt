package org.example.project.data.database.dao

import org.example.project.data.database.tables.MedicineTable
import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.id.EntityID

class MedicineDao(id: EntityID<Int>) : Entity<Int>(id) {
    companion object : EntityClass<Int,MedicineDao>(MedicineTable)
    var producer by MedicineTable.producer
    var name by MedicineTable.name
    var dateProduce by MedicineTable.dateProduce
    var dosage by MedicineTable.dosage
    var activeSubstanceDao by ActiveSubstanceDao referencedOn MedicineTable.activeSubstanceId
    var medicinalFormDao by MedicinalFormDao referencedOn MedicineTable.medicinalFormId
    var standardDao by StandardDao referencedOn MedicineTable.standardId
}
