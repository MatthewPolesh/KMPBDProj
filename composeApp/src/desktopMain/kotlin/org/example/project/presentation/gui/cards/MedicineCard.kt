package org.example.project.presentation.gui.cards

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
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
import org.example.project.utils.Utilities


@Composable
fun MedicineCard(
    item: Medicine,
    onDelete: (Int) -> Unit,
    onUpdate: (Medicine) -> Unit,
) {
    var extended by remember { mutableStateOf(false) }
    var isEditing by remember { mutableStateOf(false) }

    var textId by remember { mutableStateOf("${item.id}") }
    var textName by remember { mutableStateOf(item.name) }
    var textProducer by remember { mutableStateOf(item.producer) }
    var textDateProduce by remember { mutableStateOf("${item.dateProduce}") }
    var textSubId by remember { mutableStateOf("${item.activeSubstanceId}") }
    var textSubName by remember { mutableStateOf(item.activeSubstanceName) }
    var textFormId by remember { mutableStateOf("${item.medicinalFormId}") }
    var textFormName by remember { mutableStateOf(item.medicinalFormName) }
    var textStandardId by remember { mutableStateOf("${item.standardId}") }
    var textStandardName by remember { mutableStateOf(item.standardName) }

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
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = textName)
                Spacer(modifier = Modifier.weight(1F))
                IconButton(
                    onClick = {
                        isEditing = !isEditing
                        extended = true
                    },
                    content = { Text(text = "R") })
                IconButton(
                    onClick = { onDelete(item.id) },
                    content = { Text(text = "D") })
                IconButton(
                    onClick = {
                        extended = !extended
                        isEditing = false
                    },
                    content = { Text(text = if (extended) "<" else ">") })
            }
            if (extended) {
                Divider(modifier = Modifier.fillMaxWidth())
                if (!isEditing) {
                    Text("ID: $textId")
                    Text("Название: $textName")
                    Text("Производитель: $textProducer")
                    Text("Дата производства: $textDateProduce")
                    Text("ID активного компонента: $textSubId")
                    Text("Активный компонент: $textSubName")
                    Text("ID вида лекраства: $textFormId")
                    Text("Вид лекарства: $textFormName")
                    Text("ID стандрата: $textStandardId")
                    Text("Стандрат: $textStandardName")
                } else {
                    Text("Id: $textId")
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("Название: $textName")
                        TextField(
                            value = textName,
                            onValueChange = { newText -> textName = newText },
                        )
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("Производитель: $textProducer")
                        TextField(
                            value = textProducer,
                            onValueChange = { newText -> textProducer = newText },
                        )
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("Дата производства: $textDateProduce")
                        TextField(
                            value = textDateProduce,
                            onValueChange = { newText -> textDateProduce = newText },
                        )
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("ID активного компонента: $textSubId")
                        TextField(
                            value = textSubId,
                            onValueChange = { newText -> textSubId = newText },
                        )
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("ID вида лекраства: $textFormId")
                        TextField(
                            value = textFormId,
                            onValueChange = { newText -> textFormId = newText },
                        )
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("ID стандрата: $textStandardId")
                        TextField(
                            value = textName,
                            onValueChange = { newText -> textName = newText },
                        )
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text("ID стандрата: $textStandardId")
                            TextField(
                                value = textStandardId,
                                onValueChange = { newText -> textStandardId = newText },
                            )
                        }
                        Spacer(modifier = Modifier.weight(1f))
                        Text(
                            "Сохранить",
                            modifier = Modifier.clickable {
                                isEditing = false
                                extended = false
                                onUpdate(
                                    Medicine(
                                        id = textId.toInt(),
                                        producer = textProducer,
                                        name = textName,
                                        dateProduce = LocalDate.parse(textDateProduce),
                                        activeSubstanceId = textSubId.toInt(),
                                        activeSubstanceName = "",
                                        medicinalFormId = textFormId.toInt(),
                                        medicinalFormName = "",
                                        standardId = textStandardId.toInt(),
                                        standardName = ""

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