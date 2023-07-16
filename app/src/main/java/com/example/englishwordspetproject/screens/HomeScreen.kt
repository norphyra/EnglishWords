package com.example.englishwordspetproject.screens

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen() {
    Column(horizontalAlignment = Alignment.CenterHorizontally, 
    verticalArrangement = Arrangement.Center) {
        
        Button(onClick = { /*TODO*/ },
        shape = RoundedCornerShape(10.dp)
        ) {
            Text(text = "Top 100", textAlign = TextAlign.Center)
        }
    }
}