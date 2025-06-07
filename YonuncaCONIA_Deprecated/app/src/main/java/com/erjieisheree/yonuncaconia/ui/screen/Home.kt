package com.erjieisheree.yonuncaconia.ui.screen

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.erjieisheree.yonuncaconia.R
import com.erjieisheree.yonuncaconia.ui.others.Background
import com.erjieisheree.yonuncaconia.ui.theme.YoNuncaCONIATheme


@Composable
fun HomeScreen(navController: NavController) {
    Scaffold () { innerPadding ->
        Background()
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(innerPadding)
                .padding(30.dp, 70.dp, 30.dp, 0.dp)
                .fillMaxSize()
        ) {
            Row () {
                UserButton(
                    modifier = Modifier
                        .weight(75f)
                )
                Spacer(
                    modifier = Modifier
                        .weight(5f)
                )
                MenuButton(modifier = Modifier
                    .weight(20f))
            }
            Spacer(
                modifier = Modifier
                    .weight(1f)
            )
            PlayButton(
                navController
            )
            Spacer(
                modifier = Modifier
                    .weight(1f)
            )
        }
    }
}


@Composable
fun UserButton(modifier: Modifier) {
    Column (
        modifier = modifier
            .clip(RoundedCornerShape(20))
            .background(color = MaterialTheme.colorScheme.secondary)
    ) {
        Row (
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(10.dp, 20.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.menu),
                contentDescription = "User",
                modifier = Modifier
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.onPrimary)
                    .weight(30f)
            )
            Spacer(
                modifier = Modifier
                    .weight(5f)
            )
            Text(
                text = "ErjieIsHeree",
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier
                    .weight(65f)
            )
        }
    }
}


@Composable
fun MenuButton(modifier: Modifier) {

    Image(
        painter = painterResource(id = R.drawable.menu),
        contentDescription = "Menu",
        modifier
            .clickable { /*TODO*/ }
            .clip(RoundedCornerShape(20))
            .background(MaterialTheme.colorScheme.primary),
        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onPrimary)
    )
}


@Composable
fun PlayButton(navController: NavController) {

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp

    Button (
        onClick = {navController.navigate("pregame")},
        modifier = Modifier
            .clip(RoundedCornerShape(20))
            .background(color = MaterialTheme.colorScheme.primary)
            .width(screenWidth * 0.4f)
    ) {
        Text(
            text = "GAME",
            color = MaterialTheme.colorScheme.onPrimary,
            style = MaterialTheme.typography.titleMedium
        )
    }
}


@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, apiLevel = 34)
@Composable
private fun Preview() {
    YoNuncaCONIATheme {
        HomeScreen(rememberNavController())
    }
}