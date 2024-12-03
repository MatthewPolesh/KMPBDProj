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
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import org.example.project.presentation.gui.custom.CustomButton
import org.example.project.presentation.viewmodels.UserViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun RegScreen(
    viewModel: UserViewModel = koinViewModel(),
    onReg: () -> Unit
) {
    LaunchedEffect(Unit){
        viewModel.logout()
    }
    var textName by remember { mutableStateOf("") }
    var textAge by remember { mutableStateOf("") }
    var textEmail by remember { mutableStateOf("") }
    var textExp by remember { mutableStateOf("") }
    var textChild by remember { mutableStateOf("") }
    var textSpec by remember { mutableStateOf("") }

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
                "DDB", style = MaterialTheme.typography.h4
            )
            Text(
                "Doctor Database",
                modifier = Modifier.padding(bottom = 20.dp),
                style = MaterialTheme.typography.h5
            )
            TextField(
                value = textEmail,
                onValueChange = { textEmail = it },
                label = { Text("Почта") }
            )
            TextField(
                value = textName,
                onValueChange = { textName = it },
                label = { Text("ФИО") }
            )
            TextField(
                value = textAge,
                onValueChange = { textAge = it },
                label = { Text("Возраст") }
            )
            TextField(
                value = textExp,
                onValueChange = { textExp = it },
                label = { Text("Опыт работы") }
            )
            TextField(
                value = textChild,
                onValueChange = { textChild = it },
                label = { Text("Количество детей") }
            )
            TextField(
                value = textSpec,
                onValueChange = { textSpec = it },
                label = { Text("Ваша специальность") }
            )
            CustomButton(
                text = "Зарегистрироваться",
                onClick = {
                    val fullName = textName.split(" ")
                    viewModel.addMedicalOfficer(
                        firstName = fullName[1],
                        lastName = fullName[2],
                        surname = fullName[0],
                        age = textAge.toInt(),
                        numberChild = textChild.toInt(),
                        email = textEmail,
                        workExperience = textExp.toInt(),
                        specName = textSpec
                    )
                    onReg()
                })
        }
    }
}

