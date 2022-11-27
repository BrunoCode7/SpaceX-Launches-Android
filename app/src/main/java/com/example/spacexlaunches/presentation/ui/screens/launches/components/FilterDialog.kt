package com.example.spacexlaunches.presentation.ui.screens.launches.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.spacexlaunches.data.enums.Filter


@Composable
fun filterDialog(showDialog:Boolean,
                 optionsList:List<Filter>,
                 selected: Filter,
                 setSelected: (selected: Filter) -> Unit,
                 onDismissRequested:()->Unit){
    if (showDialog) {
        Dialog(onDismissRequest = { onDismissRequested() }) {
            Surface(
                modifier = Modifier.width(300.dp),
                shape = RoundedCornerShape(10.dp)
            ) {
                Column {
                    Text(
                        text = "Filter by:",
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                    optionsList.forEach { item ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = selected == item,
                                onClick = {
                                    setSelected(item)
                                    onDismissRequested()
                                },
                                enabled = true,
                                colors = RadioButtonDefaults.colors(
                                    selectedColor = Color.Gray
                                )
                            )
                            Text(text = item.name, modifier = Modifier.padding(start = 8.dp))
                        }
                    }
                }
            }
        }
    }
}