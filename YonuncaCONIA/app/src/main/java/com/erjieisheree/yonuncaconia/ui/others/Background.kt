package com.erjieisheree.yonuncaconia.ui.others

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.erjieisheree.yonuncaconia.R
import com.erjieisheree.yonuncaconia.ui.theme.YoNuncaCONIATheme


@Composable
fun Background() {

    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp
    
    Box (
        modifier = Modifier
            .background(color = MaterialTheme.colorScheme.background)
            .fillMaxSize()
    ) {
        Ellipse(
            modifier = Modifier
                .offset(250.dp, (-150).dp)
        )
        Line(
            modifier = Modifier
                .offset(0.dp, 300.dp)
        )
        Polygon(
            modifier = Modifier
                .offset(70.dp, 200.dp)
        )
        Star(
            modifier = Modifier
                .offset(300.dp, 400.dp)
        )
    }
}


@Composable
fun Ellipse(modifier: Modifier) {
    Image(
        painter = painterResource(id = R.drawable.ellipse),
        contentDescription = "Ellipse",
        modifier
            .height(300.dp)
            .width(350.dp),
        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.tertiary)
    )
}


@Composable
fun Line(modifier: Modifier) {
    Image(
        painter = painterResource(id = R.drawable.line),
        contentDescription = "Line",
        modifier
            .height(1000.dp)
            .width(1000.dp),
        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.tertiary)
    )
}


@Composable
fun Polygon(modifier: Modifier) {
    Image(
        painter = painterResource(id = R.drawable.polygon),
        contentDescription = "Polygon",
        modifier
            .width(100.dp)
            .height(100.dp),
        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.tertiary)
    )
}


@Composable
fun Star(modifier: Modifier) {
    Image(
        painter = painterResource(id = R.drawable.star),
        contentDescription = "Star",
        modifier
            .width(130.dp)
            .height(130.dp),
        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.tertiary)
    )
}


@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun Preview() {
    YoNuncaCONIATheme {
        Background()
    }
}