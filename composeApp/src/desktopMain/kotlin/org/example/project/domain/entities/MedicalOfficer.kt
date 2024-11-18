package org.example.project.domain.entities

data class MedicalOfficer(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val surname: String,
    val email: String,
    val workExperience: Int,
    val specialityId: Int
)