package org.example.project.presentation.viewmodels


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import org.example.project.domain.entities.MedicalOfficer
import org.example.project.domain.repositories.MedicalOfficerRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

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
                .onFailure { _error.value = it.message }
        }
    }

    fun addMedicalOfficer(medicalOfficer: MedicalOfficer) {
        viewModelScope.launch {
            medicalOfficerRepository.add(medicalOfficer)
                .onSuccess { fetchMedicalOfficers() }
                .onFailure { _error.value = it.message }
        }
    }

    fun updateMedicalOfficer(medicalOfficer: MedicalOfficer) {
        viewModelScope.launch {
            medicalOfficerRepository.update(medicalOfficer)
                .onSuccess { fetchMedicalOfficers() }
                .onFailure { _error.value = it.message }
        }
    }

    fun deleteMedicalOfficer(id: Int) {
        viewModelScope.launch {
            medicalOfficerRepository.delete(id)
                .onSuccess { fetchMedicalOfficers() }
                .onFailure { _error.value = it.message }
        }
    }
}
