package com.example.moodtracker

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


@ExperimentalMaterial3Api
@Composable
fun LoginScreen(
    onLoginClick: (String, String, auth: FirebaseAuth) -> Unit,
    onSignUpClick: () -> Unit
) {

    val auth by lazy {
        Firebase.auth
    }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
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

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text(text = "Password") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            keyboardActions = KeyboardActions(
                onDone = {focusManager.clearFocus()}
            )
        )
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

                }},
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(text = "Log In")
        }

        TextButton(
            onClick = onSignUpClick,
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Don't have an account? Sign up here")
        }
    }

}






