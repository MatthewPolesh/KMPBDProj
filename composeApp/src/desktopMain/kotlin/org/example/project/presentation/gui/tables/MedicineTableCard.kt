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
import kotlinx.datetime.LocalDate
import org.example.project.domain.entities.Medicine
import org.example.project.presentation.gui.cards.items.MedicineCard
import org.example.project.presentation.gui.custom.CustomButton
import org.example.project.presentation.viewmodels.MedicineViewModel
import org.example.project.utils.Utilities
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Preview
@Composable
fun MedicineTableCard(
    modifier: Modifier = Modifier,
    onError: (String) -> Unit,
) {

    var textName by remember { mutableStateOf("") }
    var textProducer by remember { mutableStateOf("") }
    var textDateProduce by remember { mutableStateOf("") }
    var textDosage by remember { mutableStateOf("") }
    var textSubId by remember { mutableStateOf("") }
    var textFormId by remember { mutableStateOf("") }
    var textStandardId by remember { mutableStateOf("") }
    var isEditing by remember { mutableStateOf(false) }

    val viewModel: MedicineViewModel = koinViewModel()
    val itemList = viewModel.medicines.collectAsState()
    LaunchedEffect(Unit){
        viewModel.fetchMedicines()
        viewModel.error.collect{
            if (it != null) {
                onError(it)
                viewModel.fetchMedicines()
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
                    MedicineCard(
                        item = item,
                        onUpdate = {viewModel.updateMedicine(it)},
                        onDelete = {viewModel.deleteMedicine(it)}
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
                                    Text("Производитель: ", style = MaterialTheme.typography.body2)
                                    TextField(
                                        value = textProducer,
                                        onValueChange = { newText -> textProducer = newText },
                                    )
                                }
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text("Дата производства: ", style = MaterialTheme.typography.body2)
                                    TextField(
                                        value = textDateProduce,
                                        onValueChange = { newText -> textDateProduce = newText },
                                    )
                                }
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text("Дозировка: ", style = MaterialTheme.typography.body2)
                                    TextField(
                                        value = textDosage,
                                        onValueChange = { newText -> textDosage = newText },
                                    )
                                }
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text("ID активного компонента: ", style = MaterialTheme.typography.body2)
                                    TextField(
                                        value = textSubId,
                                        onValueChange = { newText -> textSubId = newText },
                                    )
                                }
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text("ID вида лекарства: ", style = MaterialTheme.typography.body2)
                                    TextField(
                                        value = textFormId,
                                        onValueChange = { newText -> textFormId = newText },
                                    )
                                }
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text("ID стандарта: ", style = MaterialTheme.typography.body2)
                                    TextField(
                                        value = textStandardId,
                                        onValueChange = { newText -> textStandardId = newText },
                                    )
                                    Spacer(modifier = Modifier.weight(1f))
                                    CustomButton(
                                        text = "Добавить",
                                        onClick = {
                                            isEditing = !isEditing
                                            viewModel.addMedicine(
                                                Medicine(
                                                    id = 0,
                                                    producer = textProducer,
                                                    name = textName,
                                                    dateProduce = LocalDate.parse(textDateProduce),
                                                    activeSubstanceId = textSubId.toInt(),
                                                    activeSubstanceName = "",
                                                    medicinalFormId = textFormId.toInt(),
                                                    medicinalFormName = "",
                                                    standardId = textStandardId.toInt(),
                                                    standardName = "",
                                                    dosage = textDosage.toInt()
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
