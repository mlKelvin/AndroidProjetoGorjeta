package com.example.appgorjetaatividade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appgorjetaatividade.ui.theme.AppGorjetaAtividadeTheme
import java.text.NumberFormat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppGorjetaAtividadeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    //Greeting("Android")
                    MyApp()
                }
            }
        }
    }
}

fun Double.formatCurrency(): String = "%.2f".format(this)

@Composable
fun MyApp() {
    var amount by remember { mutableStateOf("") }
    var tipPercent by rememberSaveable { mutableStateOf(18f) }

    Column(modifier = Modifier.fillMaxSize()) {
        //Amount
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(115.dp)
                .padding(horizontal = 16.dp, vertical = 15.dp)
        ) {
            Text(
                text = "Amount",
                fontSize = 23.sp,
                modifier = Modifier
                    .weight(1f)
                    .padding(top = 32.dp)
            )
            Box(
                Modifier
                    .width(270.dp)
                    .padding(start = 8.dp, top = 8.dp, bottom = 8.dp)
            ) {
                OutlinedTextField(
                    value = amount,
                    onValueChange = { amount = it },
                    label = { Text("Enter the amount") },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Done
                    ),
                    modifier = Modifier.fillMaxSize()
                )
            }
        }

        //SeekBar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            val iTipPercent = tipPercent.toInt()
            Text(
                text = "Custom: ${iTipPercent}%",
                fontSize = 23.sp,
                modifier = Modifier
                    .weight(1f)
                    .padding(top = 9.dp)
            )
            Slider(
                value = tipPercent,
                onValueChange = { tipPercent = it },
                valueRange = 0f..30f,
                steps = 30,
                modifier = Modifier.width(220.dp)
            )
        }

        //Percentuais
        Spacer(modifier = Modifier.height(15.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = "15%",
                modifier = Modifier
                    .weight(1f)
                    .wrapContentWidth(Alignment.End)
            )
            Text(
                text = "Tip: ${NumberFormat.getPercentInstance().format(tipPercent / 100.0)}",
                modifier = Modifier
                    .weight(1f)
                    .wrapContentWidth(Alignment.End)
            )
        }

        //Valores considerando percentuais
        val totAmount = amount.toFloatOrNull() ?: 0f

        val percent15ofAmount = totAmount * 0.15
        val percentSeekofAmount = totAmount * (tipPercent / 100.0)

        val totAmountWith15perct = totAmount * 1.15
        val totAmountWithSeekPercent = totAmount + (totAmount * (percentSeekofAmount / 100))

        Spacer(modifier = Modifier.height(15.dp))
        //percentual do total
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Text(
                "Tip:   $${percent15ofAmount.formatCurrency()}",
                modifier = Modifier
                    .weight(1f)
                    .wrapContentWidth(Alignment.End)
                    .background(Color.LightGray)
                    .border(1.dp, color = Color.LightGray),
            )
            Text(
                text = "Tip:   $${percentSeekofAmount.formatCurrency()}",
                modifier = Modifier
                    .weight(1f)
                    .wrapContentWidth(Alignment.End)
                    .background(Color.LightGray)
                    .border(1.dp, color = Color.LightGray),
            )
        }
        Spacer(modifier = Modifier.height(15.dp))
        //total
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Text(
                "Total: $${totAmountWith15perct.formatCurrency()}",
                modifier = Modifier
                    .weight(1.0f)
                    .wrapContentWidth(Alignment.End)
                    .background(Color.LightGray)
                    .border(1.dp, color = Color.LightGray),
            )
            Text(
                "Total: $${totAmountWithSeekPercent.formatCurrency()}",
                modifier = Modifier
                    .weight(1.0f)
                    .wrapContentWidth(Alignment.End)
                    .background(Color.LightGray)
                    .border(1.dp, color = Color.LightGray),
            )
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AppGorjetaAtividadeTheme {
        //Greeting("Android")
        MyApp()
    }
}