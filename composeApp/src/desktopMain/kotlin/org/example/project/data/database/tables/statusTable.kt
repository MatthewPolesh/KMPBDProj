package org.example.project.data.database.tables

import org.jetbrains.exposed.dao.id.IdTable
import org.jetbrains.exposed.sql.kotlin.datetime.date

object StatusTable : IdTable<Int>("Status") {
    override val id = integer("StatusID").autoIncrement().entityId()
    override val primaryKey = PrimaryKey(id, name = "PK_Status_ID")
    val startData = date("StartData")
    val endData = date("EndData")
    val reasonOfChange = varchar("ReasonOfChange", 255)
}
