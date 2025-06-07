package com.erjieisheree.yonuncaconia.ui.screen.game

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.erjieisheree.yonuncaconia.R
import com.erjieisheree.yonuncaconia.ui.screen.other.GeneralBackground
import com.erjieisheree.yonuncaconia.ui.screen.other.CustomBackButton
import com.erjieisheree.yonuncaconia.ui.screen.other.CustomBtn

@Composable
fun GameScreen(
    backToHome: () -> Unit
) {

    GeneralBackground(false)

    //Back button
    CustomBackButton(
        modifier = Modifier
            .padding(30.dp, 50.dp, 0.dp, 0.dp)
        ,
        backToHome
    )

    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(37.dp, 150.dp, 37.dp, 70.dp)
        ,
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondary
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 20.dp
        )
    ) {
        Column (
            modifier = Modifier
                .fillMaxSize()
            ,
            verticalArrangement = Arrangement.Center
        ){
            Spacer(
                modifier = Modifier
                    .weight(1f)
            )
            Text(
                text = stringResource(R.string.title_card),
                modifier = Modifier
                    .fillMaxWidth()
                ,
                color = MaterialTheme.colorScheme.onPrimary,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.displayMedium
            )
            Spacer(
                modifier = Modifier
                    .weight(3f)
            )
            Text(
                text = "hola", //TODO
                modifier = Modifier
                    .fillMaxWidth()
                ,
                color = MaterialTheme.colorScheme.onPrimary,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.labelLarge
            )
            Spacer(
                modifier = Modifier
                    .weight(3f)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                ,
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.Bottom
            ) {
                Spacer(
                    modifier = Modifier
                        .weight(1f)
                )
                CustomBtn(
                    onClick = backToHome,
                    paintId = R.drawable.cross
                )
                Spacer(
                    modifier = Modifier
                        .weight(1f)
                )
                CustomBtn(
                    onClick = backToHome,
                    paintId = R.drawable.tick
                )
                Spacer(
                    modifier = Modifier
                        .weight(1f)
                )
            }
            Spacer(
                modifier = Modifier
                    .weight(1f)
            )
        }
    }
}