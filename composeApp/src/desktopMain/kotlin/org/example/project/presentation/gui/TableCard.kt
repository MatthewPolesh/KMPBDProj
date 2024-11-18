package org.example.project.presentation.gui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.example.project.utils.Utilities
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun TableCard(modifier: Modifier = Modifier) {
    val itemList = listOf(1,2,3,4,5,6,7,8,9,10)
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(start = Utilities.paddingExternal, end = Utilities.paddingExternal, bottom = Utilities.paddingExternal)

    )
    {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            LazyColumn(
                modifier = Modifier.weight(0.9f)
            ) {
                items(itemList) { item ->
                    RowCard()
                }
            }
        }
    }
}