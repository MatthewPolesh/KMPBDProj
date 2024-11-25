package org.example.project.data.database.tables

import org.jetbrains.exposed.dao.id.IdTable

object StandardTable : IdTable<Int>("Standard") {
    override val id = integer("StandardID").autoIncrement().entityId()
    override val primaryKey = PrimaryKey(id, name = "PK_Standard_ID")
    val name = varchar("Name", 255)
    val components = varchar("Components", 255)
}
