package org.example.project.presentation.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class UserViewModel : ViewModel() {

    // Храним username пользователя
    private val _username = MutableStateFlow<String?>(null)
    val username: StateFlow<String?> = _username

    // Храним состояние аутентификации
    private val _isAuthenticated = MutableStateFlow(false)
    val isAuthenticated: StateFlow<Boolean> = _isAuthenticated

    // Метод для аутентификации пользователя
    fun authenticate(username: String, password: String) {
        // Здесь должна быть логика проверки учетных данных
        // Например, запрос к серверу или локальная проверка
        // Для демонстрации предположим, что аутентификация всегда успешна

        // Никогда не сохраняйте пароль в памяти или базе данных

        // Обновляем состояние
        _username.value = username
        _isAuthenticated.value = true
    }

    // Метод для выхода пользователя
    fun logout() {
        _username.value = null
        _isAuthenticated.value = false
    }
}
