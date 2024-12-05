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
import kotlinx.datetime.LocalDate
import org.example.project.domain.filters.ReportFilter
import org.example.project.presentation.gui.custom.AutoTextField
import org.example.project.presentation.gui.custom.CustomButton
import org.example.project.presentation.viewmodels.ReportViewModel
import org.example.project.utils.Utilities
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun FilterReportCard(
    onAccept: () -> Unit,
    onDismiss: () -> Unit,
) {
    val viewModel: ReportViewModel = koinViewModel()
    val itemsList = viewModel.reports.collectAsState()
    val rowState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    val idMedOffList = itemsList.value.map { it.medicalOfficerId.toString() }
    val nameMedOffList = itemsList.value.map { it.medicalOfficerName}
    val dateList = itemsList.value.map { it.date.toString()}
    val nameList = itemsList.value.map  { it.name }
    val idList = itemsList.value.map  { it.id.toString() }
    var filterState by remember { mutableStateOf(ReportFilter())}
    var locDate by remember {mutableStateOf("")}
    val charList = listOf("ID", "Название", "Дата выполнения", "Выполнил", "ID сотрудника")
    val allOptions = mapOf(
        "ID" to idList,
        "Название" to nameList,
        "Дата выполнения" to dateList ,
        "Выполнил" to nameMedOffList,
        "ID сотрудника" to idMedOffList,
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
                            println("Filter state: $filterState")
                            Text(text = item)
                            AutoTextField(
                                allOptions[item]!!.distinct(),
                                onTextChange  = { newValue ->
                                     when (item) {
                                        "ID" -> filterState = filterState.copy(id = newValue.toIntOrNull())
                                        "Название" -> filterState = filterState.copy(name = newValue)
                                        "Дата выполнения" ->  {
                                            locDate = newValue
                                        }
                                        "Выполнил" ->filterState =  filterState.copy(medicalOfficerName = newValue)
                                        "ID сотрудника" -> filterState = filterState.copy(medicalOfficerId = newValue.toIntOrNull())
                                        else -> {}
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
                            if (locDate.isNotBlank()) {
                                val parsedDate = try {
                                    LocalDate.parse(locDate)
                                } catch (e: Exception) {
                                    null
                                }
                                filterState = filterState.copy(date = parsedDate)
                            }
                            viewModel.fetchFilteredReports(filterState)
                            onAccept() }
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    CustomButton(
                        text = "Отменить",
                        onClick = {
                            onDismiss()
                            viewModel.fetchReports()
                        }
                    )
                }
            }
        }
    }
}
