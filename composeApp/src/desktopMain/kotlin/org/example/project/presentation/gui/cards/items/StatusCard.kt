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
import org.example.project.domain.entities.Status
import org.example.project.presentation.gui.custom.CustomButton
import org.example.project.utils.Utilities
import org.jetbrains.compose.resources.painterResource


@Composable
fun StatusCard(
    item: Status,
    onDelete: (Int) -> Unit,
    onUpdate: (Status) -> Unit,
    accessibility: Boolean,
) {
    var extended by remember { mutableStateOf(false) }
    var isEditing by remember { mutableStateOf(false) }
    val textId by remember(item) { mutableStateOf("${item.id}") }
    var textStartData by remember(item) { mutableStateOf("${item.startData}") }
    var textEndData by remember(item) { mutableStateOf("${item.endData}") }
    var textReasonOfChange by remember(item) { mutableStateOf(item.reasonOfChange) }


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
                Text(text = "Статус №${textId}", style = MaterialTheme.typography.body1)
                Spacer(modifier = Modifier.weight(1F))
                IconButton(
                    onClick = {
                        isEditing = !isEditing
                        extended = true
                    },
                    content = { Icon(painter = painterResource(Res.drawable.edit_note_24px),null, tint = Color.Black) })
                if (accessibility)
                IconButton(
                    onClick = { onDelete(item.id) },
                    content = { Icon(painter = painterResource(Res.drawable.delete_24px),null, tint = Color.Black) })
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
                    Text("Начало: $textStartData", style = MaterialTheme.typography.body2)
                    Text("Конец: $textEndData", style = MaterialTheme.typography.body2)
                    Text("Причина изменений: $textReasonOfChange", style = MaterialTheme.typography.body2)
                } else {
                    Text("Id: $textId", style = MaterialTheme.typography.body2)
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("Начало: ", style = MaterialTheme.typography.body2)
                        TextField(
                            value = textStartData,
                            onValueChange = { newText -> textStartData = newText },
                        )
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("Конец: ", style = MaterialTheme.typography.body2)
                        TextField(
                            value = textEndData,
                            onValueChange = { newText -> textEndData = newText },
                        )
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("Причина изменений: ", style = MaterialTheme.typography.body2)
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
                                        id = 0,
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