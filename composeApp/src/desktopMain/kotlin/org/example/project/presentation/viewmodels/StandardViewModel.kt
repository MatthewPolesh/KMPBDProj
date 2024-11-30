package org.example.project.presentation.viewmodels


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.example.project.domain.entities.Standard
import org.example.project.domain.repositories.StandardRepository

class StandardViewModel(
    private val standardRepository: StandardRepository
) : ViewModel() {

    private val _standards = MutableStateFlow<List<Standard>>(emptyList())
    val standards: StateFlow<List<Standard>> = _standards

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun fetchStandards() {
        viewModelScope.launch {
            standardRepository.getAll()
                .onSuccess { _standards.value = it }
                .onFailure {
                    _error.value = it.message
                    delay(1000)
                    _error.value = null
                }
        }
    }

    fun addStandard(standard: Standard) {
        viewModelScope.launch {
            standardRepository.add(standard)
                .onSuccess { fetchStandards() }
                .onFailure {
                    _error.value = it.message
                    delay(1000)
                    _error.value = null
                }
        }
    }

    fun updateStandard(standard: Standard) {
        viewModelScope.launch {
            standardRepository.update(standard)
                .onSuccess { fetchStandards() }
                .onFailure {
                    _error.value = it.message
                    delay(1000)
                    _error.value = null
                }
        }
    }

    fun deleteStandard(id: Int) {
        viewModelScope.launch {
            standardRepository.delete(id)
                .onSuccess { fetchStandards() }
                .onFailure {
                    _error.value = it.message
                    delay(1000)
                    _error.value = null
                }
        }
    }
}
