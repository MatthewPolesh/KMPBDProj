package org.example.project.presentation.viewmodels


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.example.project.domain.entities.MedicinalForm
import org.example.project.domain.repositories.MedicinalFormRepository

class MedicinalFormViewModel(
    private val medicinalFormRepository: MedicinalFormRepository
) : ViewModel() {

    private val _medicinalForms = MutableStateFlow<List<MedicinalForm>>(emptyList())
    val medicinalForms: StateFlow<List<MedicinalForm>> = _medicinalForms

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun fetchMedicinalForms() {
        viewModelScope.launch {
            medicinalFormRepository.getAll()
                .onSuccess { _medicinalForms.value = it }
                .onFailure {
                    _error.value = it.message
                    delay(1000)
                    _error.value = null
                }
        }
    }

    fun addMedicinalForm(medicinalForm: MedicinalForm) {
        viewModelScope.launch {
            medicinalFormRepository.add(medicinalForm)
                .onSuccess { fetchMedicinalForms() }
                .onFailure {
                    _error.value = it.message
                    delay(1000)
                    _error.value = null
                }
        }
    }

    fun updateMedicinalForm(medicinalForm: MedicinalForm) {
        viewModelScope.launch {
            medicinalFormRepository.update(medicinalForm)
                .onSuccess { fetchMedicinalForms() }
                .onFailure {
                    _error.value = it.message
                    delay(1000)
                    _error.value = null
                }
        }
    }

    fun deleteMedicinalForm(id: Int) {
        viewModelScope.launch {
            medicinalFormRepository.delete(id)
                .onSuccess { fetchMedicinalForms() }
                .onFailure {
                    _error.value = it.message
                    delay(1000)
                    _error.value = null
                }
        }
    }
}
