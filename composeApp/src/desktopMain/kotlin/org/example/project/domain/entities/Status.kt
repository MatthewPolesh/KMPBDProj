package org.example.project.domain.entities

data class Status(
    val id: Int,
    val startData: kotlinx.datetime.LocalDate,
    val endData: kotlinx.datetime.LocalDate,
    val reasonOfChange: String
)