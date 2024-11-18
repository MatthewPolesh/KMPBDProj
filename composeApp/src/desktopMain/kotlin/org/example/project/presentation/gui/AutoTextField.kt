package org.example.project.presentation.gui


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.example.project.utils.Utilities

@Composable
fun AutoTextField(
    suggestions: List<String>,
    modifier: Modifier = Modifier
) {
    var text by remember { mutableStateOf("") }
    var filteredSuggestions by remember { mutableStateOf(listOf<String>()) }


    Column(modifier = modifier.width(170.dp)) {
        OutlinedTextField(
            value = text,
            onValueChange = { newText ->
                text = newText
                filteredSuggestions = if (newText.isNotEmpty()) {
                    suggestions.filter { it.contains(newText, ignoreCase = true) }
                } else {
                    emptyList()
                }
            },
            label = { Text("Введите текст") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        if (filteredSuggestions.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = 200.dp)
            ) {
                items(filteredSuggestions) { suggestion ->
                    Text(
                        text = suggestion,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                text = suggestion
                                filteredSuggestions = emptyList()
                            }
                            .padding(Utilities.paddingExternal)
                    )
                    Divider()
                }
            }
        }
    }
}
