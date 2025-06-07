package com.erjieisheree.yonuncaconia.ui.screen.setting

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.erjieisheree.yonuncaconia.R
import com.erjieisheree.yonuncaconia.ui.screen.other.CustomBackButton
import com.erjieisheree.yonuncaconia.ui.screen.other.CustomCheckbox
import com.erjieisheree.yonuncaconia.ui.screen.other.CustomSlider

@Composable
fun SettingsMenu(
    back: () -> Unit
) {

    var musicLevel by remember { mutableFloatStateOf(0.5f) }
    var soundsLevel by remember { mutableFloatStateOf(0.5f) }
    var vibration by remember { mutableStateOf(false) }

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

        Column (
            modifier = Modifier
                .padding(20.dp, 0.dp, 20.dp, 20.dp)
                .background(
                    shape = RoundedCornerShape(20.dp),
                    color = MaterialTheme.colorScheme.primary)
                .fillMaxWidth()
            ,
            verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterVertically)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                ,
                verticalArrangement = Arrangement.spacedBy(50.dp, Alignment.CenterVertically)
            ) {
                Column {
                    Text(
                        text = stringResource(R.string.music),
                        modifier = Modifier
                            .padding(10.dp, 0.dp, 0.dp, 0.dp),
                        color = MaterialTheme.colorScheme.onPrimary,
                        style = MaterialTheme.typography.labelMedium
                    )

                    CustomSlider(
                        modifier = Modifier,
                        value = musicLevel,
                        onValueChange = { musicLevel = it }
                    )
                }

                Column {
                    Text(
                        text = stringResource(R.string.sounds),
                        modifier = Modifier
                            .padding(10.dp, 0.dp, 0.dp, 0.dp),
                        color = MaterialTheme.colorScheme.onPrimary,
                        style = MaterialTheme.typography.labelMedium
                    )

                    CustomSlider(
                        modifier = Modifier,
                        value = soundsLevel,
                        onValueChange = { soundsLevel = it }
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                    ,
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(R.string.vibration),
                        modifier = Modifier
                            .padding(10.dp, 0.dp, 0.dp, 0.dp),
                        color = MaterialTheme.colorScheme.onPrimary,
                        style = MaterialTheme.typography.labelMedium
                    )

                    Box(
                        modifier = Modifier
                            .fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        CustomCheckbox(
                            checked = vibration,
                            onCheckedChange = { vibration = it },
                            modifier = Modifier
                        )
                    }
                }
            }
        }
    }
}