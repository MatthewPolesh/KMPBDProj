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
import org.example.project.domain.filters.MedicineFilter
import org.example.project.presentation.gui.custom.AutoTextField
import org.example.project.presentation.gui.custom.CustomButton
import org.example.project.presentation.viewmodels.MedicineViewModel
import org.example.project.utils.Utilities
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun FilterMedicineCard(
    onAccept: () -> Unit,
    onDismiss: () -> Unit,
) {
    val viewModel: MedicineViewModel = koinViewModel()
    val itemsList = viewModel.medicines.collectAsState()
    val rowState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    val idList = itemsList.value.map { it.id.toString() }
    val producerList = itemsList.value.map { it.producer }
    val nameList = itemsList.value.map { it.name }
    val dosageList = itemsList.value.map { it.dosage.toString() }
    val dateProduceList = itemsList.value.map { it.dateProduce.toString() }
    val activeSubstanceIdList = itemsList.value.map { it.activeSubstanceId.toString() }
    val activeSubstanceNameList = itemsList.value.map { it.activeSubstanceName }
    val medicinalFormIdList = itemsList.value.map { it.medicinalFormId.toString() }
    val medicinalFormNameList = itemsList.value.map { it.medicinalFormName }
    val standardIdList = itemsList.value.map { it.standardId.toString() }
    val standardNameList = itemsList.value.map { it.standardName }
    var locDate by remember {mutableStateOf("")}

    var filterState by remember { mutableStateOf(MedicineFilter()) }
    val charList = listOf(
        "ID",
        "Производитель",
        "Название",
        "Дозировка",
        "Дата производства",
        "ID активного вещества",
        "Название активного вещества",
        "ID лекарственной формы",
        "Название лекарственной формы",
        "ID стандарта",
        "Название стандарта"
    )
    val allOptions = mapOf(
        "ID" to idList,
        "Производитель" to producerList,
        "Название" to nameList,
        "Дозировка" to dosageList,
        "Дата производства" to dateProduceList,
        "ID активного вещества" to activeSubstanceIdList,
        "Название активного вещества" to activeSubstanceNameList,
        "ID лекарственной формы" to medicinalFormIdList,
        "Название лекарственной формы" to medicinalFormNameList,
        "ID стандарта" to standardIdList,
        "Название стандарта" to standardNameList
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
                                allOptions[item]!!.distinct(),
                                onTextChange = { newValue ->
                                    when (item) {
                                        "ID" -> filterState = filterState.copy(id = newValue.toIntOrNull())
                                        "Производитель" -> filterState = filterState.copy(producer = newValue.trim())
                                        "Название" -> filterState = filterState.copy(name = newValue.trim())
                                        "Дозировка" -> filterState = filterState.copy(dosage = newValue.toIntOrNull())
                                        "Дата производства" -> {
                                            locDate = newValue
                                        }
                                        "ID активного вещества" -> filterState = filterState.copy(activeSubstanceId = newValue.toIntOrNull())
                                        "Название активного вещества" -> filterState = filterState.copy(activeSubstanceName = newValue.trim())
                                        "ID лекарственной формы" -> filterState = filterState.copy(medicinalFormId = newValue.toIntOrNull())
                                        "Название лекарственной формы" -> filterState = filterState.copy(medicinalFormName = newValue.trim())
                                        "ID стандарта" -> filterState = filterState.copy(standardId = newValue.toIntOrNull())
                                        "Название стандарта" -> filterState = filterState.copy(standardName = newValue.trim())
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
                                filterState = filterState.copy(dateProduce = parsedDate)
                            }
                            viewModel.fetchFilteredMedicine(filterState)
                            onAccept()
                        }
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    CustomButton(
                        text = "Отменить",
                        onClick = {
                            onDismiss()
                            viewModel.fetchMedicines()
                        }
                    )
                }
            }
        }
    }
}
