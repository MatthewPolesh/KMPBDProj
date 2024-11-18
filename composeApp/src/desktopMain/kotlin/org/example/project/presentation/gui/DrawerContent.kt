package org.example.project.presentation.gui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.example.project.utils.Utilities

@Composable
fun DrawerContent(
    onMenuItemClick: (String) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(text = "Меню",
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onMenuItemClick("Меню") }
                .padding(Utilities.paddingButton))
        Divider()
        DrawerItem(text = "Таблицы", onClick = {})
        DrawerItem(text = "Настройки", onClick = {})
        Spacer(modifier = Modifier.weight(1f))
        DrawerItem(text = "Выход", onClick = {})
    }

}