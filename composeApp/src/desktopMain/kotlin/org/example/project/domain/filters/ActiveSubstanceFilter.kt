package org.example.project.domain.filters

data class ActiveSubstanceFilter(
    val id: Int? = null,
    val name: String = "",
    val composition: String = "",
    val appointment: String = "",
    val medicalOfficerId: Int? = null
)