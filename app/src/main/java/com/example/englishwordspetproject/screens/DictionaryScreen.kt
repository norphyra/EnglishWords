package com.example.englishwordspetproject.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ExpandMore
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.englishwordspetproject.R
import com.example.englishwordspetproject.data.LanguageViewModel
import com.example.englishwordspetproject.piechart.PieChart
import com.example.englishwordspetproject.piechart.PieChartInput

@Composable
fun DictionaryScreen(languageViewModel: LanguageViewModel) {

    val context = LocalContext.current

    //val languages = mapOf("English" to R.drawable.usa_flag_icon, "Russian" to R.drawable.russian_flag_icon)
    //var selectedLanguage by rememberSaveable { mutableStateOf("English") }
    
    Column(horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp)
    ) {
        TopSectionChooser()

        PieChart(
            radius = 230f,
            innerRadius = 100f,
            padding = listOf(130.dp, 0.dp, 0.dp, 0.dp),
            textColor = Color.Black,
            centerText = "Some text",
            centerTextColor = Color.Black,
            input = listOf(
                PieChartInput(
                    color = Color.Green,
                    value = 29,
                    description = "Python"
                ),
                PieChartInput(
                    color = Color.Gray,
                    value = 21,
                    description = "Swift"
                ),
                PieChartInput(
                    color = Color.Yellow,
                    value = 32,
                    description = "JavaScript"
                )
            )
        )
    }
    
}

@Composable
fun TopSectionChooser() {

    var expanded by rememberSaveable { mutableStateOf(false) }

    var selectedSection by rememberSaveable { mutableStateOf(R.string.my_dictionary) }

    Surface(shape = RoundedCornerShape(20.dp),
        color = MaterialTheme.colorScheme.primaryContainer,
        shadowElevation = 10.dp,
        modifier = Modifier.clickable { expanded = !expanded }
    ) {
        Row(
            Modifier
                .fillMaxWidth(0.4f)
                .padding(start = 30.dp, top = 10.dp, end = 30.dp, bottom = 10.dp), horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = stringResource(selectedSection),
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth())
        }
    }

    if(expanded) {
        Surface(shape = RoundedCornerShape(20.dp),
            color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.6f),
            modifier = Modifier
                .padding(top = 10.dp)
                .clickable {
                    selectedSection =
                        if (selectedSection == R.string.my_dictionary) R.string.my_sets else R.string.my_dictionary
                    expanded = !expanded
                }
        ) {
            Row(
                Modifier
                    .fillMaxWidth(0.4f)
                    .padding(start = 30.dp, top = 10.dp, end = 30.dp, bottom = 10.dp), horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = stringResource(id = if (selectedSection == R.string.my_dictionary) R.string.my_sets else R.string.my_dictionary),
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth(),
                    color = Color.Black.copy(alpha = 0.6f))
            }
        }
    }
}

@Composable
fun expandedLanguageChooser(languageViewModel: LanguageViewModel) {
    val context = LocalContext.current

    val languages = mapOf("English" to R.drawable.usa_flag_icon, "Russian" to R.drawable.russian_flag_icon)

    var expanded by rememberSaveable { mutableStateOf(false) }
    var selectedLanguage by rememberSaveable { mutableStateOf("English") }

    Column(horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp)
    ) {
        Surface(shape = RoundedCornerShape(20.dp), color = MaterialTheme.colorScheme.primaryContainer,
            modifier = Modifier.wrapContentHeight()
        ) {
            Row(
                Modifier
                    .fillMaxWidth(0.4f)
                    .padding(start = 30.dp, top = 10.dp, end = 30.dp, bottom = 10.dp), horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = selectedLanguage, fontSize = 20.sp)
                Icon(imageVector = Icons.Rounded.ExpandMore,
                    contentDescription = "expand more",
                    modifier = Modifier.clickable(onClick = languageViewModel::showBottomSheet))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DictionaryScreenPreview() {
    DictionaryScreen(viewModel())
}