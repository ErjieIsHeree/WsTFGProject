package com.erjieisheree.yonuncaconia.ui.screen.user

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.erjieisheree.yonuncaconia.R
import com.erjieisheree.yonuncaconia.ui.screen.other.CustomButton
import com.erjieisheree.yonuncaconia.ui.screen.other.CustomTextField

@Composable
fun SignUpScreen() {
    val context = LocalContext.current

    var user by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    //SimpleBackground()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(35.dp, 70.dp, 35.dp, 70.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        var tried by remember { mutableStateOf(false) }

        //Title
        Text(
            text = stringResource(R.string.title_signup),
            color = MaterialTheme.colorScheme.onPrimary,
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.weight(1f))

        //Form
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(50.dp, Alignment.CenterVertically)
        ) {

            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(R.string.lbl_user),
                    modifier = Modifier
                        .padding(20.dp, 0.dp, 0.dp, 0.dp)
                    ,
                    color = MaterialTheme.colorScheme.onPrimary,
                    style = MaterialTheme.typography.labelMedium
                )
                CustomTextField(
                    value = user,
                    onValueChange = { user = it; tried = false },
                    label = stringResource(R.string.lbl_empty),
                    validation = { validateUser(it, tried, context) },
                    hide = false,
                    trailingIcon = null
                )
            }

            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(R.string.lbl_password),
                    modifier = Modifier
                        .padding(20.dp, 0.dp, 0.dp, 0.dp)
                    ,
                    color = MaterialTheme.colorScheme.onPrimary,
                    style = MaterialTheme.typography.labelMedium
                )

                var isPasswordVisible by remember { mutableStateOf(true) }
                CustomTextField(
                    value = password,
                    onValueChange = { password = it; tried = false },
                    label = stringResource(R.string.lbl_empty),
                    validation = { validatePassword(it, tried, context) },
                    hide = isPasswordVisible,
                    trailingIcon = {
                        val image = if (isPasswordVisible) painterResource(R.drawable.eye_on) else painterResource(R.drawable.eye_off)
                        val description = if (isPasswordVisible) stringResource(R.string.eye_on) else stringResource(R.string.eye_off)
                        Image(
                            painter = image,
                            contentDescription = description,
                            modifier = Modifier
                                .height(60.dp)
                                .width(60.dp)
                                .padding(0.dp, 0.dp, 20.dp, 0.dp)
                                .clickable{ isPasswordVisible = !isPasswordVisible }
                        )
                    }
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        //Button
        CustomButton(
            title = stringResource(R.string.title_signup),
            onClick = {
                tried = true
                // TODO
            },
            modifier = Modifier
                .height(80.dp)
                .width(160.dp),
            cornerShape = 40,
            style = MaterialTheme.typography.labelMedium
        )

        Spacer(modifier = Modifier.weight(0.5f))

        Text(
            text = stringResource(R.string.lbl_already_user),
            color = MaterialTheme.colorScheme.onPrimary,
            style = MaterialTheme.typography.labelSmall
        )
    }
}

fun validateUser(user: String, tried: Boolean, context: Context): String? {
    val regex = Regex("^[A-Za-z]+$")

    return when {
        !tried -> null
        user.isBlank() -> context.getString(R.string.error_empty)
        user.length < 3 -> context.getString(R.string.error_less_than_three)
        user.length > 20 -> context.getString(R.string.error_more_than_twenty)
        !regex.matches(user) -> context.getString(R.string.error_invalid)
        else -> null
    }
}

fun validatePassword(password: String, tried: Boolean, context: Context): String? {
    val regex = Regex("^[A-Za-z\\d@\$!%*?&]+$")

    return when {
        !tried -> null
        password.isBlank() -> context.getString(R.string.error_empty)
        password.length < 3 -> context.getString(R.string.error_less_than_three)
        password.length > 20 -> context.getString(R.string.error_more_than_twenty)
        !regex.matches(password) -> context.getString(R.string.error_invalid)
        else -> null
    }
}


@Preview
@Composable
fun SignupScreen() {
    SignUpScreen()
}