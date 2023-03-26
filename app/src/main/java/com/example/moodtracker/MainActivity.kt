package com.example.moodtracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.moodtracker.ui.theme.MoodTrackerTheme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    private val auth by lazy {
        Firebase.auth
    }
    companion object {
        val TAG : String = MainActivity::class.java.simpleName
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MoodTrackerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = colorResource(id = R.color.background_color)
                ) {
                    //Log in page
                   /* LoginScreen(onLoginClick =  {
                        email, password, auth -> },
                        onSignUpClick = {auth})*/
                /* RegistrationPage(onSignupClick){}*/
                 LoginAndRegistration()


            }
        }
    }
}
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginAndRegistration(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "login_screen", builder = {
        composable("login_screen", content = { LoginScreen(
            onLoginClick = {email, password, auth ->},
            onSignUpClick = { /*TODO*/ },
            navController = navController
        )})
        composable("register_screen", content = { RegistrationScreen(navController = navController )})
    })
}

/*
@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun RegistrationPagePreview(){
    RegistrationScreen()
}
*/
