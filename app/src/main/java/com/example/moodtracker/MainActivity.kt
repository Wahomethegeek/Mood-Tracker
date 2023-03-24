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
import androidx.compose.ui.tooling.preview.Preview
import com.example.moodtracker.ui.theme.MoodTrackerTheme
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
                    /*LoginScreen(onLoginClick =  {
                        email, password, auth -> },
                        onSignUpClick = {auth})*/
                /*    RegistrationPage(onSignupClick){}*/
                    RegistrationScreen()


            }
        }
    }
}
}
@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun RegistrationPagePreview(){
    RegistrationScreen()
}