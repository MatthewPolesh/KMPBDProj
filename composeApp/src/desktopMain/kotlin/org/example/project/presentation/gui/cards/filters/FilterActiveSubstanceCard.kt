package org.example.project.presentation.gui.cards.filters

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import kotlinx.coroutines.launch
import org.example.project.domain.filters.ActiveSubstanceFilter
import org.example.project.presentation.gui.custom.AutoTextField
import org.example.project.presentation.gui.custom.CustomButton
import org.example.project.presentation.viewmodels.ActiveSubstanceViewModel
import org.example.project.utils.Utilities
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun FilterActiveSubstanceCard(
    onAccept: () -> Unit,
    onDismiss: () -> Unit,
) {
    val viewModel: ActiveSubstanceViewModel = koinViewModel()
    val itemsList = viewModel.activeSubstances.collectAsState()
    val rowState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    val idMedOffList = itemsList.value.map { it.medicalOfficerId.toString() }
    val appointmentList = itemsList.value.map { it.appointment}
    val compositionList = itemsList.value.map { it.composition }
    val nameList = itemsList.value.map  { it.name }
    val idList = itemsList.value.map  { it.id.toString() }
    var filterState by remember { mutableStateOf(ActiveSubstanceFilter()) }
    val charList = listOf("ID", "Название", "Состав", "Показания к применению", "ID сотрудника")
    val allOptions = mapOf(
        "ID" to idList,
        "Название" to nameList,
        "Состав" to compositionList ,
        "Показания к применению" to appointmentList ,
        "ID сотрудника" to idMedOffList ,
    )

    Dialog(
        onDismissRequest = { onDismiss() },
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Card(
            modifier = Modifier
                .height(400.dp)
                .width(590.dp),
            elevation = 10.dp,
        ) {
            Column(
                modifier = Modifier.padding(Utilities.paddingExternal)
            ) {
                Row {
                    Text(text = "Фильтры")
                }
                LazyRow(
                    state = rowState,
                    modifier = Modifier
                        .fillMaxWidth()
                        .pointerInput(Unit) {
                            awaitPointerEventScope {
                                while (true) {
                                    val event = awaitPointerEvent()
                                    val scrollDelta =
                                        event.changes.firstOrNull()?.scrollDelta ?: Offset.Zero
                                    if (scrollDelta.y != 0f) {
                                        val delta = scrollDelta.y
                                        coroutineScope.launch {
                                            val currentIndex = rowState.firstVisibleItemIndex
                                            val targetIndex = if (delta > 0) {
                                                (currentIndex - 1).coerceAtLeast(0)
                                            } else {
                                                (currentIndex + 1).coerceAtMost(charList.size - 1)
                                            }
                                            rowState.animateScrollToItem(targetIndex)
                                        }
                                        event.changes.forEach { it.consume() }
                                    }
                                }
                            }
                        }
                ) {
                    itemsIndexed(charList) { _, item ->
                        Column(
                            modifier = Modifier.padding(Utilities.paddingExternal)
                        ) {
                            Text(text = item)
                            AutoTextField(
                                allOptions[item]!!,
                                onTextChange  = { newValue ->
                                    filterState = when (item) {
                                        "ID" -> filterState.copy(id = newValue.toIntOrNull())
                                        "Название" -> filterState.copy(name = newValue)
                                        "Состав" -> filterState.copy(composition = newValue)
                                        "Показания к применению" -> filterState.copy(appointment = newValue)
                                        "ID сотрудника" -> filterState.copy(medicalOfficerId = newValue.toIntOrNull())
                                        else -> filterState
                                    }
                                }
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.weight(1f))
                Row {
                    CustomButton(
                        text = "Применить",
                        onClick = {
                            viewModel.fetchFilteredActiveSubstance(filterState)
                            onAccept() }
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    CustomButton(
                        text = "Отменить",
                        onClick = {
                            onDismiss()
                            viewModel.fetchActiveSubstances()
                        }
                    )
                }
            }
        }
    }
}
