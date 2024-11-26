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
import org.example.project.domain.entities.ActiveSubstance
import org.example.project.utils.Utilities


@Composable
fun ActiveSubstanceCard(
    item: ActiveSubstance,
    onDelete: (Int) -> Unit,
    onUpdate: (ActiveSubstance) -> Unit,
) {
    var extended by remember { mutableStateOf(false) }
    var isEditing by remember { mutableStateOf(false) }

    val textId by remember { mutableStateOf("${item.id}") }
    var textName by remember { mutableStateOf(item.name) }
    var textCompos by remember { mutableStateOf(item.composition) }
    var textAppoint by remember{ mutableStateOf(item.appointment) }
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
                modifier = Modifier.fillMaxWidth(),
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
                    Text("Состав: $textCompos")
                    Text("Показания к применению: $textAppoint")
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
                        Text("Состав: ")
                        TextField(
                            value = textCompos,
                            onValueChange = { newText -> textCompos = newText },
                        )
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("Показания к применению: ")
                        TextField(
                            value = textAppoint,
                            onValueChange = { newText -> textAppoint = newText },
                        )
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("Id сотрудника: ")
                        TextField(
                            value = textOffId,
                            onValueChange = { newText -> textOffId = newText },
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Text(
                            "Сохранить",
                            modifier = Modifier.clickable {
                                isEditing = false
                                extended = false
                                onUpdate(
                                    ActiveSubstance(
                                        id = textId.toInt(),
                                        name = textName,
                                        composition = textCompos,
                                        appointment = textAppoint,
                                        medicalOfficerId = textOffId.toInt(),

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