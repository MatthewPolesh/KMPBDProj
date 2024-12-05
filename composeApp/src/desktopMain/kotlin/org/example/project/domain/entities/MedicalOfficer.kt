package org.example.project.domain.entities

data class MedicalOfficer(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val surname: String,
    val age: Int,
    val numberChild: Int,
    val email: String,
    val workExperience: Int,
    val bonus: Int = 0,
    val specialityId: Int
)