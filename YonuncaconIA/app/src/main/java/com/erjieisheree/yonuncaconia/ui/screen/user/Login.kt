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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.erjieisheree.yonuncaconia.R
import com.erjieisheree.yonuncaconia.ui.screen.other.CustomButton
import com.erjieisheree.yonuncaconia.ui.screen.other.CustomTextField
import com.erjieisheree.yonuncaconia.viewmodel.user.UserViewModel
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    navigateToProfile: () -> Unit,
    navigateToSignUp: () -> Unit,
    userViewModel: UserViewModel
) {
    var user by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val id by userViewModel.userId.collectAsState()

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
            text = stringResource(R.string.title_login),
            color = MaterialTheme.colorScheme.onPrimary,
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.weight(1f))

        //Form
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(50.dp, Alignment.CenterVertically)
        ) {

            //User
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
                    validation = { userNotExist(tried) },
                    hide = false,
                    trailingIcon = null
                )
            }

            //Password
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

        val coroutineScope = rememberCoroutineScope()
        //Button
        CustomButton(
            title = stringResource(R.string.title_login),
            onClick = {
                coroutineScope.launch{
                    userViewModel.loginUser(user, password)
                    if (id != "0") navigateToProfile()
                    tried = true
                }
            },
            modifier = Modifier
                .height(90.dp)
                .width(160.dp),
            cornerShape = 40,
                style = MaterialTheme.typography.labelMedium
        )

        Spacer(modifier = Modifier.weight(0.5f))

        Text(
            text = stringResource(R.string.lbl_not_user),
            modifier = Modifier
                .clickable { navigateToSignUp() }
            ,
            color = MaterialTheme.colorScheme.onPrimary,
            style = MaterialTheme.typography.labelSmall
        )
    }
}

private fun userNotExist(tried: Boolean): String? {
    return when {
        tried -> "User or password incorrect"
        else -> null
    }
}
