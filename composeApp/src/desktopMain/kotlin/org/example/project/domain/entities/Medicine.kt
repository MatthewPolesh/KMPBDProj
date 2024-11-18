package org.example.project.domain.entities

import kotlinx.datetime.LocalDate


data class Medicine(
    val id: Int,
    val producer: String,
    val name: String,
    val dateProduce: LocalDate,
    val activeSubstanceId: Int,
    val medicinalFormId: Int,
    val standardId: Int
)