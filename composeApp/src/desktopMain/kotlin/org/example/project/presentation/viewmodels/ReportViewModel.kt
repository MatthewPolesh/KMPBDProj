package org.example.project.presentation.viewmodels


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.example.project.domain.entities.Report
import org.example.project.domain.filters.ReportFilter
import org.example.project.domain.repositories.ReportRepository

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
                .onFailure {
                    _error.value = it.message
                    delay(1000)
                    _error.value = null
                }
        }
    }

    fun addReport(report: Report) {
        viewModelScope.launch {
            reportRepository.add(report)
                .onSuccess { fetchReports() }
                .onFailure {
                    _error.value = it.message
                    delay(1000)
                    _error.value = null
                }
        }
    }

    fun updateReport(report: Report) {
        viewModelScope.launch {
            reportRepository.update(report)
                .onSuccess { fetchReports() }
                .onFailure {
                    _error.value = it.message
                    delay(1000)
                    _error.value = null
                }
        }
    }

    fun deleteReport(id: Int) {
        viewModelScope.launch {
            reportRepository.delete(id)
                .onSuccess { fetchReports() }
                .onFailure {
                    _error.value = it.message
                    delay(1000)
                    _error.value = null
                }
        }
    }

    fun fetchFilteredReports(filter: ReportFilter){
        viewModelScope.launch {
            reportRepository.getReports(filter)
                .onSuccess{
                    _reports.value = it
                }
                .onFailure{
                    _error.value = it.message
                    delay(1000)
                    _error.value = null
                }

        }
    }
}
