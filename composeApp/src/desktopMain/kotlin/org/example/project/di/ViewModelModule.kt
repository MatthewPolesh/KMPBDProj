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
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { ActiveSubstanceReportViewModel(get()) }
    viewModel { ActiveSubstanceViewModel(get()) }
    viewModel { GOSTMedicineViewModel(get()) }
    viewModel { GOSTViewModel(get()) }
    viewModel { MedicalOfficerViewModel(get()) }
    viewModel { MedicinalFormReportViewModel(get())}
    viewModel { MedicinalFormViewModel(get()) }
    viewModel { MedicineViewModel(get()) }
    viewModel { ReportMedicineViewModel(get())}
    viewModel { ReportViewModel(get()) }
    viewModel { SpecialityViewModel(get()) }
    viewModel { StandardViewModel(get()) }
    viewModel { StatusViewModel(get()) }
}
