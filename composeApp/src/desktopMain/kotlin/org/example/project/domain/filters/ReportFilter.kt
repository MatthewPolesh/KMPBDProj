package org.example.project.domain.filters

import kotlinx.datetime.LocalDate

data class ReportFilter(
    val id: Int? = null,
    val name: String = "",
    val date: LocalDate? = null,
    val medicalOfficerId: Int
)
