package org.example.project.presentation.viewmodels


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.example.project.domain.entities.Status
import org.example.project.domain.repositories.StatusRepository

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
                .onFailure {
                    _error.value = it.message
                    delay(1000)
                    _error.value = null
                }
        }
    }

    fun addStatus(status: Status) {
        viewModelScope.launch {
            statusRepository.add(status)
                .onSuccess { fetchStatuses() }
                .onFailure {
                    _error.value = it.message
                    delay(1000)
                    _error.value = null
                }
        }
    }

    fun updateStatus(status: Status) {
        viewModelScope.launch {
            statusRepository.update(status)
                .onSuccess { fetchStatuses() }
                .onFailure {
                    _error.value = it.message
                    delay(1000)
                    _error.value = null
                }
        }
    }

    fun deleteStatus(id: Int) {
        viewModelScope.launch {
            statusRepository.delete(id)
                .onSuccess { fetchStatuses() }
                .onFailure {
                    _error.value = it.message
                    delay(1000)
                    _error.value = null
                }
        }
    }
}
