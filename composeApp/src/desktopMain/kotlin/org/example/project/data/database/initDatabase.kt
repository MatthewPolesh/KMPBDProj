package org.example.project.data.database

import org.example.project.data.database.tables.ActiveSubstanceTable
import org.example.project.data.database.tables.GOSTTable
import org.example.project.data.database.tables.MedicalOfficerTable
import org.example.project.data.database.tables.MedicinalFormTable
import org.example.project.data.database.tables.MedicineTable
import org.example.project.data.database.tables.ReportTable
import org.example.project.data.database.tables.SpecialityTable
import org.example.project.data.database.tables.StandardTable
import org.example.project.data.database.tables.StatusTable
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction


fun initDatabase(database: Database) {
    transaction(database) {
        SchemaUtils.create(
            GOSTTable,
            ActiveSubstanceTable,
            MedicalOfficerTable,
            MedicinalFormTable,
            MedicineTable,
            ReportTable,
            SpecialityTable,
            StandardTable,
            StatusTable
        )
    }
}


