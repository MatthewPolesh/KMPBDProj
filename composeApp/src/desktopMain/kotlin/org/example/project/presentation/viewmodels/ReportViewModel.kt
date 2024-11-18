package org.example.project.presentation.viewmodels


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import org.example.project.domain.entities.Report
import org.example.project.domain.repositories.ReportRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ReportViewModel(
    private val reportRepository: ReportRepository
) : ViewModel() {

    private val _reports = MutableStateFlow<List<Report>>(emptyList())
    val reports: StateFlow<List<Report>> = _reports

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun fetchReports() {
        viewModelScope.launch {
            reportRepository.getAll()
                .onSuccess { _reports.value = it }
                .onFailure { _error.value = it.message }
        }
    }

    fun addReport(report: Report) {
        viewModelScope.launch {
            reportRepository.add(report)
                .onSuccess { fetchReports() }
                .onFailure { _error.value = it.message }
        }
    }

    fun updateReport(report: Report) {
        viewModelScope.launch {
            reportRepository.update(report)
                .onSuccess { fetchReports() }
                .onFailure { _error.value = it.message }
        }
    }

    fun deleteReport(id: Int) {
        viewModelScope.launch {
            reportRepository.delete(id)
                .onSuccess { fetchReports() }
                .onFailure { _error.value = it.message }
        }
    }
}
