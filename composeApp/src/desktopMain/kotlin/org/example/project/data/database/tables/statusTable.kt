package org.example.project.data.database.tables

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.kotlin.datetime.date

object StatusTable : IntIdTable("Status") {
    val startData = date("StartData")
    val endData = date("EndData")
    val reasonOfChange = varchar("ReasonOfChange", 255)
}
