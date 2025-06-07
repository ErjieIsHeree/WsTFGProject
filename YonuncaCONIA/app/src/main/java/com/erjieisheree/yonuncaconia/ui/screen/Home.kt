package com.erjieisheree.yonuncaconia.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.erjieisheree.yonuncaconia.R
import com.erjieisheree.yonuncaconia.ui.screen.other.GeneralBackground
import com.erjieisheree.yonuncaconia.ui.screen.other.CustomButton


@Composable
fun HomeScreen(
    navigateToGame: () -> Unit,
    showBlock: () -> Unit
) {
    GeneralBackground(false)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(35.dp, 50.dp, 35.dp, 50.dp)
        ,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Row(
            modifier = Modifier,
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Top

        ) {
            Card(
                modifier = Modifier
                    .width(270.dp)
                    .height(100.dp)
                ,
                shape = RoundedCornerShape(10f),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.secondary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                )
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                    ,
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = stringResource(R.string.user_name),
                        color = MaterialTheme.colorScheme.onPrimary,
                        style = MaterialTheme.typography.labelLarge
                    )
                }
            }

            Spacer(
                modifier = Modifier
                    .weight(1f)
            )

            Box(
                modifier = Modifier
                    .width(50.dp)
                    .height(50.dp)
                    .background(color = MaterialTheme.colorScheme.primary, shape = RoundedCornerShape(10f))
                    .clickable{ showBlock() }
                ,
                contentAlignment = Alignment.Center,
            ) {
                Image(
                    painter = painterResource(R.drawable.menu),
                    contentDescription = stringResource(R.string.desc_menu),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(5.dp)
                    ,
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onPrimary)
                )
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CustomButton(
                title = stringResource(R.string.game),
                onClick = navigateToGame,
                modifier = Modifier
                    .height(80.dp)
                    .width(180.dp)
            )
        }
    }
}