package org.example.project.presentation.gui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import kotlinx.coroutines.launch
import org.example.project.presentation.gui.custom.CustomButton
import org.example.project.presentation.gui.sidemenu.CustomDrawerShape
import org.example.project.presentation.viewmodels.UserViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AuthScreen(
    viewModel: UserViewModel = koinViewModel(),
    onAuth: () -> Unit,
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
        drawerGesturesEnabled = true,
        drawerShape = CustomDrawerShape(200.dp),
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
    )
    {
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
                    viewModel.authenticate(usernameInput, passwordInput)
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

}
