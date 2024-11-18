package org.example.project.data.database.tables

import org.jetbrains.exposed.dao.id.IntIdTable

object StandardTable : IntIdTable("Standard") {
    val name = varchar("Name", 255)
    val components = varchar("Components", 255)
}
