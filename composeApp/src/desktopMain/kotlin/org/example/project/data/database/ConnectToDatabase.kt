package org.example.project.data.database

import org.jetbrains.exposed.sql.Database


fun connectToDatabase(){
    try {
        Database.connect(url = "jdbc:mysql://192.168.0.112:3306/medicine", driver = "com.mysql.cj.jdbc.Driver", user = "vika", password = "12345678" )
        println("Connected to DB")

    }catch (e:Exception)
    {
        println("Connection error: ${e.message}")
    }

}


