package org.example.project.domain.entities

import kotlinx.datetime.LocalDate


data class Medicine(
    val id: Int,
    val producer: String,
    val name: String,
    val dosage: Int,
    val dateProduce: LocalDate,
    val activeSubstanceId: Int,
    val activeSubstanceName: String,
    val medicinalFormId: Int,
    val medicinalFormName: String,
    val standardId: Int,
    val standardName: String
)