package org.example.project.presentation.gui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.example.project.utils.Utilities

@Composable
fun DescriptionCard(modifier: Modifier = Modifier) {
    val charList = listOf("Поле", "Тип", "Null", "Ключ", "Заданное значение", "Дополнительно")
    val lazyListState = rememberLazyListState()
    Box(
        modifier = modifier.fillMaxSize().background(Color.LightGray)
            .padding(Utilities.paddingExternal)
    ) {
        LazyColumn(
            state = lazyListState
        ) {
            itemsIndexed(charList) { index, char ->
                if (index == 0) Divider(modifier = Modifier.width((171 * 6).dp))
                Row(
                    modifier = if (index == 0) Modifier.fillMaxWidth().height(20.dp) else Modifier.fillMaxWidth().height(40.dp) ,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    charList.forEach { char ->
                        Divider(modifier = Modifier.fillMaxHeight().width(1.dp))
                        Text(
                            text = char, // Для отображения Характеристик
                            textAlign = TextAlign.Center,
                            modifier = Modifier.width(170.dp).fillMaxHeight()
                                .padding(horizontal = Utilities.paddingSmall)
                        )
                    }
                    Divider(modifier = Modifier.fillMaxHeight().width(1.dp))
                }
                Divider(modifier = Modifier.width((171 * 6).dp))
            }

        }
    }
}