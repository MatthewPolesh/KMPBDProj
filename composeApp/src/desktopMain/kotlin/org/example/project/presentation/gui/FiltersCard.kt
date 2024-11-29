package org.example.project.presentation.gui

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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import kotlinx.coroutines.launch
import org.example.project.utils.Utilities


@Composable
fun FiltersCard(
    onAccept: () -> Unit,
    onDismiss: () -> Unit,
) {
    val rowState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    val charList = listOf("Поле1", "Поле2", "Поле3", "Поле4", "Поле5", "Поле6")
    val allOptions = mapOf(
        "Поле1" to listOf("Хаха1", "Хи"),
        "Поле2" to listOf("Хаха2", "Хи"),
        "Поле3" to listOf("Хаха3", "Хи"),
        "Поле4" to listOf("Поле4", "Хи"),
        "Поле5" to listOf("Хаха5", "Хи"),
        "Поле6" to listOf("Хаха6", "Хи")
    )

    Dialog(
        onDismissRequest = { onDismiss() },
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Card(
            modifier = Modifier
                .height(800.dp)
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
                    itemsIndexed(charList) { index, item ->
                        Column(
                            modifier = Modifier.padding(Utilities.paddingExternal)
                        ) {
                            Text(text = item)
                            AutoTextField(allOptions[item]!!)
                        }
                    }
                }
                Spacer(modifier = Modifier.weight(1f))
                Row {
                    CustomButton(
                        text = "Применить",
                        onClick = { onAccept() }
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    CustomButton(
                        text = "Отменить",
                        onClick = { onDismiss() }
                    )
                }
            }
        }
    }
}
