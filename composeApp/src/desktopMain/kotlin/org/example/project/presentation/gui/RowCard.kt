package org.example.project.presentation.gui

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
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.example.project.utils.Utilities


@Composable
fun RowCard() {
    var extended by remember { mutableStateOf(false) }
    val charList = listOf("Номер такой", "Выполнил тот то", "Выполнил этот")
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
                Text(text = "№1", modifier = Modifier.padding(end = 10.dp))
                Text(text = "Запись 1")
                Spacer(modifier = Modifier.weight(1F))
                IconButton(
                    onClick = {},
                    content = { Text(text = "R") })
                IconButton(
                    onClick = {},
                    content = { Text(text = "D") })
                IconButton(
                    onClick = {extended = !extended},
                    content = { Text(text = if (extended) "<" else ">") })
            }
            if (extended) {
                Divider(modifier = Modifier.fillMaxWidth())
                charList.forEach { char ->
                    Text(text = char)
                }
            }
        }

    }
}