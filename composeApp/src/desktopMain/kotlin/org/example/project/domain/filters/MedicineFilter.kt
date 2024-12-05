package org.example.project.domain.filters

import kotlinx.datetime.LocalDate

data class MedicineFilter(
    val id: Int? = null,
    val producer: String = "",
    val name: String = "",
    val dosage: Int? = null,
    val dateProduce: LocalDate? = null,
    val activeSubstanceId: Int? = null,
    val activeSubstanceName: String = "",
    val medicinalFormId: Int? = null,
    val medicinalFormName: String = "",
    val standardId: Int? = null,
    val standardName: String = ""
)
