package org.example.project.domain.entities


data class MedicinalForm(
    val id: Int,
    val name: String,
    val composition: String,
    val medicalOfficerId: Int
)