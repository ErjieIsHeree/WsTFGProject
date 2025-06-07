package com.erjieisheree.yonuncaconia.ui.others

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.erjieisheree.yonuncaconia.R

@Composable
fun BackButtom(navController: NavController, modifier: Modifier) {
    Image(
        painter = painterResource(id = R.drawable.arrow_back),
        contentDescription = "Back",
        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
        modifier = modifier
            .clickable { navController.popBackStack() }
    )
}