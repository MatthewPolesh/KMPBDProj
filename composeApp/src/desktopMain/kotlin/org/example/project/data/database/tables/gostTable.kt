package org.example.project.data.database.tables

import org.jetbrains.exposed.dao.id.IntIdTable

object GOSTTable : IntIdTable("GOST") {
    val name = varchar("Name", 255)
}
