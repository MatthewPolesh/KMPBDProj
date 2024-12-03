package org.example.project.presentation.gui.sidemenu

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.example.project.utils.Utilities

@Composable
fun DrawerItem(modifier: Modifier = Modifier,text: String, onClick: (String) -> Unit) {
    Text(
        text = text,
        style = MaterialTheme.typography.caption,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick(text) }
            .padding(Utilities.paddingButton)
    )
}