package org.example.project.di

import org.jetbrains.exposed.sql.Database
import org.koin.dsl.module

val databaseModule = module{
    single<Database> {
        Database.connect(
            url = "jdbc:mysql://26.15.95.41:3306/medicines",
            driver = "com.mysql.cj.jdbc.Driver",
            user = "vika",
            password = "1111"
        )
    }
}