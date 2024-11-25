package org.example.project.presentation.gui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Checkbox
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import org.example.project.presentation.gui.sidemenu.DrawerContent
import org.example.project.presentation.gui.tables.ReportTableCard
import org.example.project.utils.Utilities


@Composable
fun MainScreen() {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    var checkboxState by remember { mutableStateOf(false) }
    var filtersState by remember{mutableStateOf(false)}

    if (filtersState)
        FiltersCard(
            onAccept = {
                filtersState = false
                checkboxState = true
                       },
            onDismiss = {
                filtersState = false
                checkboxState = false
            }
        )

    Scaffold(
        scaffoldState = scaffoldState,
        drawerGesturesEnabled = true,
        drawerShape = CustomDrawerShape(150.dp),
        drawerContent = {
            DrawerContent {
                scope.launch {
                    scaffoldState.drawerState.close()
                }
            }
        },
        topBar = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.background(Color.LightGray)
            ) {
                Text(
                    text = "Меню",
                    modifier = Modifier
                        .clickable { scope.launch { scaffoldState.drawerState.open() } }
                        .padding(Utilities.paddingButton))
                Text(text = "Название таблицы", modifier = Modifier.padding(Utilities.paddingButton))
                Spacer(modifier = Modifier.weight(1f))
                Text(text = "Фильтры", modifier = Modifier.clickable { filtersState = !filtersState }.padding(
                    Utilities.paddingButton
                ))
                Checkbox(checked = checkboxState, onCheckedChange = {checkboxState = !checkboxState})
            }
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            ReportTableCard(modifier = Modifier.weight(0.7f))
            //DescriptionCard(modifier = Modifier.weight(0.3f))
        }
    }
}

