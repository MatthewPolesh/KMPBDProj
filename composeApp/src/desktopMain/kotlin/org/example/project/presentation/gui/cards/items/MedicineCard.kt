package org.example.project.presentation.gui.cards.items

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
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
import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.arrow_drop_down_24px
import kotlinproject.composeapp.generated.resources.arrow_drop_up_24px
import kotlinproject.composeapp.generated.resources.delete_24px
import kotlinproject.composeapp.generated.resources.edit_note_24px
import kotlinx.datetime.LocalDate
import org.example.project.domain.entities.Medicine
import org.example.project.presentation.gui.custom.CustomButton
import org.example.project.utils.Utilities
import org.jetbrains.compose.resources.painterResource


@Composable
fun MedicineCard(
    item: Medicine,
    onDelete: (Int) -> Unit,
    onUpdate: (Medicine) -> Unit,
) {
    var extended by remember { mutableStateOf(false) }
    var isEditing by remember { mutableStateOf(false) }

    var textName by remember(item) { mutableStateOf(item.name) }
    var textProducer by remember(item) { mutableStateOf(item.producer) }
    var textDateProduce by remember(item) { mutableStateOf("${item.dateProduce}") }
    var textDosage by remember(item) { mutableStateOf("${item.dosage}") }
    var textSubId by remember(item) { mutableStateOf("${item.activeSubstanceId}") }
    val textSubName by remember(item) { mutableStateOf(item.activeSubstanceName) }
    var textFormId by remember(item) { mutableStateOf("${item.medicinalFormId}") }
    val textFormName by remember(item) { mutableStateOf(item.medicinalFormName) }
    var textStandardId by remember(item) { mutableStateOf("${item.standardId}") }
    val textStandardName by remember(item) { mutableStateOf(item.standardName) }

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
                Text(text = textName, style = MaterialTheme.typography.body1)
                Spacer(modifier = Modifier.weight(1F))
                IconButton(
                    onClick = {
                        isEditing = !isEditing
                        extended = true
                    },
                    content = {
                        Icon(painter = painterResource(Res.drawable.edit_note_24px),
                            null,
                            tint = Color.Black) })
                IconButton(
                    onClick = { onDelete(item.id) },
                    content = {
                        Icon(painter = painterResource(Res.drawable.delete_24px),
                            null,
                            tint = Color.Black) })
                IconButton(
                    onClick = {
                        extended = !extended
                        isEditing = false
                    },
                    content = { Icon(
                        painter = if (!extended)
                            painterResource(Res.drawable.arrow_drop_down_24px)
                        else
                            painterResource(Res.drawable.arrow_drop_up_24px),
                        null,
                        tint = Color.Black) }
                )
            }
            if (extended) {
                Divider(modifier = Modifier.fillMaxWidth().padding(vertical = Utilities.paddingExternal))
                if (!isEditing) {
                    Text("Название: $textName", style = MaterialTheme.typography.body2)
                    Text("Производитель: $textProducer", style = MaterialTheme.typography.body2)
                    Text("Дата производства: $textDateProduce", style = MaterialTheme.typography.body2)
                    Text("Дозировка: $textDosage", style = MaterialTheme.typography.body2)
                    Text("Активный компонент: $textSubName", style = MaterialTheme.typography.body2)
                    Text("Вид лекарства: $textFormName", style = MaterialTheme.typography.body2)
                    Text("Стандрат: $textStandardName", style = MaterialTheme.typography.body2)
                } else {
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
                        Text("ID вида лекраства: ", style = MaterialTheme.typography.body2)
                        TextField(
                            value = textFormId,
                            onValueChange = { newText -> textFormId = newText },
                        )
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("ID стандрата: ", style = MaterialTheme.typography.body2)
                        TextField(
                            value = textName,
                            onValueChange = { newText -> textName = newText },
                        )
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text("ID стандрата: ", style = MaterialTheme.typography.body2)
                            TextField(
                                value = textStandardId,
                                onValueChange = { newText -> textStandardId = newText },
                            )
                        }
                        Spacer(modifier = Modifier.weight(1f))
                        CustomButton(
                            text = "Сохранить",
                            onClick = {
                                isEditing = false
                                extended = false
                                onUpdate(
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