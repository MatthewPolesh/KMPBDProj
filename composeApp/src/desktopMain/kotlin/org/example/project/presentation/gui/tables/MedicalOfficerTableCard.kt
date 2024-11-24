package org.example.project.presentation.gui.tables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import org.example.project.presentation.gui.cards.MedicalOfficerCard
import org.example.project.presentation.viewmodels.MedicalOfficerViewModel
import org.example.project.utils.Utilities
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Preview
@Composable
fun MedicalOfficerTableCard(modifier: Modifier = Modifier) {
    val viewModel: MedicalOfficerViewModel = koinViewModel()
    val itemList = viewModel.medicalOfficers.collectAsState()
    LaunchedEffect(Unit){
       viewModel.fetchMedicalOfficers()
    }
    println("Number of GOSTs: ${itemList.value.size}")
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
                items(itemList.value) { item ->
                    MedicalOfficerCard(item)
                }
            }
        }
    }
}