package org.example.project.presentation.gui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
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
import org.example.project.presentation.gui.cards.filters.FilterActiveSubstanceCard
import org.example.project.presentation.gui.cards.filters.FilterMedicineCard
import org.example.project.presentation.gui.sidemenu.CustomDrawerShape
import org.example.project.presentation.gui.sidemenu.DrawerContent
import org.example.project.presentation.gui.tables.ActiveSubstanceTableCard
import org.example.project.presentation.gui.tables.GostTableCard
import org.example.project.presentation.gui.tables.MedicalOfficerTableCard
import org.example.project.presentation.gui.tables.MedicinalFormTableCard
import org.example.project.presentation.gui.tables.MedicineTableCard
import org.example.project.presentation.gui.tables.ReportTableCard
import org.example.project.presentation.gui.tables.SpecialityTableCard
import org.example.project.presentation.gui.tables.StandardTableCard
import org.example.project.presentation.gui.tables.StatusTableCard
import org.example.project.utils.Utilities


@Composable
fun MainScreen(
    onLogOut: () -> Unit,
) {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    var checkboxState by remember { mutableStateOf(false) }
    var filtersState by remember{mutableStateOf(false)}
    var tableName by remember{mutableStateOf("Отчёты")}
    val snackbarHostState = remember{ SnackbarHostState()}


    if (filtersState)
        when (tableName)
        {
            "Активные комоненты препаратов" ->{
                FilterActiveSubstanceCard(
                    onAccept = {
                        filtersState = false
                        checkboxState = true
                    },
                    onDismiss = {
                        filtersState = false
                        checkboxState = false
                    }
                )
            }
            "Лекарства" -> {
                FilterMedicineCard(
                    onAccept = {
                        filtersState = false
                        checkboxState = true
                    },
                    onDismiss = {
                        filtersState = false
                        checkboxState = false
                    }
                )
            }

        }



    Scaffold(
        scaffoldState = scaffoldState,
        drawerGesturesEnabled = true,
        drawerShape = CustomDrawerShape(200.dp),
        snackbarHost = { SnackbarHost(hostState = snackbarHostState)},
        drawerContent = {
            DrawerContent {
                when(it){
                    "Выход" -> onLogOut()
                    "Таблицы" -> {}
                    else -> tableName = it
                }
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
                    text = "Таблицы",
                    modifier = Modifier
                        .clickable { scope.launch { scaffoldState.drawerState.open() } }
                        .padding(Utilities.paddingButton))
                Text(text = tableName, modifier = Modifier.padding(Utilities.paddingButton))
                Spacer(modifier = Modifier.weight(1f))
                Text(text = "Фильтры", modifier = Modifier.clickable { filtersState = !filtersState }.padding(
                    Utilities.paddingButton
                ))
            }
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            when(tableName)
            {
                "Отчёты" -> ReportTableCard(
                    onError = { scope.launch { snackbarHostState.showSnackbar(message = it) }})
                "Сотрудники" -> MedicalOfficerTableCard(
                    onError = { scope.launch { snackbarHostState.showSnackbar(message = it) }})
                "Специальности" -> SpecialityTableCard(
                    onError = { scope.launch { snackbarHostState.showSnackbar(message = it) }})
                "Лекарства" -> MedicineTableCard(
                    onError = { scope.launch { snackbarHostState.showSnackbar(message = it) }})
                "Активные комоненты препаратов" -> ActiveSubstanceTableCard(
                    onError = { scope.launch { snackbarHostState.showSnackbar(message = it) }})
                "Виды лекарства"-> MedicinalFormTableCard(
                    onError = { scope.launch { snackbarHostState.showSnackbar(message = it) }})
                "Стандарты изготовления" -> StandardTableCard(
                    onError = { scope.launch { snackbarHostState.showSnackbar(message = it) }})
                "ГОСТы" -> GostTableCard(
                    onError = { scope.launch { snackbarHostState.showSnackbar(message = it) }})
                "Статусы изготовления"-> StatusTableCard(
                    onError = { scope.launch { snackbarHostState.showSnackbar(message = it) }})
            }

        }
    }
}

