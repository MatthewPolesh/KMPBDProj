package org.example.project.presentation.gui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import org.example.project.presentation.gui.custom.CustomButton
import org.example.project.presentation.viewmodels.UserViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AuthScreen(
    userViewModel: UserViewModel = koinViewModel(),
    onAuth: () -> Unit,
    onReg: () -> Unit
) {
    var usernameInput by remember { mutableStateOf("") }
    var passwordInput by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            "DDB")
        Text(
            "Doctor Database",
            modifier = Modifier.padding(bottom = 10.dp))

        TextField(
            value = usernameInput,
            onValueChange = { usernameInput = it },
            label = { Text("Имя пользователя") }
        )
        TextField(
            value = passwordInput,
            onValueChange = { passwordInput = it },
            label = { Text("Пароль") },
            visualTransformation = PasswordVisualTransformation()
        )
        CustomButton(
            text = "Авторизоваться",
            onClick = {
                userViewModel.authenticate(usernameInput, passwordInput)
                onAuth()
            }
        )
        CustomButton(
            text = "Зарегистрироваться",
            onClick = {
                onReg()
            })
    }
}
