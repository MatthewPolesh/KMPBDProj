package org.example.project.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.example.project.domain.entities.MedicalOfficer
import org.example.project.domain.entities.Speciality
import org.example.project.domain.repositories.MedicalOfficerRepository
import org.example.project.domain.repositories.SpecialityRepository

class UserViewModel(
    private val medicalOfficerRepository: MedicalOfficerRepository,
    private val specialityRepository: SpecialityRepository
) : ViewModel() {
    private val _username = MutableStateFlow<String?>(null)
    val username: StateFlow<String?> = _username

    private val _accessibility = MutableStateFlow(false)
    val accessibility: StateFlow<Boolean> = _accessibility

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    private val _isAuthenticated = MutableStateFlow(false)
    val isAuthenticated: StateFlow<Boolean> = _isAuthenticated



    fun addMedicalOfficer(
        firstName: String,
        lastName: String,
        surname: String,
        age: Int,
        numberChild: Int,
        email: String,
        workExperience: Int,
        specName: String
    ): Boolean {
        var result = false
        viewModelScope.launch {
            specialityRepository.getAll()
                .onSuccess {
                    var specId: Int? = null
                    val specMap = mutableMapOf<Int, String>()
                    for (spec in it) {
                        if (specName == spec.name) {
                            specId = spec.id
                            val medicalOfficer = MedicalOfficer(
                                id = 0,
                                firstName = firstName,
                                lastName = lastName,
                                surname = surname,
                                age = age,
                                numberChild = numberChild,
                                email = email,
                                workExperience = workExperience,
                                specialityId = specId,
                                specialityName = ""
                            )
                            viewModelScope.launch {
                                medicalOfficerRepository.add(medicalOfficer)
                                    .onSuccess {
                                        _username.value = "$surname $firstName $lastName"
                                        _isAuthenticated.value = true
                                        _accessibility.value = specId == 0
                                        result = true
                                    }
                                    .onFailure { _error.value = it.message }
                            }
                            break
                        }
                        specMap += Pair(spec.id, spec.name)
                    }
                    if (specId == null) {
                        viewModelScope.launch {
                            specialityRepository.add(Speciality(0, specName, "Заполнить"))
                                .onSuccess {
                                    val medicalOfficer = MedicalOfficer(
                                        id = 0,
                                        firstName =firstName,
                                        lastName = lastName,
                                        surname = surname,
                                        age =  age,
                                        numberChild = numberChild,
                                        email = email,
                                        workExperience = workExperience,
                                        specialityId = specMap.keys.max() + 1,
                                        specialityName = ""
                                    )
                                    medicalOfficerRepository.add(medicalOfficer)
                                        .onSuccess {

                                            _username.value = "$surname $firstName $lastName"
                                            _isAuthenticated.value = true
                                            _accessibility.value = false
                                            result = true
                                        }
                                        .onFailure {
                                            _error.value = it.message
                                        }
                                }
                                .onFailure {
                                    _error.value = it.message
                                }
                        }
                    }
                }
                .onFailure { _error.value = it.message }
        }
        return result
    }


    fun authenticate(username: String, password: String): Boolean {
        var result = false
        viewModelScope.launch {
            medicalOfficerRepository.getAll()
                .onSuccess {
                    val surnameMap = mutableMapOf<String, List<String>>()
                    for (medicalOff in it) {
                        surnameMap += Pair(
                            medicalOff.surname,
                            listOf(medicalOff.id.toString(), medicalOff.specialityId.toString())
                        )
                    }
                    if (username in surnameMap.keys) {
                        if (password == surnameMap[username]!![0]) {
                            _username.value = username
                            _isAuthenticated.value = true
                            _accessibility.value = surnameMap[username]!![1] == "1"
                            result = true
                        } else {
                            _error.value = error("Неправильный пароль")
                        }
                    } else {
                        _error.value = error("Такой пользователь не найден")
                    }
                }
                .onFailure {
                    _error.value = it.message
                    result = false
                }
        }
        return result
    }

    fun logout() {
        _username.value = null
        _isAuthenticated.value = false
        _accessibility.value = false
    }
}
