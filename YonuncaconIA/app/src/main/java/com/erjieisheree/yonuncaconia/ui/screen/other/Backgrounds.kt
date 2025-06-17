package com.erjieisheree.yonuncaconia.ui.screen.other

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.erjieisheree.yonuncaconia.R
import com.erjieisheree.yonuncaconia.ui.theme.YoNuncaConIATheme
import androidx.compose.animation.core.*
import androidx.compose.runtime.*
import androidx.compose.ui.draw.rotate

@Composable
fun SimpleBackground() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
    )
}

@Composable
fun GeneralBackground(animated: Boolean = false) {
    val infiniteTransition = if (animated) rememberInfiniteTransition() else null

    val rotationAngle by infiniteTransition?.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 4000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    ) ?: remember { mutableFloatStateOf(0f) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
        ,
        contentAlignment = Alignment.Center
    ) {
        Ellipse(
            modifier = Modifier
                .offset(180.dp, (-445).dp)
                .rotate(rotationAngle)
        )
        Line(
            modifier = Modifier
                .offset(0.dp, 280.dp)
                .rotate(rotationAngle)
        )
        Polygon(
            modifier = Modifier
                .offset((-100).dp, (-250).dp)
                .rotate(rotationAngle)
        )
        Star(
            modifier = Modifier
                .offset(175.dp, 150.dp)
                .rotate(rotationAngle)
        )
    }
}


@Composable
fun Ellipse(modifier: Modifier) {
    Image(
        painter = painterResource(id = R.drawable.ellipse),
        contentDescription = stringResource(R.string.desc_ellipse),
        modifier
            .height(180.dp)
            .width(350.dp),
        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.tertiary)
    )
}


@Composable
fun Line(modifier: Modifier) {
    Image(
        painter = painterResource(id = R.drawable.line),
        contentDescription = stringResource(R.string.desc_line),
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
        contentDescription = stringResource(R.string.desc_polygon),
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
        contentDescription = stringResource(R.string.desc_star),
        modifier
            .width(110.dp)
            .height(110.dp),
        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.tertiary)
    )
}