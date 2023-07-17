package com.example.englishwordspetproject.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeScreen() {
    Column(verticalArrangement = Arrangement.Center,
           horizontalAlignment = Alignment.CenterHorizontally,
           modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 30.dp)
    ) {
        Button(onClick = { /*TODO*/ },
               shape = RoundedCornerShape(50.dp),
               contentPadding = PaddingValues(22.dp),
               modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .fillMaxWidth(0.7f)
                            .padding(bottom = 20.dp)
        ) {
            Text(text = "Start", textAlign = TextAlign.Center, fontSize = 56.sp)
        }

        Button(onClick = { /*TODO*/ },
               shape = RoundedCornerShape(50.dp),
               contentPadding = PaddingValues(22.dp),
               modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .fillMaxWidth(0.7f)
        ) {
            Text(text = "Dictionary", textAlign = TextAlign.Center, fontSize = 56.sp)
        }
    }
}



@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}