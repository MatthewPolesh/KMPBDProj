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
        Text(text = "Таблицы",
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onMenuItemClick("Таблицы") }
                .padding(Utilities.paddingButton))
        Divider()
        DrawerItem(text = "Сотрудники", onClick = { onMenuItemClick("Сотрудники") })
        DrawerItem(text = "Специальности", onClick = { onMenuItemClick("Специальности") })
        DrawerItem(text = "Отчёты", onClick = { onMenuItemClick("Отчёты") })
        DrawerItem(text = "Лекарства", onClick = { onMenuItemClick("Лекарства") }) //Medicine
        DrawerItem(
            text = "Активные комоненты препаратов ",
            onClick = { onMenuItemClick("Активные комоненты препаратов") }) //ActiveSubstance
        DrawerItem(
            text = "Виды лекарства",
            onClick = { onMenuItemClick("Виды лекарства") }) // MedicinalForm
        DrawerItem(text = "ГОСТы", onClick = { onMenuItemClick("ГОСТы") })
        DrawerItem(
            text = "Стандарты изготовления",
            onClick = { onMenuItemClick("Стандарты изготовления") })
        DrawerItem(
            text = "Статусы изготовления",
            onClick = { onMenuItemClick("Статусы изготовления") })

        Spacer(modifier = Modifier.weight(1f))
        DrawerItem(text = "Выход", onClick = { onMenuItemClick("Выход") })
    }


}

