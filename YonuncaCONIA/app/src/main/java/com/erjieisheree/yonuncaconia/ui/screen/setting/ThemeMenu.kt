package com.erjieisheree.yonuncaconia.ui.screen.setting

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.erjieisheree.yonuncaconia.R
import com.erjieisheree.yonuncaconia.ui.screen.other.CustomBackButton
import com.erjieisheree.yonuncaconia.ui.screen.other.CustomBtn

@Composable
fun ThemeMenu(
    back: () -> Unit,
    darkTheme: (Boolean) -> Unit
) {
    Column (
        modifier = Modifier
            .padding(20.dp)
            .background(
                shape = RoundedCornerShape(20.dp),
                color = MaterialTheme.colorScheme.secondary)
            .fillMaxWidth()
            .clickable(
                enabled = false,
                onClick = {}
            )
        ,
        verticalArrangement = Arrangement.Center
    ) {
        CustomBackButton(
            modifier = Modifier
                .padding(20.dp),
            onClick = back
        )

        Box (
            modifier = Modifier
                .padding(20.dp, 0.dp, 20.dp, 20.dp)
                .background(
                    shape = RoundedCornerShape(20.dp),
                    color = MaterialTheme.colorScheme.primary)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(50.dp)
                ,
                verticalArrangement = Arrangement.spacedBy(50.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(R.string.lbl_select_theme),
                    modifier = Modifier,
                    color = MaterialTheme.colorScheme.onPrimary,
                    style = MaterialTheme.typography.labelMedium
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Spacer(
                        modifier = Modifier
                            .weight(1f)
                    )
                    CustomBtn(
                        onClick = { darkTheme(false) },
                        paintId = 0,
                        containerColor = colorResource(R.color.light_background),
                        modifier = Modifier
                            .width(75.dp)
                            .height(75.dp)
                    )
                    Spacer(
                        modifier = Modifier
                            .weight(1f)
                    )
                    CustomBtn(
                        onClick = { darkTheme(true) },
                        paintId = 0,
                        containerColor = colorResource(R.color.dark_background),
                        modifier = Modifier
                            .width(75.dp)
                            .height(75.dp)
                    )
                    Spacer(
                        modifier = Modifier
                            .weight(1f)
                    )
                }
            }
        }
    }
}