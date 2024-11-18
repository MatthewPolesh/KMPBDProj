package org.example.project.domain.entities

data class ReportMedicine(
    val id: Int,
    val reportId: Int,
    val reportMedicalOfficerId: Int,
    val medicineId: Int
)