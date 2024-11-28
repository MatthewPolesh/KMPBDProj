package org.example.project.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.example.project.domain.entities.MedicalOfficer
import org.example.project.domain.repositories.MedicalOfficerRepository

class UserViewModel(
    private val medicalOfficerRepository: MedicalOfficerRepository
) : ViewModel() {
    // Храним username пользователя
    private val _username = MutableStateFlow<String?>(null)
    val username: StateFlow<String?> = _username

    private val _accessibility = MutableStateFlow<Boolean?>(null)
    val accessibility: StateFlow<Boolean?> = _accessibility

    private val _medicalOfficers = MutableStateFlow<List<MedicalOfficer>>(emptyList())
    val medicalOfficers: StateFlow<List<MedicalOfficer>> = _medicalOfficers

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    // Храним состояние аутентификации
    private val _isAuthenticated = MutableStateFlow(false)
    val isAuthenticated: StateFlow<Boolean> = _isAuthenticated
    fun fetchMedicalOfficers() {
        viewModelScope.launch {
            medicalOfficerRepository.getAll()
                .onSuccess { _medicalOfficers.value = it }
                .onFailure { _error.value = it.message }
        }
    }
    fun authenticate(username: String, password: String) {
        val surnameMap = mutableMapOf<String, List<String>>()
        for (medicalOff in medicalOfficers.value)
        {
            surnameMap += Pair(medicalOff.surname, listOf(medicalOff.id.toString(), medicalOff.specialityId.toString()))
        }
        if (username in surnameMap.keys)
        {
            if (password == surnameMap[username]!![0])
            {
                _username.value = username
                _isAuthenticated.value = true
                _accessibility.value = surnameMap[username]!![1] == "0"
            }
            else {
                _error.value = error("Неправильный пароль")
            }
        }
        else {
            _error.value = error("Такой пользователь не найден")
        }
    }

    fun logout() {
        _username.value = null
        _isAuthenticated.value = false
        _accessibility.value = false
    }
}
