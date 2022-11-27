package com.example.spacexlaunches.presentation.ui.screens.launches.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.spacexlaunches.data.models.launches.LaunchItem

@Composable
fun launchesListItem(
    launchItem:LaunchItem,
    onItemClick: (LaunchItem)-> Unit
){
    Row(Modifier.fillMaxWidth()
        .clickable { onItemClick(launchItem) }
        .padding(15.dp), horizontalArrangement = Arrangement.SpaceBetween) {
        launchItem.name?.let { it1 -> Text(text = it1) }
        launchItem.date_local?.let { it1 -> Text(text = it1) }

    }

}