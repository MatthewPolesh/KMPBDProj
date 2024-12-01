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
import org.example.project.domain.entities.MedicalOfficer
import org.example.project.presentation.gui.custom.CustomButton
import org.example.project.utils.Utilities
import org.jetbrains.compose.resources.painterResource


@Composable
fun MedicalOfficerCard(
    item: MedicalOfficer,
    onDelete: (Int) -> Unit,
    onUpdate: (MedicalOfficer) -> Unit,
) {
    var extended by remember { mutableStateOf(false) }
    var isEditing by remember { mutableStateOf(false) }

    var textId by remember(item) { mutableStateOf("${item.id}") }
    var textName by remember(item) { mutableStateOf("${item.surname} ${item.firstName} ${item.lastName}") }
    var textAge by remember(item) { mutableStateOf("${item.age}") }
    var textEmail by remember(item) { mutableStateOf(item.email) }
    var textExp by remember(item) { mutableStateOf("${item.workExperience}") }
    var textSpec by remember(item) { mutableStateOf("${item.specialityId}") }
    var textChild by remember(item) { mutableStateOf("${item.numberChild}") }


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
                    content = { Icon(painter = painterResource(Res.drawable.edit_note_24px),null, tint = Color.Black) })
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
                        CustomButton(
                            text = "Сохранить",
                            onClick = {
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