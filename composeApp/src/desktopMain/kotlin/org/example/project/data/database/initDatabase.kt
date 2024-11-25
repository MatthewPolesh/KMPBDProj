package org.example.project.data.database

import org.example.project.data.database.tables.GOSTTable
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction


fun initDatabase(database: Database) {
    transaction(database) {
        SchemaUtils.create(
            GOSTTable,
        )
    }

}


