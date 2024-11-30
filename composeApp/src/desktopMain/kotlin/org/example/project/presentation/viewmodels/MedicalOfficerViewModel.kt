package org.example.project.presentation.viewmodels


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.example.project.domain.entities.MedicalOfficer
import org.example.project.domain.repositories.MedicalOfficerRepository

class MedicalOfficerViewModel(
    private val medicalOfficerRepository: MedicalOfficerRepository
) : ViewModel() {

    private val _medicalOfficers = MutableStateFlow<List<MedicalOfficer>>(emptyList())
    val medicalOfficers: StateFlow<List<MedicalOfficer>> = _medicalOfficers

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun fetchMedicalOfficers() {
        viewModelScope.launch {
            medicalOfficerRepository.getAll()
                .onSuccess { _medicalOfficers.value = it }
                .onFailure {
                    _error.value = it.message
                    delay(1000)
                    _error.value = null
                }
        }
    }

    fun addMedicalOfficer(medicalOfficer: MedicalOfficer) {
        viewModelScope.launch {
            medicalOfficerRepository.add(medicalOfficer)
                .onSuccess { fetchMedicalOfficers() }
                .onFailure {
                    _error.value = it.message
                    delay(1000)
                    _error.value = null
                }
        }
    }

    fun updateMedicalOfficer(medicalOfficer: MedicalOfficer) {
        viewModelScope.launch {
            medicalOfficerRepository.update(medicalOfficer)
                .onSuccess { fetchMedicalOfficers() }
                .onFailure {
                    _error.value = it.message
                    delay(1000)
                    _error.value = null
                }
        }
    }

    fun deleteMedicalOfficer(id: Int) {
        viewModelScope.launch {
            medicalOfficerRepository.delete(id)
                .onSuccess { fetchMedicalOfficers() }
                .onFailure {
                    _error.value = it.message
                    delay(1000)
                    _error.value = null
                }
        }
    }
}
