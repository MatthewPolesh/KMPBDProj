package org.example.project.data.database.tables
import org.jetbrains.exposed.dao.id.IdTable

object SpecialityTable : IdTable<Int>("Speciality") {
    override val id = integer("SpecialityID").autoIncrement().entityId()
    override val primaryKey = PrimaryKey(GOSTTable.id, name = "PK_Speciality_ID")
    val name = varchar("Name", 255)
    val duties = varchar("Duties", 255)
}
