package org.example.project.presentation.gui.tables

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import org.example.project.domain.entities.GOST
import org.example.project.presentation.gui.cards.GostCard
import org.example.project.presentation.viewmodels.GOSTViewModel
import org.example.project.utils.Utilities
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Preview
@Composable
fun GostTableCard(modifier: Modifier = Modifier) {

    var isEditing by remember { mutableStateOf(false) }
    var textId by remember { mutableStateOf("") }
    var textName by remember { mutableStateOf("") }


    val viewModel: GOSTViewModel = koinViewModel()
    val itemList = viewModel.gosts.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.fetchGOSTs()
    }
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(
                start = Utilities.paddingExternal,
                end = Utilities.paddingExternal,
                bottom = Utilities.paddingExternal
            )

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
                    GostCard(
                        item,
                        onUpdate = { viewModel.updateGOST(it) },
                        onDelete = { viewModel.deleteGOST(it) }
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
                            .clickable { isEditing = !isEditing }
                    )
                    {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = Utilities.paddingIntertal)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(
                                        start = Utilities.paddingExternal,
                                        end = Utilities.paddingExternal,
                                        bottom = Utilities.paddingExternal
                                    ),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "Добавить",
                                    modifier = Modifier
                                        .padding( vertical = Utilities.paddingButton/2)
                                        .clip(shape = RoundedCornerShape(Utilities.cornerBox))
                                        .clickable { isEditing = !isEditing }
                                        .padding( vertical = Utilities.paddingButton/2) )
                            }

                            if (isEditing) {
                                Divider(modifier = Modifier.fillMaxWidth())
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text("Id: ")
                                    TextField(
                                        value = textId,
                                        onValueChange = { newText -> textId = newText },
                                    )
                                }
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text("Название: ")
                                    TextField(
                                        value = textName,
                                        onValueChange = { newText -> textName = newText },
                                    )
                                    Spacer(modifier = Modifier.weight(1f))
                                    Text(
                                        "Подтвердить",
                                        modifier = Modifier.clickable {
                                            isEditing = !isEditing
                                            viewModel.addGOST(
                                                GOST(
                                                    id = textId.toInt(),
                                                    name = textName
                                                )
                                            )
                                        }.padding( vertical = Utilities.paddingButton/2)

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
