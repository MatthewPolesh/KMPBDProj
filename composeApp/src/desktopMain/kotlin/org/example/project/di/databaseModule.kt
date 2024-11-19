package org.example.project.di

import org.jetbrains.exposed.sql.Database
import org.koin.dsl.module

val databaseModule = module{
    single<Database> {
        Database.connect(
            url = "jdbc:mysql://192.168.0.108:3306/mydb",
            driver = "com.mysql.cj.jdbc.Driver",
            user = "vika",
            password = "1234567890"
        )
    }
}