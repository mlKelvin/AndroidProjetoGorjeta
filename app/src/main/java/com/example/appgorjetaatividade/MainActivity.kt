package com.example.appgorjetaatividade

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
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
fun Double.formatPercentage(): String = "%.2f%%".format(this)

fun Double.formatCurrency(): String = "%.2f".format(this)

@Composable
fun MyApp() {
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

            var amount by remember { mutableStateOf("") }
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
                    keyboardActions = KeyboardActions(
                        onDone = { /* action to be executed when the keyboard's "Done" button is clicked */ }
                    ),
                    modifier = Modifier.fillMaxSize()
                )
            }
        }

        //SeekBar
        var tipPercent by remember { mutableStateOf(0) }
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Text(
                text = "Custom: ${tipPercent}%",
                fontSize = 23.sp,
                modifier = Modifier
                    .weight(1f)
                    .padding(top = 9.dp)
            )
            Slider(
                value = tipPercent.toFloat(),
                onValueChange = { tipPercent = it.toInt() },
                valueRange = 0f..30f,
                steps = 30,
                modifier = Modifier.width(220.dp)
            )
        }

        //Percentuais
        Spacer(modifier = Modifier.height(15.dp))
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
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
        val totalAmount = 100.0 //val total do pedido

        val tipAmount = totalAmount * tipPercent / 100.0
        val totalWithTip = totalAmount + tipAmount
        Text(
            text = "Tip amount: $${tipAmount.formatCurrency()}",
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )
        Text(
            text = "Total with tip: $${totalWithTip.formatCurrency()}",
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )
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