package com.example.moodtracker

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.*
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController


@ExperimentalMaterial3Api
@Composable

fun RegistrationScreen(navController: NavController) {
    val context = LocalContext.current
    val name = remember { mutableStateOf(TextFieldValue()) }
    val email = remember { mutableStateOf(TextFieldValue()) }
    val password= remember { mutableStateOf(TextFieldValue()) }
    val confirmPassword = remember { mutableStateOf(TextFieldValue()) }

    val focusManager = LocalFocusManager.current

    val nameErrorState= remember { mutableStateOf(false)}
    val emailErrorState = remember { mutableStateOf(false) }
    val passwordErrorState = remember { mutableStateOf(false) }
    val confirmPasswordErrorState = remember { mutableStateOf(false) }
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ) {
        Text(
            text = "Register Mood Tracker",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(15.dp)
        )

        OutlinedTextField(
            value = name.value,
            onValueChange = {
                if (nameErrorState.value) {
                    nameErrorState.value = false
                }
                name.value = it
            },
            label = { Text(text = "Name")},
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth(),
            keyboardActions = KeyboardActions(
                onNext = {focusManager.moveFocus(FocusDirection.Down)}
            ),

        )
        if (nameErrorState.value) {
            Text(text = "Required", color = Color.Red, fontStyle = FontStyle.Italic)
        }

        OutlinedTextField(
            value = email.value,
            onValueChange = {
                if (emailErrorState.value){
                    emailErrorState.value = false
                }
                email.value = it
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth(),
            isError = emailErrorState.value,
            label = {
                Text(text = "Email")
            },
            keyboardActions = KeyboardActions(
                onNext = {focusManager.moveFocus(FocusDirection.Down)}
            ),
        )
        if (emailErrorState.value) {
            Text(text = "Required", color = Color.Red, fontStyle = FontStyle.Italic)
        }

        val passwordVisibility = remember { mutableStateOf(true) }
        OutlinedTextField(
            value = password.value,
            onValueChange = {
                if (passwordErrorState.value) {
                    passwordErrorState.value = false
                }
                password.value = it
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = {focusManager.moveFocus(FocusDirection.Down)}
            ),
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text(text = "Password")
            },
            isError = passwordErrorState.value,
            trailingIcon = {
                IconButton(onClick = {
                    passwordVisibility.value = !passwordVisibility.value
                }) {
                    Icon(
                        imageVector = if (passwordVisibility.value) {
                            Icons.Default.VisibilityOff
                        } else
                        Icons.Default.Visibility,
                        contentDescription = "visibility",
                        tint = Color.Gray
                    )
                }
            },
            visualTransformation = if (passwordVisibility.value) {
                PasswordVisualTransformation()}
            else {
                VisualTransformation.None
            }
        )
        if (passwordErrorState.value) {
            Text(text = "Required", color = Color.Red, fontStyle = FontStyle.Italic)
        }

        val cPasswordVisibility = remember { mutableStateOf(true) }
        OutlinedTextField(
            value = confirmPassword.value,
            onValueChange = {
                if (confirmPasswordErrorState.value){
                    confirmPasswordErrorState.value = false
                }
                confirmPassword.value = it
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = {focusManager.moveFocus(FocusDirection.Down)}
            ),
            modifier = Modifier.fillMaxWidth(),
           isError = confirmPasswordErrorState.value,
            label = {
                Text(text = "Confirm Password")
            },
            trailingIcon = {
                IconButton(onClick = {
                    cPasswordVisibility.value = !cPasswordVisibility.value

                }) {
                    Icon(
                        imageVector = if (cPasswordVisibility.value) {
                            Icons.Default.VisibilityOff
                        }
                    else{
                        Icons.Default.Visibility
                        },
                        contentDescription = "visibility",
                        tint = Color.Gray

                    )
                }
            },
            visualTransformation = if (cPasswordVisibility.value){
                PasswordVisualTransformation()
            }
        else {
            VisualTransformation.None
            }
        )
        if (confirmPasswordErrorState.value){
            val msg = if (confirmPassword.value.text.isEmpty()){
                "Required"
            }else if (confirmPassword.value.text != password.value.text){
                "Password not matching"
            }else {
                ""
            }
            Text(text = msg, color = Color.Red, fontStyle = FontStyle.Italic)
        }
        Spacer(Modifier.size(16.dp))
        Button(onClick = {
            when {
                name.value.text.isEmpty() -> {
                    nameErrorState.value = true
                }
                email.value.text.isEmpty() -> {
                    emailErrorState.value = true
                }
                password.value.text.isEmpty() -> {
                    passwordErrorState.value = true
                }
                confirmPassword.value.text.isEmpty() -> {
                    confirmPasswordErrorState.value = true
                }
                else -> {
                    Toast.makeText(
                        context,
                        "Registered successfully",
                        Toast.LENGTH_SHORT
                    ).show()
                    navController.navigate("login_screen"){
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }
                }
            }
        },
            content = {
                Text(text = "Register")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            colors = ButtonDefaults.buttonColors(colorResource(id = R.color.purple_500))
        )
        Spacer(Modifier.size(16.dp))
        Row(modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center) {
            TextButton(onClick = {
                navController.navigate("login_screen"){
                    popUpTo(navController.graph.startDestinationId)
                    launchSingleTop = true
                }
            }) {
                Text(text = "Login")
            }
        }





    }
}