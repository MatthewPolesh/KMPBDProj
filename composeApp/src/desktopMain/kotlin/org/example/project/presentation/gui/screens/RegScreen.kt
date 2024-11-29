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
import androidx.compose.ui.unit.dp
import org.example.project.presentation.gui.custom.CustomButton
import org.example.project.presentation.viewmodels.UserViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun RegScreen(
    userViewModel: UserViewModel = koinViewModel(),
    onReg: () -> Unit
) {
    var textName by remember { mutableStateOf("") }
    var textAge by remember { mutableStateOf("") }
    var textEmail by remember { mutableStateOf("") }
    var textExp by remember { mutableStateOf("") }
    var textChild by remember { mutableStateOf("") }
    var textSpec by remember { mutableStateOf("") }

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
                userViewModel.addMedicalOfficer(
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
