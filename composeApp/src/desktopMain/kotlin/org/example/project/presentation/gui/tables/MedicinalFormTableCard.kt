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
import androidx.compose.foundation.lazy.items
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
import org.example.project.domain.entities.MedicinalForm
import org.example.project.presentation.gui.cards.items.MedicinalFormCard
import org.example.project.presentation.gui.custom.CustomButton
import org.example.project.presentation.viewmodels.MedicinalFormViewModel
import org.example.project.utils.Utilities
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun MedicinalFormTableCard(
    modifier: Modifier = Modifier,
    onError: (String) -> Unit,
) {
    var textName by remember { mutableStateOf("") }
    var textCompos by remember { mutableStateOf("") }
    var textOffId by remember { mutableStateOf("") }
    var isEditing by remember { mutableStateOf(false) }

    val viewModel: MedicinalFormViewModel = koinViewModel()
    val itemList = viewModel.medicinalForms.collectAsState()
    LaunchedEffect(Unit){
        viewModel.fetchMedicinalForms()
        viewModel.error.collect{
            if (it != null) {
                onError(it)
                viewModel.fetchMedicinalForms()
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
                items(itemList.value) { item ->
                    MedicinalFormCard(
                        item,
                        onUpdate = {viewModel.updateMedicinalForm(it)},
                        onDelete = {viewModel.deleteMedicinalForm(it)}
                    )
                }
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
                                    Text("Название: ", style = MaterialTheme.typography.body2)
                                    TextField(
                                        value = textName,
                                        onValueChange = { newText -> textName = newText },
                                    )
                                }
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text("Cостав: ", style = MaterialTheme.typography.body2)
                                    TextField(
                                        value = textCompos,
                                        onValueChange = { newText -> textCompos = newText  },
                                    )
                                }
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text("ID сотрудника: ", style = MaterialTheme.typography.body2)
                                    TextField(
                                        value = textOffId,
                                        onValueChange = { newText -> textOffId = newText  },
                                    )
                                    Spacer(modifier = Modifier.weight(1f))
                                    CustomButton(
                                        text = "Добавить",
                                        onClick = {
                                            isEditing = !isEditing
                                            viewModel.addMedicinalForm(
                                                MedicinalForm(
                                                    id = 0,
                                                    name = textName,
                                                    composition = textCompos,
                                                    medicalOfficerId = textOffId.toInt()
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
