package org.example.project.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.example.project.domain.entities.Medicine
import org.example.project.domain.repositories.MedicineRepository

class MedicineViewModel(
    private val medicineRepository: MedicineRepository
) : ViewModel() {

    private val _medicines = MutableStateFlow<List<Medicine>>(emptyList())
    val medicines: StateFlow<List<Medicine>> = _medicines

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun fetchMedicines() {
        viewModelScope.launch {
            medicineRepository.getAll()
                .onSuccess { _medicines.value = it }
                .onFailure { _error.value = it.message }
            println(error.value)
        }
    }

    fun addMedicine(medicine: Medicine) {
        viewModelScope.launch {
            medicineRepository.add(medicine)
                .onSuccess { fetchMedicines() }
                .onFailure { _error.value = it.message }
        }
    }

    fun updateMedicine(medicine: Medicine) {
        viewModelScope.launch {
            medicineRepository.update(medicine)
                .onSuccess { fetchMedicines() }
                .onFailure { _error.value = it.message }
        }
    }

    fun deleteMedicine(id: Int) {
        viewModelScope.launch {
            medicineRepository.delete(id)
                .onSuccess { fetchMedicines() }
                .onFailure { _error.value = it.message }
        }
    }
}
