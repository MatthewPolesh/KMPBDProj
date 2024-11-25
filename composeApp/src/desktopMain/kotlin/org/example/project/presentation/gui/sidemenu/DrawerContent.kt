package org.example.project.presentation.gui.sidemenu

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.example.project.utils.Utilities

@Composable
fun DrawerContent(
    onMenuItemClick: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
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
        DrawerItem(text = "Таблицы", onClick = {expanded = !expanded })
        if (expanded)
        {
            DrawerItem(text ="Сотрудники", onClick = {})
            DrawerItem(text ="Специальности", onClick = {})
            DrawerItem(text ="Отчёты", onClick = {})
            DrawerItem(text ="Лекарства", onClick = {}) //Medicine
            DrawerItem(text ="Активные комоненты таблеток", onClick = {}) //ActiveSubstance
            DrawerItem(text ="Виды лекарства", onClick = {}) // MedicinalForm
            DrawerItem(text ="Стандарты изготовления", onClick = {})
            DrawerItem(text ="Статусы изготовления", onClick = {})
        }
        Spacer(modifier = Modifier.weight(1f))
        DrawerItem(text = "Выход", onClick = {})
    }

}

