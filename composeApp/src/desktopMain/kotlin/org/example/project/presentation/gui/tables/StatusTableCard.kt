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
import kotlinx.datetime.LocalDate
import org.example.project.domain.entities.Status
import org.example.project.presentation.gui.cards.items.StatusCard
import org.example.project.presentation.gui.custom.CustomButton
import org.example.project.presentation.viewmodels.StatusViewModel
import org.example.project.utils.Utilities
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Preview
@Composable
fun StatusTableCard(
    modifier: Modifier = Modifier,
    onError: (String) -> Unit
    ) {

    var isEditing by remember { mutableStateOf(false) }
    var textId by remember { mutableStateOf("") }
    var textStartData by remember { mutableStateOf("") }
    var textEndData by remember { mutableStateOf("") }
    var textReasonOfChange by remember { mutableStateOf("") }


    val viewModel: StatusViewModel = koinViewModel()
    val itemList = viewModel.statuses.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.fetchStatuses()
        viewModel.error.collect{
            if (it != null) {
                onError(it)
                viewModel.fetchStatuses()
            }
        }
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
                    StatusCard(
                        item,
                        onUpdate = { viewModel.updateStatus(it) },
                        onDelete = { viewModel.deleteStatus(it) }
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
                                    .fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                CustomButton(
                                    text = "Новое",
                                    onClick = { isEditing = !isEditing }
                                )
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
                                    Text("Начало: $textStartData")
                                    TextField(
                                        value = textStartData,
                                        onValueChange = { newText -> textStartData = newText },
                                    )
                                }
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text("Конец: $textEndData")
                                    TextField(
                                        value = textEndData,
                                        onValueChange = { newText -> textEndData = newText },
                                    )
                                }
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text("Причина изменений: $textReasonOfChange")
                                    TextField(
                                        value = textReasonOfChange,
                                        onValueChange = { newText -> textReasonOfChange = newText },
                                    )
                                    Spacer(modifier = Modifier.weight(1f))
                                    CustomButton(
                                        text = "Добавить",
                                        onClick = {
                                            isEditing = !isEditing
                                            viewModel.addStatus(
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
        }
    }
}