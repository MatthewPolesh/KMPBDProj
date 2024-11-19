package org.example.project.di





import org.example.project.presentation.viewmodels.ActiveSubstanceReportViewModel
import org.example.project.presentation.viewmodels.ActiveSubstanceViewModel
import org.example.project.presentation.viewmodels.GOSTMedicineViewModel
import org.example.project.presentation.viewmodels.GOSTViewModel
import org.example.project.presentation.viewmodels.MedicalOfficerViewModel
import org.example.project.presentation.viewmodels.MedicinalFormReportViewModel
import org.example.project.presentation.viewmodels.MedicinalFormViewModel
import org.example.project.presentation.viewmodels.MedicineViewModel
import org.example.project.presentation.viewmodels.ReportMedicineViewModel
import org.example.project.presentation.viewmodels.ReportViewModel
import org.example.project.presentation.viewmodels.SpecialityViewModel
import org.example.project.presentation.viewmodels.StandardViewModel
import org.example.project.presentation.viewmodels.StatusViewModel
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::ActiveSubstanceReportViewModel)
    viewModelOf(::ActiveSubstanceViewModel)
    viewModelOf(::GOSTMedicineViewModel)
    viewModelOf(::GOSTViewModel)
    viewModelOf(::MedicalOfficerViewModel)
    viewModelOf(::MedicinalFormReportViewModel)
    viewModelOf(::MedicinalFormViewModel)
    viewModelOf(::MedicineViewModel)
    viewModelOf(::ReportMedicineViewModel)
    viewModelOf(::ReportViewModel)
    viewModelOf(::SpecialityViewModel)
    viewModelOf(::StandardViewModel)
    viewModelOf(::StatusViewModel)
}
