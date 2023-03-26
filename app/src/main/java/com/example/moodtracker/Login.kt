package com.example.moodtracker

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


@ExperimentalMaterial3Api
@Composable
fun LoginScreen(
    onLoginClick: (String, String, auth: FirebaseAuth) -> Unit,
    onSignUpClick: () -> Unit,
    navController: NavController
) {
    val auth by lazy { Firebase.auth }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var emailErrorState = remember { mutableStateOf(false) }
    var passwordErrorState = remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Welcome My Mood Tracker",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(16.dp)
        )

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text(text = "Email") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            keyboardActions = KeyboardActions(
                onNext = {focusManager.moveFocus(FocusDirection.Down)}
            )
        )
        if (emailErrorState.value){
            Text(text = "Required", color = Color.Red, fontStyle = FontStyle.Italic)
        }
var passwordVisibility: Boolean by remember {
    mutableStateOf(false)
}
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text(text = "Password") },
            visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            keyboardActions = KeyboardActions(
                onDone = {focusManager.clearFocus()}
            ),
            trailingIcon = {
                IconButton(onClick = {
                    passwordVisibility = !passwordVisibility
                }) {
                    Icon(
                        imageVector = if (passwordVisibility){
                            Icons.Default.Visibility
                        }
                    else{
                        Icons.Default.VisibilityOff
                    },
                        contentDescription = "visibility",
                    tint = Color.Gray
                    )
                }
            }
        )
        if (passwordErrorState.value){
            Text(text = "Required", color = Color.Red, fontStyle = FontStyle.Italic)
        }
        Row(
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.fillMaxWidth()
        ){
            TextButton(onClick = { /*TODO*/ }) {
              Text(
                  color = Color.Gray,
                  fontStyle = FontStyle.Italic,
                  text = "Forgotten password?",
                  modifier = Modifier
                      .padding(start = 6.dp)
              )
            }
        }

        Button(
            onClick = { auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener{
                    if (it.isSuccessful){
                        Log.d(TAG, "The user has successfully logged in")
                    } else {
                        Log.d(TAG, "The user has failed to log in", it.exception)
                    }
                }
                navController.navigate("login_screen"){
                    popUpTo(navController.graph.startDestinationId)
                    launchSingleTop = true
                }
                      },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(text = "Log In")
        }

        ClickableText(
            modifier = Modifier.padding(16.dp),
            text = buildAnnotatedString {
                append("Don't have an account, ")
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append("Sign up")
                }
            },
            onClick = {
                      navController.navigate("register_screen"){
                          popUpTo(navController.graph.startDestinationId)
                          launchSingleTop = true
                      }
            },
            style = MaterialTheme.typography.bodyMedium.copy(color = colorResource(id = R.color.purple_500))
        )
    }

}








