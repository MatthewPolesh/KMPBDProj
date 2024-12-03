package org.example.project.presentation.gui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.example.project.presentation.gui.custom.CustomButton
import org.example.project.presentation.viewmodels.UserViewModel
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun AuthScreen(
    viewModel: UserViewModel = koinViewModel(),
    onAuth: (Boolean) -> Unit,
    onReg: () -> Unit
) {
    var usernameInput by remember { mutableStateOf("") }
    var passwordInput by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()
    val snackbarHostState = remember{ SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.error.collect{
            if (it != null) {
                scope.launch { snackbarHostState.showSnackbar(message = it) }
            }
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
    )
    {
        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                "DDB", style = MaterialTheme.typography.h4)
            Text(
                "Doctor Database",
                style = MaterialTheme.typography.h5,
                modifier = Modifier.padding(bottom = 20.dp))

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
                    val result = viewModel.authenticate(usernameInput, passwordInput)
                    scope.launch {
                        delay(3000)
                        if (viewModel.isAuthenticated.value || result)
                            onAuth(viewModel.accessibility.value)
                        else
                            snackbarHostState.showSnackbar("Не верно введен пароль или пользователь")
                    }
                }
            )
            CustomButton(
                text = "Зарегистрироваться",
                onClick = {
                    onReg()
                })
        }
    }

}
