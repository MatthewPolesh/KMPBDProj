package org.example.project.presentation.gui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import org.example.project.utils.Utilities

@Composable
fun CustomButton(
    text : String,
    onClick : () -> Unit){
    Text(
        text = text,
        modifier = Modifier
            .padding( vertical = Utilities.paddingButton/4)
            .clip(shape = RoundedCornerShape(Utilities.cornerBox))
            .clickable {onClick()}
            .padding( vertical = Utilities.paddingButton/4) )
}

