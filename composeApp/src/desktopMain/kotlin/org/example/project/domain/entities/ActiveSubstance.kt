package org.example.project.domain.entities

data class ActiveSubstance(
    val id: Int,
    val name: String,
    val composition: String,
    val appointment: String,
    val medicalOfficerId: Int
)