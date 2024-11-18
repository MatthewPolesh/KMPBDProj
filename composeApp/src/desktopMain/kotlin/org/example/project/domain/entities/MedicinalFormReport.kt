package org.example.project.domain.entities

data class MedicinalFormReport(
    val id: Int,
    val reportId: Int,
    val reportMedicalOfficerId: Int,
    val medicinalFormId: Int
)