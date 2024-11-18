package org.example.project.domain.entities

data class Report(
    val id: Int,
    val name: String,
    val date: kotlinx.datetime.LocalDate,
    val medicalOfficerId: Int
)