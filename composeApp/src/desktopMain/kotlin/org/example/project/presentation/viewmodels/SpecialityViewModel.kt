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

    private val _fioResult = MutableStateFlow<List<String>>(List(100){""})
    val fioResult: StateFlow<List<String>> = _fioResult

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    private fun updateFIOResult()
    {
        _fioResult.value = List(_specialities.value.size){""}
    }
    fun fetchSpecialities() {
        viewModelScope.launch {
            specialityRepository.getAll()
                .onSuccess { _specialities.value = it; updateFIOResult()}
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
    fun getMedicalOfficerFIOBySpeciality(nameSpeciality: String, index: Int) {
        viewModelScope.launch {
            specialityRepository.getMedicalOfficerFIOBySpeciality(nameSpeciality)
                .onSuccess { fio ->
                    if (fio != null) {
                        val tempArr = _fioResult.value.toMutableList().apply { add(index,fio) }
                        _fioResult.value = tempArr
                    }
                    else _error.value = "Неудалось получить ФИО сотрудника"
                }
                .onFailure {
                    _error.value = it.message
                    delay(2000)
                    _error.value = null
                }
        }
    }
}
