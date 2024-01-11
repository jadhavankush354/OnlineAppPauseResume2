package com.example.onlineapppauseresume

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import com.example.jetpackcomposemvvmretrofitandrecyclerview.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlin.math.log

class MainActivity : ComponentActivity() {
    val mainViewModel by viewModels<MainViewModel> ()
    var cityName: String by mutableStateOf("")
    var findWeather: Boolean by mutableStateOf(false)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
           Column(modifier = Modifier.background(Color.Black).fillMaxSize()) {
               val apiService = ApiService.getInstance()

               if (findWeather)
               {
                   mainViewModel.getMovieList(cityName)
               }
               cityName = outLinedTextField(placeholderName = "Enter city name", keypadType = "Text")
               Column {
                   Button(onClick = {
                       findWeather = true
                   }) {
                       Text(text = "Get weather")
                   }
                   if (findWeather)
                   {
                      println("-------------------------------")
                      println("${cityName}")
                      println("----------------------------")
                       Text(text = "${mainViewModel.weather.main.temp}", color = Color.White)
                   }
               }
           }
        }
    }
}









@Composable
fun outLinedTextField(
    placeholderName: String,
    keypadType: String
): String {
    var field by remember { mutableStateOf("") }
    val keyboardOption = when (keypadType) {
        "Number" -> KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        "Email" -> KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email)
        "Password" -> KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password)
        "NumberPassword" -> KeyboardOptions.Default.copy(keyboardType = KeyboardType.NumberPassword)
        "Ascii" -> KeyboardOptions.Default.copy(keyboardType = KeyboardType.Ascii)
        "Decimal" -> KeyboardOptions.Default.copy(keyboardType = KeyboardType.Decimal)
        "Phone" -> KeyboardOptions.Default.copy(keyboardType = KeyboardType.Phone)
        "Uri" -> KeyboardOptions.Default.copy(keyboardType = KeyboardType.Uri)
        else -> KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text)
    }
    var visualTransformation = when (keypadType) {
        "Password" -> PasswordVisualTransformation()
        else -> VisualTransformation.None // Default to no transformation
    }
    var validationType = "Email"

    when (validationType) {
        "Email" -> !validateEmail(field)
        "Password" -> !validatePassword(field)
        "Number" -> !validateMobileNumber(field)
        "Text" -> !validateLocation(field)
        else -> false
    }
    OutlinedTextField(
        value = field,
        onValueChange = {
            field = it
        },
        placeholder = {
            Text(
                text = placeholderName,
                textAlign = TextAlign.Center,
                color = Color.White,
                modifier = Modifier.fillMaxWidth()
            )
        },
        shape = RoundedCornerShape(35.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        textStyle = TextStyle(
            color = Color.White,
            textAlign = TextAlign.Center,
            fontSize = 18.sp
        ),
        keyboardOptions = keyboardOption,
        visualTransformation = visualTransformation,
    )
    return field
}

fun validatePassword(password: String): Boolean {
    val passwordRegex = Regex("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[^A-Za-z0-9]).{8,}\$")
    return passwordRegex.matches(password)
}

fun validateEmail(email: String): Boolean {
    val emailRegex = Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}\$")
    return emailRegex.matches(email)
}

fun validateMobileNumber(mobileNumber: String): Boolean {
    val mobileNumberRegex = Regex("^[0-9]{10}$")
    return mobileNumberRegex.matches(mobileNumber) && !mobileNumber.any { it.isLetter() }
}

fun validateName(name: String): Boolean {
    return name.isNotBlank() && !name.any { it.isDigit() }
}

fun validateLocation(location: String): Boolean {
    return location.isNotBlank()
}



