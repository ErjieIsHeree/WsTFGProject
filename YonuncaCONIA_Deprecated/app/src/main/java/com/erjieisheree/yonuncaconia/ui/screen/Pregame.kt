package com.erjieisheree.yonuncaconia.ui.screen

import android.content.res.Configuration
import android.renderscript.ScriptGroup.Input
import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.VectorProperty
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.erjieisheree.yonuncaconia.R
import com.erjieisheree.yonuncaconia.ui.others.BackButtom
import com.erjieisheree.yonuncaconia.ui.others.Background
import com.erjieisheree.yonuncaconia.ui.others.Star
import com.erjieisheree.yonuncaconia.ui.theme.YoNuncaCONIATheme
import kotlin.math.exp
import com.erjieisheree.yonuncaconia.controller.GameCreate

public var userInput by mutableStateOf("")
var predefinedOption by mutableStateOf("")
var selectedQty by mutableIntStateOf(5)
var isPredefined by mutableStateOf(false)


@Composable
fun PregameScreen(navController: NavController) {
    Background()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp, 70.dp, 30.dp, 0.dp)
    ) {
        BackButtom(
            navController,
            modifier = Modifier
                .width(50.dp)
                .weight(1f)
        )
        Column (
            modifier = Modifier
                .fillMaxSize()
                .weight(15f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            UserCustomInput(
                modifier = Modifier
                    .weight(1f)
            )
            UserPredefinedInput(
                modifier = Modifier
                    .weight(1f)
            )
            CardsQty(
                modifier = Modifier
                    .weight(1f)
            )
            IsUserInput()
            Box(
                modifier = Modifier
                    .weight(1f)
            ) {
                StartButton(
                    navController
                )
            }
        }
    }
}


@Composable
fun UserCustomInput(modifier: Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = "Introduce el tópico que deseas que tenga el juego:",
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onPrimary,
            textAlign = TextAlign.Center
        )

        TextField(
            value = userInput,
            onValueChange = { userInput = it },
            modifier = Modifier
                .clip(RoundedCornerShape(0))
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserPredefinedInput(modifier: Modifier = Modifier) {
    val predefinedGames = listOf("Opción 1", "Opción 2", "Opción 3")
    var expandedSelection by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox (
        expanded = expandedSelection,
        onExpandedChange = { expandedSelection = !expandedSelection },
        modifier = modifier
    ) {
        OutlinedTextField(
            readOnly = true,
            value = predefinedOption.toString(),
            onValueChange = {},
            label = { Text("o selecciona uno predefinido") },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedSelection)
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors(),
            modifier = Modifier
                .menuAnchor() // IMPORTANTE: vincula el TextField con el menú
                .fillMaxWidth()
        )

        ExposedDropdownMenu(
            expanded = expandedSelection,
            onDismissRequest = { expandedSelection = false }
        ) {
            predefinedGames.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = {
                        predefinedOption = option
                        expandedSelection = false
                    }
                )
            }
        }
    }
}


/*
@Composable
fun UserPredefinedInput(modifier: Modifier) {
    val options = listOf("Opción 1", "Opción 2", "Opción 3")
    var expanded by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf(options[0]) }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Text(
            text = "o selecciona uno predefinido:",
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onPrimary,
            textAlign = TextAlign.Center
        )
        Button(
            shape = ButtonDefaults.shape,
            onClick = { expanded = !expanded },
            modifier = Modifier
                .clip(RoundedCornerShape(10))
                .background(MaterialTheme.colorScheme.onPrimary),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.onPrimary,
                contentColor = MaterialTheme.colorScheme.onSecondary

            )
        ) {
            Text(
                text = selectedOption,
                style = MaterialTheme.typography.labelMedium,
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = {
                        selectedOption = option
                        expanded = false
                    }
                )
            }
        }
    }
}*/


@Composable
fun IsUserInput() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "¿Usar tópico predefinido?",
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onPrimary,
            textAlign = TextAlign.Center
        )
        Checkbox(
            checked = isPredefined,
            onCheckedChange = {
                isPredefined = it
            }
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardsQty(modifier: Modifier = Modifier) {
    val cardsQty = listOf(5, 10, 15, 20, 25)
    var expandedQty by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox (
        expanded = expandedQty,
        onExpandedChange = { expandedQty = !expandedQty },
        modifier = modifier
    ) {
        OutlinedTextField(
            readOnly = true,
            value = selectedQty.toString(),
            onValueChange = {},
            label = { Text("Cantidad de cartas") },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedQty)
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors(),
            modifier = Modifier
                .menuAnchor() // IMPORTANTE: vincula el TextField con el menú
                .fillMaxWidth()
        )

        ExposedDropdownMenu(
            expanded = expandedQty,
            onDismissRequest = { expandedQty = false }
        ) {
            cardsQty.forEach { qtyOption ->
                DropdownMenuItem(
                    text = { Text(qtyOption.toString()) },
                    onClick = {
                        selectedQty = qtyOption
                        expandedQty = false
                    }
                )
            }
        }
    }
}


/*@Composable
fun CardsQty(modifier: Modifier) {
    val cardsQty = listOf(5, 10, 15, 20, 25)
    var expanded by remember { mutableStateOf(false) }
    var selectedQty by remember { mutableIntStateOf(cardsQty[0]) }

    Row (
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically

    ) {
        Text(
            text = "Cantidad de cartas:",
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onPrimary,
            textAlign = TextAlign.Center
        )
        Button(
            shape = ButtonDefaults.shape,
            onClick = { expanded = !expanded },
            modifier = Modifier
                .clip(RoundedCornerShape(10))
                .background(MaterialTheme.colorScheme.onPrimary),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.onPrimary,
                contentColor = MaterialTheme.colorScheme.onSecondary

            )
        ) {
            Text(
                text = selectedQty.toString(),
                style = MaterialTheme.typography.labelMedium
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            offset = DpOffset(0.dp, 0.dp)
        ) {
            cardsQty.forEach{ qtyOption ->
                DropdownMenuItem(
                    text = { Text(qtyOption.toString()) },
                    onClick = {
                        selectedQty = qtyOption
                        expanded = false
                    }
                )
            }
        }
    }
}*/


@Composable
fun StartButton(navController: NavController) {

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp

    Button (
        onClick = {
            GameCreate.getInstance().loadGame(userInput, predefinedOption, selectedQty, isPredefined);
        },
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


@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, apiLevel = 34)
@Composable
private fun Preview() {
    YoNuncaCONIATheme {
        PregameScreen(rememberNavController())
    }
}