package org.example.project.presentation.viewmodels


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import org.example.project.domain.entities.ActiveSubstance
import org.example.project.domain.repositories.ActiveSubstanceRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ActiveSubstanceViewModel(
    private val activeSubstanceRepository: ActiveSubstanceRepository
) : ViewModel() {

    private val _activeSubstances = MutableStateFlow<List<ActiveSubstance>>(emptyList())
    val activeSubstances: StateFlow<List<ActiveSubstance>> = _activeSubstances

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun fetchActiveSubstances() {
        viewModelScope.launch {
            activeSubstanceRepository.getAll()
                .onSuccess { _activeSubstances.value = it }
                .onFailure { _error.value = it.message }
        }
    }

    fun addActiveSubstance(activeSubstance: ActiveSubstance) {
        viewModelScope.launch {
            activeSubstanceRepository.add(activeSubstance)
                .onSuccess { fetchActiveSubstances() }
                .onFailure { _error.value = it.message }
        }
    }

    fun updateActiveSubstance(activeSubstance: ActiveSubstance) {
        viewModelScope.launch {
            activeSubstanceRepository.update(activeSubstance)
                .onSuccess { fetchActiveSubstances() }
                .onFailure { _error.value = it.message }
        }
    }

    fun deleteActiveSubstance(id: Int) {
        viewModelScope.launch {
            activeSubstanceRepository.delete(id)
                .onSuccess { fetchActiveSubstances() }
                .onFailure { _error.value = it.message }
        }
    }
}