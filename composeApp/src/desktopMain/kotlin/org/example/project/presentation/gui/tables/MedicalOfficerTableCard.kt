package org.example.project.presentation.gui.tables

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import org.example.project.domain.entities.MedicalOfficer
import org.example.project.presentation.gui.cards.items.MedicalOfficerCard
import org.example.project.presentation.gui.custom.CustomButton
import org.example.project.presentation.viewmodels.MedicalOfficerViewModel
import org.example.project.utils.Utilities
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Preview
@Composable
fun MedicalOfficerTableCard(
    modifier: Modifier = Modifier,
    onError: (String) -> Unit,
    accessibility: Boolean
) {

    var textName by remember { mutableStateOf("") }
    var textAge by remember { mutableStateOf("") }
    var textEmail by remember { mutableStateOf("") }
    var textExp by remember { mutableStateOf("") }
    var textSpec by remember { mutableStateOf("")}
    var textChild by remember { mutableStateOf("") }
    var isEditing by remember { mutableStateOf(false) }

    val viewModel: MedicalOfficerViewModel = koinViewModel()
    val itemList = viewModel.medicalOfficers.collectAsState()

    LaunchedEffect(Unit){
       viewModel.fetchMedicalOfficers()
        viewModel.error.collect{
            if (it != null) {
                onError(it)
                viewModel.fetchMedicalOfficers()
            }
        }
    }
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(start = Utilities.paddingExternal, end = Utilities.paddingExternal, bottom = Utilities.paddingExternal)

    )
    {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            LazyColumn(
                modifier = Modifier.weight(0.9f).padding(Utilities.paddingIntertal)
            ) {
                itemsIndexed(itemList.value) { index, item->
                    MedicalOfficerCard(
                        accessibility = accessibility,
                        item = item,
                        onUpdate = {viewModel.updateMedicalOfficer(it)},
                        onDelete = {viewModel.deleteMedicalOfficer(it)}
                    )

                }
                if (accessibility)
                item {
                    Box(
                        modifier = Modifier
                            .wrapContentHeight()
                            .padding(vertical = Utilities.paddingExternal)
                            .fillMaxWidth()
                            .animateContentSize()
                            .clip(RoundedCornerShape(Utilities.cornerBox))
                            .background(color = Color.Gray)
                            .padding(Utilities.paddingIntertal)

                    )
                    {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = Utilities.paddingIntertal)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                CustomButton(
                                    text = "Новое",
                                    onClick = { isEditing = !isEditing }
                                )
                            }
                            if (isEditing) {
                                Divider(modifier = Modifier.fillMaxWidth())
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text("ФИО: ", style = MaterialTheme.typography.body2)
                                    TextField(
                                        value = textName,
                                        onValueChange = { newText -> textName = newText },
                                    )
                                }
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text("Возраст: ", style = MaterialTheme.typography.body2)
                                    TextField(
                                        value = textAge,
                                        onValueChange = { newText -> textAge = newText },
                                    )
                                }
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text("Почта: ", style = MaterialTheme.typography.body2)
                                    TextField(
                                        value = textEmail,
                                        onValueChange = { newText -> textEmail = newText },
                                    )
                                }
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text("ID специальности: ", style = MaterialTheme.typography.body2)
                                    TextField(
                                        value = textSpec,
                                        onValueChange = { newText -> textSpec = newText },
                                    )
                                }
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text("Опыт работы: ", style = MaterialTheme.typography.body2)
                                    TextField(
                                        value = textExp,
                                        onValueChange = { newText -> textExp = newText },
                                    )
                                }
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text("Кол-во детей: ", style = MaterialTheme.typography.body2)
                                    TextField(
                                        value = textChild,
                                        onValueChange = { newText -> textChild = newText  },
                                    )
                                    Spacer(modifier = Modifier.weight(1f))
                                    CustomButton(
                                        text = "Добавить",
                                        onClick = {
                                            isEditing = !isEditing
                                            val fullName = textName.split(" ")
                                            viewModel.addMedicalOfficer(
                                                MedicalOfficer(
                                                    id = 0,
                                                    firstName = fullName[1],
                                                    lastName = fullName[2],
                                                    surname = fullName[0],
                                                    age = textAge.toInt(),
                                                    numberChild = textChild.toInt(),
                                                    email = textEmail,
                                                    workExperience = textExp.toInt(),
                                                    specialityId = textSpec.toInt(),
                                                    specialityName = ""
                                                )
                                            )
                                        }
                                    )
                                }
                            }

                        }
                    }
                }
            }

        }
    }
}
