package org.example.project.presentation.viewmodels


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.example.project.domain.entities.Speciality
import org.example.project.domain.repositories.SpecialityRepository

class SpecialityViewModel(
    private val specialityRepository: SpecialityRepository
) : ViewModel() {

    private val _specialities = MutableStateFlow<List<Speciality>>(emptyList())
    val specialities: StateFlow<List<Speciality>> = _specialities

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun fetchSpecialities() {
        viewModelScope.launch {
            specialityRepository.getAll()
                .onSuccess { _specialities.value = it }
                .onFailure {
                    _error.value = it.message
                    delay(1000)
                    _error.value = null
                }
        }
    }

    fun addSpeciality(speciality: Speciality) {
        viewModelScope.launch {
            specialityRepository.add(speciality)
                .onSuccess { fetchSpecialities() }
                .onFailure {
                    _error.value = it.message
                    delay(1000)
                    _error.value = null
                }
        }
    }

    fun updateSpeciality(speciality: Speciality) {
        viewModelScope.launch {
            specialityRepository.update(speciality)
                .onSuccess { fetchSpecialities() }
                .onFailure {
                    _error.value = it.message
                    delay(1000)
                    _error.value = null
                }
        }
    }

    fun deleteSpeciality(id: Int) {
        viewModelScope.launch {
            specialityRepository.delete(id)
                .onSuccess { fetchSpecialities() }
                .onFailure {
                    _error.value = it.message
                    delay(1000)
                    _error.value = null
                }
        }
    }
}
