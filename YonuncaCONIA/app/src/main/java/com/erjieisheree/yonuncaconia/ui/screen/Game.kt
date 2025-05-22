package com.erjieisheree.yonuncaconia.ui.screen

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.erjieisheree.yonuncaconia.R
import com.erjieisheree.yonuncaconia.ui.others.BackButtom
import com.erjieisheree.yonuncaconia.ui.others.Background
import com.erjieisheree.yonuncaconia.ui.theme.YoNuncaCONIATheme

@Composable
fun GameScreen(navController: NavController) {
    Background()
    BackButtom(navController, modifier = Modifier)
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(0.dp, 30.dp, 0.dp, 0.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondary
        )
    ) {
        Text(
            text = "YO NUNCA...",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onPrimary,
            textAlign = TextAlign.Center
        )

        val gptResponse = { mutableStateOf("holahola") }

        Text(
            text = gptResponse.toString(),
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onPrimary,
            textAlign = TextAlign.Center
        )
        Row {
            Button(
                onClick = { /*TODO*/ }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.cross),
                    contentDescription = "Dislike",
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
                    modifier = Modifier
                        .clickable { navController.popBackStack() }
                )
            }
            Button(
                onClick = { /*TODO*/ }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.tick),
                    contentDescription = "Like",
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
                    modifier = Modifier
                        .clickable { navController.popBackStack() }
                )
            }
        }
    }
}


@Preview (uiMode = Configuration.UI_MODE_NIGHT_YES, apiLevel = 34)
@Composable
private fun Preview() {
    YoNuncaCONIATheme {
        GameScreen(rememberNavController())
    }
}