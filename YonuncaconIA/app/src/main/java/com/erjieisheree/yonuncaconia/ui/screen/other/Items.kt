package com.erjieisheree.yonuncaconia.ui.screen.other

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.erjieisheree.yonuncaconia.R


@Composable
fun CustomBackButton(
    modifier: Modifier,
    onClick: () -> Unit
) {
    Image(
        painter = painterResource(R.drawable.arrow_back),
        contentDescription = stringResource(R.string.btn_back_home),
        modifier = modifier
            .clickable(onClick = onClick)
            .width(50.dp)
            .height(50.dp)
        ,
        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onPrimary)
    )
}


@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    validation: ((String) -> String?)? = null,
    hide: Boolean,
    trailingIcon: @Composable (() -> Unit)?
) {
    var error: String? = null
    if (validation != null) error = validation(value)

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier
            .fillMaxWidth()
            .height(85.dp)
        ,
        label = { Text(label) },
        trailingIcon = trailingIcon,
        singleLine = true,
        supportingText = { error?.let { Text(it, color = Color.Red) } },
        isError = error != null,
        visualTransformation =
            if (hide) PasswordVisualTransformation() else VisualTransformation.None,
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedContainerColor = MaterialTheme.colorScheme.onPrimary,
            focusedContainerColor = MaterialTheme.colorScheme.onPrimary,
            errorContainerColor = MaterialTheme.colorScheme.onPrimary,
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            errorTextColor = MaterialTheme.colorScheme.onSecondary,
            focusedTextColor = MaterialTheme.colorScheme.onSecondary,
            unfocusedTextColor = MaterialTheme.colorScheme.onSecondary
        )
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextFieldDropdownMenu(
    selectedOption: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier,
    label: String,
    optionList: List<String>
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        @Suppress("DEPRECATION")
        OutlinedTextField(
            value = selectedOption,
            onValueChange = onValueChange,
            modifier = modifier
                .menuAnchor()
                .fillMaxWidth()
            ,
            readOnly = true,
            label = { Text(label) },
            singleLine = true,
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedContainerColor = MaterialTheme.colorScheme.onPrimary,
                focusedContainerColor = MaterialTheme.colorScheme.onPrimary,
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedTextColor = MaterialTheme.colorScheme.onSecondary,
                focusedTextColor = MaterialTheme.colorScheme.onSecondary
            )
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            containerColor = MaterialTheme.colorScheme.onPrimary
        ) {
            optionList.forEach{
                DropdownMenuItem(
                    text = {
                        Text(
                            text = it,
                            color = MaterialTheme.colorScheme.onSecondary,
                            style = MaterialTheme.typography.labelSmall
                        )
                    },
                    onClick = {
                        onValueChange(it)
                        expanded = false
                    }
                )
            }
        }
    }
}


@Composable
fun CustomCheckbox(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier
) {
    Checkbox(
        checked = checked,
        onCheckedChange = onCheckedChange,
        modifier = modifier,
        colors = CheckboxDefaults.colors(
            checkedColor = MaterialTheme.colorScheme.primary,
            checkmarkColor = MaterialTheme.colorScheme.onPrimary
        )
    )
}


@Composable
fun CustomButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    cornerShape: Int = 20,
    title: String,
    style: TextStyle = MaterialTheme.typography.titleLarge,
    containerColor: Color = MaterialTheme.colorScheme.primary
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(cornerShape.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = MaterialTheme.colorScheme.onPrimary
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = title,
                textAlign = TextAlign.Center,
                style = style
            )
        }
    }
}

@Composable
fun CustomBtn(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    paintId: Int,
    containerColor: Color = MaterialTheme.colorScheme.primary
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .height(100.dp)
            .width(100.dp)
        ,
        shape = RoundedCornerShape(1000.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = MaterialTheme.colorScheme.onPrimary
        )
    ) {
        if (paintId != 0) {
            Image(
                painter = painterResource(paintId),
                contentDescription = stringResource(R.string.btn_desc_cross),
                modifier = Modifier.fillMaxSize(),
                alignment = Alignment.Center
            )
        }
    }
}

@Composable
fun CustomSlider(
    modifier: Modifier = Modifier,
    value: Float = 0.5f,
    onValueChange: (Float) -> Unit
) {
    Slider(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .fillMaxWidth()
        ,
        valueRange = 0f..1f,
        steps = 9,
        colors = SliderDefaults.colors(
            activeTrackColor = MaterialTheme.colorScheme.secondary,
            inactiveTrackColor = MaterialTheme.colorScheme.onPrimary,
            thumbColor = MaterialTheme.colorScheme.secondary,
            activeTickColor = MaterialTheme.colorScheme.onPrimary,
            inactiveTickColor = MaterialTheme.colorScheme.secondary
        )
    )
}