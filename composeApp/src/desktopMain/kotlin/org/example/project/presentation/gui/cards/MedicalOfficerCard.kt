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
import org.example.project.domain.entities.MedicalOfficer
import org.example.project.utils.Utilities


@Composable
fun MedicalOfficerCard(
    item: MedicalOfficer,
    onDelete: (Int) -> Unit,
    onUpdate: (MedicalOfficer) -> Unit,
) {
    var extended by remember { mutableStateOf(false) }
    var isEditing by remember { mutableStateOf(false) }

    var textId by remember { mutableStateOf("${item.id}") }
    var textName by remember { mutableStateOf("${item.surname} ${item.firstName} ${item.lastName}") }
    var textAge by remember { mutableStateOf("${item.age}") }
    var textEmail by remember { mutableStateOf(item.email) }
    var textExp by remember { mutableStateOf("${item.workExperience}") }
    var textSpec by remember { mutableStateOf("${item.specialityId}") }
    var textChild by remember { mutableStateOf("${item.numberChild}") }


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
                    Text("ФИО: $textName")
                    Text("Возраст: $textAge")
                    Text("Почта: $textEmail")
                    Text("ID специальности: $textSpec")
                    Text("Опыт работы: $textExp")
                    Text("Кол-во детей: $textChild")
                } else {
                    Text("Id: $textId")
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("ФИО: ")
                        TextField(
                            value = textName,
                            onValueChange = { newText -> textName = newText },
                        )
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("Возраст: ")
                        TextField(
                            value = textAge,
                            onValueChange = { newText -> textAge = newText },
                        )
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("Почта: ")
                        TextField(
                            value = textEmail,
                            onValueChange = { newText -> textEmail = newText },
                        )
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("ID специальности: ")
                        TextField(
                            value = textSpec,
                            onValueChange = { newText -> textSpec = newText },
                        )
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("Опыт работы: ")
                        TextField(
                            value = textExp,
                            onValueChange = { newText -> textExp = newText },
                        )
                    }

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("Кол-во детей: ")
                        TextField(
                            value = textChild,
                            onValueChange = { newText -> textChild = newText },
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Text(
                            "Сохранить",
                            modifier = Modifier.clickable {
                                isEditing = false
                                extended = false
                                val name = textName.split(" ")
                                onUpdate(
                                    MedicalOfficer(
                                        id = textId.toInt(),
                                        firstName = name[1],
                                        lastName = name[2],
                                        surname = name[0],
                                        age = textAge.toInt(),
                                        numberChild = textChild.toInt(),
                                        email = textEmail,
                                        workExperience = textExp.toInt(),
                                        specialityId = textSpec.toInt()
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