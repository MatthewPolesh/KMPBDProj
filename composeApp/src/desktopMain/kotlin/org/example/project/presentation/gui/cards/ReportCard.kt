package org.example.project.presentation.gui.cards

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.unit.dp
import kotlinx.datetime.LocalDate
import org.example.project.domain.entities.Report
import org.example.project.presentation.gui.CustomButton
import org.example.project.utils.Utilities


@Composable
fun ReportCard(
    item: Report,
    onDelete: (Int) -> Unit,
    onUpdate: (Report) -> Unit,
) {
    var extended by remember { mutableStateOf(false) }
    var isEditing by remember { mutableStateOf(false) }

    val textId by remember { mutableStateOf("${item.id}") }
    var textName by remember { mutableStateOf(item.name) }
    var textDate by remember { mutableStateOf("${item.date}") }
    val textDone by remember(item.medicalOfficerName) { mutableStateOf(item.medicalOfficerName) }
    var textOffId by remember { mutableStateOf("${item.medicalOfficerId}") }
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
                modifier = Modifier.fillMaxWidth().height(40.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = item.name)
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
                    Text("Id: $textId")
                    Text("Название: $textName")
                    Text("Дата выполнения: $textDate")
                    Text("Выполнил: $textDone")
                    Text("Id сотрудника: $textOffId")
                } else {
                    Text("Id: $textId")
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("Название: ")
                        TextField(
                            value = textName,
                            onValueChange = { newText -> textName = newText },
                        )
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("Дата выполнения: ")
                        TextField(
                            value = textDate,
                            onValueChange = { newText -> textDate = newText },
                        )
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("Id сотрудника: ")
                        TextField(
                            value = textOffId,
                            onValueChange = { newText -> textOffId = newText },
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        CustomButton(
                            text = "Сохранить",
                            onClick = {
                                isEditing = false
                                extended = false
                                onUpdate(
                                    Report(
                                        id = textId.toInt(),
                                        name = textName,
                                        date = LocalDate.parse(textDate),
                                        medicalOfficerId = textOffId.toInt(),
                                        medicalOfficerName = ""
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
