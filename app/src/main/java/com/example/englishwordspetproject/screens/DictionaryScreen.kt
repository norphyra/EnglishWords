package com.example.englishwordspetproject.screens

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ExpandMore
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterStart
import androidx.compose.ui.Alignment.Companion.Start
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.englishwordspetproject.R
import com.example.englishwordspetproject.data.viewModels.DictionaryViewModel
import com.example.englishwordspetproject.data.viewModels.LanguageViewModel
import com.example.englishwordspetproject.data.viewModels.WordStatus
import com.example.englishwordspetproject.data.viewModels.sections
import com.example.englishwordspetproject.data.viewModels.words
import com.example.englishwordspetproject.data.viewModels.wordsMap
import com.example.englishwordspetproject.data.viewModels.wordsStatisticInDictionary
import com.example.englishwordspetproject.ui.theme.CustomTextFieldColors
import com.example.englishwordspetproject.ui.theme.in_progress_icon_color
import com.example.englishwordspetproject.ui.theme.learned_icon_color
import com.example.englishwordspetproject.ui.theme.new_word_icon_color
import com.example.englishwordspetproject.utils.CalculatePaddings


@Composable
fun DictionaryScreen(dictionaryViewModel: DictionaryViewModel = viewModel()) {

    val context = LocalContext.current

    val selectedItem by dictionaryViewModel.selectedItem.collectAsState()
    val expanded by dictionaryViewModel.expanded.collectAsState()

    //val sheetState = rememberModalBottomSheetState()

    val scrollState = rememberScrollState()

    val screenHeight = context.resources.displayMetrics.heightPixels
    val screenWidth = context.resources.displayMetrics.widthPixels
    
    Box(modifier = Modifier
        .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = CalculatePaddings(screenHeight, screenWidth)
                .fillMaxSize()
                .verticalScroll(scrollState, enabled = true),
            horizontalAlignment = Start
        ) {
            TextField(value = stringResource(id = selectedItem.sectionName),
                onValueChange = {},
                textStyle = TextStyle(fontSize = 20.sp),
                readOnly = true,
                leadingIcon = { Icon(painter = painterResource(id = R.drawable.top_section_picker_icon),
                    contentDescription = null,
                    modifier = Modifier.padding(start = 20.dp, end = 20.dp)) },
                trailingIcon = { Icon(imageVector = Icons.Rounded.ExpandMore,
                    contentDescription = "expand more",
                    modifier = Modifier
                        .padding(end = 20.dp)
                        .size(24.dp)
                        .clickable { dictionaryViewModel.isExpand(!expanded) })},
                modifier = Modifier
                    .align(Start)
                    .width(420.dp),
                shape = RoundedCornerShape(10.dp),
                colors = TextFieldDefaults.colors(focusedIndicatorColor = Color.Transparent, unfocusedIndicatorColor = Color.Transparent))

            Surface(shape = RoundedCornerShape(20.dp),
                color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f),
                modifier = Modifier.padding(top = 40.dp, bottom = 5.dp)
            ) {
                Text(text = stringResource(id = R.string.dictionary_statistics),
                modifier = Modifier
                    .align(Start)
                    .padding(top = 6.dp, bottom = 6.dp, start = 10.dp, end = 10.dp),
                color = MaterialTheme.colorScheme.onPrimaryContainer)
            }

            wordsStatisticInDictionary.entries.forEach {set ->
                WordsStatistic(context, set.value.count, set.key)
            }

            var textFieldValue by rememberSaveable {
                mutableStateOf("")
            }

            var isErrorText by rememberSaveable {
                mutableStateOf(false)
            }

            var errorText by rememberSaveable {
                mutableStateOf(R.string.empty_string)
            }

            TextField(value = textFieldValue,
                onValueChange = {
                    textFieldValue = it
                    if (!inputTextValidation(it)) {
                        isErrorText = true
                        errorText =  R.string.supporting_error_text_search
                    } else {
                        isErrorText = false
                        errorText =  R.string.empty_string
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp),
                placeholder = { Text(text = stringResource(id = R.string.dictionary_text_field_search_placeholder))},
                leadingIcon = {Icon(imageVector = Icons.Rounded.Search, contentDescription = null)},
                singleLine = true,
                isError = isErrorText,
                supportingText = { Text(text = stringResource(id = errorText))},
                colors = CustomTextFieldColors())

//            LazyVerticalGrid(columns = GridCells.Adaptive(128.dp),
//                contentPadding = PaddingValues(top = 8.dp)) {
//
//            }
            InDictionaryWordItem()
        }
    }

    if (expanded) {
        CustomModalBottomSheet(dictionaryViewModel = dictionaryViewModel)
    }
    
}

@Composable
fun InDictionaryWordItem() {
    LazyHorizontalGrid(rows = GridCells.Adaptive(60.dp),
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .heightIn(max = 80.dp)
    ) {
        items(listOf(words[0].word, words[0].translate, words[0].status)) { item ->
            if (item is WordStatus) {
                Icon(painter = painterResource(id = when(item) {
                    WordStatus.New -> R.drawable.new_word_icon
                    WordStatus.InProgress -> R.drawable.in_progress_icon
                    WordStatus.Learned -> R.drawable.learned_icon }),
                    contentDescription = null,
                    Modifier.rotate(90f))
            } else {
                Text(text = item.toString(), textAlign = TextAlign.Center)
            }
        }
    }
}

@Composable
fun WordsStatistic(context: Context, count: Int, @StringRes stringRes: Int) {
    Row(horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth(0.45f)
            .padding(top = 10.dp)
    ) {
        val string = "$count  ${context.getString(stringRes)}"

        val decimalIndex = string.indexOf(char = string.find { it.isLetter() }!!)

        val annotatedString = AnnotatedString(text = string,
            spanStyles = listOf(
                AnnotatedString.Range(SpanStyle(fontWeight = FontWeight.Bold, fontSize = 18.sp), start = 0, end = decimalIndex)
            ))

        Text(text = annotatedString,
            textAlign = TextAlign.Center,
            fontSize = 18.sp)

        Icon(painterResource(id = when(stringRes) {
                                R.string.new_word -> R.drawable.new_word_icon
                                R.string.in_progress_word -> R.drawable.in_progress_icon
                                R.string.learned_word -> R.drawable.learned_icon
                                else -> R.drawable.new_word_icon
                            }),
            contentDescription = stringResource(id = stringRes),
            Modifier
                .rotate(90f),
            tint = when(stringRes) {
                R.string.new_word -> new_word_icon_color
                R.string.in_progress_word -> in_progress_icon_color
                R.string.learned_word -> learned_icon_color
                else -> MaterialTheme.colorScheme.onSecondaryContainer
            })
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CustomModalBottomSheet(dictionaryViewModel: DictionaryViewModel) {
    ModalBottomSheet(onDismissRequest = {dictionaryViewModel.isExpand(false)},
        sheetState = dictionaryViewModel.sheetState,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Box(modifier = Modifier
            .fillMaxSize(),
            contentAlignment = Alignment.TopCenter
        ) {
            Surface(shape = RoundedCornerShape(20.dp),
                modifier = Modifier
                    .padding(top = 40.dp, start = 50.dp, end = 50.dp)
                    .fillMaxSize(),
                shadowElevation = 10.dp
            ) {
                Column(modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 20.dp),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    sections.filter { it.sectionName != dictionaryViewModel.selectedItem.value.sectionName }
                        .forEach {

                            Row(modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 30.dp, end = 30.dp)
                            ) {
                                FilledTonalButton(onClick = {
                                    dictionaryViewModel.selectItem(it)
                                    dictionaryViewModel.isExpand(false) },
                                    shape = RoundedCornerShape(10.dp)
                                ) {
                                    Text(text = stringResource(id = it.sectionName),
                                        fontSize = 30.sp,
                                        modifier = Modifier.fillMaxWidth())
                                }
                            }
                        }
                }
            }
        }
    }
}

@Composable
private fun DictionaryWordsList() {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 30.dp)
    ) {
        items(wordsMap.keys.toList()) {

            FilledTonalButton(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(10.dp),
                elevation = ButtonDefaults.buttonElevation(4.dp)
            ) {
                Text(
                    text = it, textAlign = TextAlign.Start,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            wordsMap[it]?.forEachIndexed { index, word ->
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.padding(10.dp)
                ) {
                    Text(text = word)
                }
                if (index < (wordsMap[it]?.size?.minus(1) ?: 0)) {
                    Divider()
                }
            }
        }
    }
}

@Composable
fun ExpandedLanguageChooser(languageViewModel: LanguageViewModel) {
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

@Preview(showBackground = true, device = Devices.PHONE)
@Composable
fun DictionaryScreenPreviewPhone() {
    DictionaryScreen()
}

@Preview(showBackground = true, device = Devices.TABLET)
@Composable
fun DictionaryScreenPreviewTablet() {
    DictionaryScreen()
}
fun inputTextValidation(inputText: String): Boolean {
    return inputText.all { it.isLetter() }
}