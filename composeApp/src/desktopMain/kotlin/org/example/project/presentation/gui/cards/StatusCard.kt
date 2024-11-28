package org.example.project.presentation.gui.cards

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
import org.example.project.domain.entities.Status
import org.example.project.presentation.gui.CustomButton
import org.example.project.utils.Utilities


@Composable
fun StatusCard(
    item: Status,
    onDelete: (Int) -> Unit,
    onUpdate: (Status) -> Unit,
) {
    var extended by remember { mutableStateOf(false) }
    var isEditing by remember { mutableStateOf(false) }
    val textId by remember { mutableStateOf("${item.id}") }
    var textStartData by remember { mutableStateOf("${item.startData}") }
    var textEndData by remember { mutableStateOf("${item.endData}") }
    var textReasonOfChange by remember { mutableStateOf(item.reasonOfChange) }


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
                Text(text = "Статус №${textId}")
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
                    Text("Начало: $textStartData")
                    Text("Конец: $textEndData")
                    Text("Причина изменений: $textReasonOfChange")
                } else {
                    Text("Id: $textId")
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("Начало: ")
                        TextField(
                            value = textStartData,
                            onValueChange = { newText -> textStartData = newText },
                        )
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("Конец: ")
                        TextField(
                            value = textEndData,
                            onValueChange = { newText -> textEndData = newText },
                        )
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("Причина изменений: ")
                        TextField(
                            value = textReasonOfChange,
                            onValueChange = { newText -> textReasonOfChange = newText },
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        CustomButton(
                            text = "Сохранить",
                            onClick = {
                                isEditing = false
                                extended = false
                                onUpdate(
                                    Status(
                                        id = textId.toInt(),
                                        startData = LocalDate.parse(textStartData),
                                        endData = LocalDate.parse(textEndData),
                                        reasonOfChange = textReasonOfChange

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