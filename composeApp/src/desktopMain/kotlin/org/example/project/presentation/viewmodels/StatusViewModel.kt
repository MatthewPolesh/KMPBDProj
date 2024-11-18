package org.example.project.presentation.viewmodels


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import org.example.project.domain.entities.Status
import org.example.project.domain.repositories.StatusRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class StatusViewModel(
    private val statusRepository: StatusRepository
) : ViewModel() {

    private val _statuses = MutableStateFlow<List<Status>>(emptyList())
    val statuses: StateFlow<List<Status>> = _statuses

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun fetchStatuses() {
        viewModelScope.launch {
            statusRepository.getAll()
                .onSuccess { _statuses.value = it }
                .onFailure { _error.value = it.message }
        }
    }

    fun addStatus(status: Status) {
        viewModelScope.launch {
            statusRepository.add(status)
                .onSuccess { fetchStatuses() }
                .onFailure { _error.value = it.message }
        }
    }

    fun updateStatus(status: Status) {
        viewModelScope.launch {
            statusRepository.update(status)
                .onSuccess { fetchStatuses() }
                .onFailure { _error.value = it.message }
        }
    }

    fun deleteStatus(id: Int) {
        viewModelScope.launch {
            statusRepository.delete(id)
                .onSuccess { fetchStatuses() }
                .onFailure { _error.value = it.message }
        }
    }
}
