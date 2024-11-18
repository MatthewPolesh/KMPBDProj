package org.example.project.data.database.tables
import org.jetbrains.exposed.dao.id.IntIdTable

object SpecialityTable : IntIdTable("Speciality") {
    val name = varchar("Name", 255)
    val duties = varchar("Duties", 255)
}
