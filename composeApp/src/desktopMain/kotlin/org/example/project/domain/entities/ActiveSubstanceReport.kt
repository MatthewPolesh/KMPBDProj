package org.example.project.domain.entities

data class ActiveSubstanceReport(
    val id: Int,
    val activeSubstanceId: Int,
    val reportId: Int,
    val reportMedicalOfficerId: Int
)