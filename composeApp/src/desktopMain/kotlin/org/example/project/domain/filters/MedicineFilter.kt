package org.example.project.domain.filters

import kotlinx.datetime.LocalDate

data class MedicineFilter(
    val id: Int? = null,
    val producer: String? = null,
    val name: String? = null,
    val dosage: Int? = null,
    val dateProduce: LocalDate? = null,
    val activeSubstanceId: Int? = null,
    val activeSubstanceName: String? = null,
    val medicinalFormId: Int? = null,
    val medicinalFormName: String? = null,
    val standardId: Int? = null,
    val standardName: String? = null
)
