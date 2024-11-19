package org.example.project.data.database.tables

import org.jetbrains.exposed.dao.id.IdTable

object GOSTTable : IdTable<Int>("GOST") {
    // Определяем столбец 'Number' как первичный ключ
    override val id = integer("Number").autoIncrement().entityId()
    val name = varchar("Name", 255).uniqueIndex()

    // Указываем первичный ключ
    override val primaryKey = PrimaryKey(id, name = "PK_GOST_Number")
}
