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
import org.example.project.domain.entities.Speciality
import org.example.project.presentation.gui.custom.CustomButton
import org.example.project.utils.Utilities
import org.jetbrains.compose.resources.painterResource


@Composable
fun SpecialityCard(
    textFio: String,
    item: Speciality,
    onDelete: (Int) -> Unit,
    onUpdate: (Speciality) -> Unit,
    accessibility:Boolean,
    fioChecked: () -> Unit
) {
    var extended by remember { mutableStateOf(false) }
    var isEditing by remember { mutableStateOf(false) }
    var fioChecked by remember { mutableStateOf(false) }

    var textName by remember(item) { mutableStateOf(item.name) }
    var textDuties by remember(item) { mutableStateOf(item.duties) }
    val textFIO by remember(textFio) { mutableStateOf(textFio) }



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
                if (accessibility)
                {
                    IconButton(
                        onClick = {
                            isEditing = !isEditing
                            extended = true
                        },
                        content = { Icon(painter = painterResource(Res.drawable.edit_note_24px),null, tint = Color.Black) })
                    IconButton(
                        onClick = { onDelete(item.id) },
                        content = { Icon(painter = painterResource(Res.drawable.delete_24px),null, tint = Color.Black) })
                }
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
                    Text("Специальность: $textName", style = MaterialTheme.typography.body2)
                    Text("Обязаности: $textDuties", style = MaterialTheme.typography.body2)
                    if (fioChecked)
                        Text("ФИО сотрудник(а/ов): $textFIO", style = MaterialTheme.typography.body2)
                    else CustomButton("Узнать сотрудника", onClick = {fioChecked = true; fioChecked()})
                } else {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("Специальность: ", style = MaterialTheme.typography.body2)
                        TextField(
                            value = textName,
                            onValueChange = { newText -> textName = newText },
                        )
                    }

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text("Обязаности: ", style = MaterialTheme.typography.body2)
                            TextField(
                                value = textDuties,
                                onValueChange = { newText -> textDuties = newText },
                            )
                        }
                        Spacer(modifier = Modifier.weight(1f))
                        CustomButton(
                            text = "Сохранить",
                            onClick = {
                                isEditing = false
                                extended = false
                                onUpdate(
                                    Speciality(
                                        id = 0,
                                        name = textName,
                                        duties = textDuties
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