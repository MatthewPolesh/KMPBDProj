package org.example.project.di

import org.example.project.presentation.viewmodels.ActiveSubstanceViewModel
import org.example.project.presentation.viewmodels.GOSTViewModel
import org.example.project.presentation.viewmodels.MedicalOfficerViewModel
import org.example.project.presentation.viewmodels.MedicinalFormViewModel
import org.example.project.presentation.viewmodels.MedicineViewModel
import org.example.project.presentation.viewmodels.ReportViewModel
import org.example.project.presentation.viewmodels.SpecialityViewModel
import org.example.project.presentation.viewmodels.StandardViewModel
import org.example.project.presentation.viewmodels.StatusViewModel
import org.example.project.presentation.viewmodels.UserViewModel
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::ActiveSubstanceViewModel)
    viewModelOf(::GOSTViewModel)
    viewModelOf(::MedicalOfficerViewModel)
    viewModelOf(::MedicinalFormViewModel)
    viewModelOf(::MedicineViewModel)
    viewModelOf(::ReportViewModel)
    viewModelOf(::SpecialityViewModel)
    viewModelOf(::StandardViewModel)
    viewModelOf(::StatusViewModel)
    viewModelOf(::UserViewModel)
}
