package org.example.project.presentation.viewmodels


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.example.project.domain.entities.GOST
import org.example.project.domain.repositories.GOSTRepository

class GOSTViewModel(
    private val gostRepository: GOSTRepository
) : ViewModel() {

    private val _gosts = MutableStateFlow<List<GOST>>(emptyList())
    val gosts: StateFlow<List<GOST>> = _gosts

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun fetchGOSTs() {
        viewModelScope.launch {
            gostRepository.getAll()
                .onSuccess {
                    _gosts.value = it
                }
                .onFailure {
                    _error.value = it.message
                    delay(1000)
                    _error.value = null
                }
        }
    }

    fun addGOST(gost: GOST) {
        viewModelScope.launch {
            gostRepository.add(gost)
                .onSuccess { fetchGOSTs() }
                .onFailure {
                    _error.value = it.message
                    delay(1000)
                    _error.value = null }
        }
    }

    fun updateGOST(gost: GOST) {
        viewModelScope.launch {
            gostRepository.update(gost)
                .onSuccess { fetchGOSTs() }
                .onFailure {
                    _error.value = it.message
                    delay(1000)
                    _error.value = null
                }
        }
    }

    fun deleteGOST(id: Int) {
        viewModelScope.launch {
            gostRepository.delete(id)
                .onSuccess { fetchGOSTs() }
                .onFailure {
                    _error.value = it.message
                    delay(1000)
                    _error.value = null
                }
        }
    }
}
