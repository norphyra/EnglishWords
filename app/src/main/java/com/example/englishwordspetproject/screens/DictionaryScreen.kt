package com.example.englishwordspetproject.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DeleteOutline
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material.icons.rounded.ExpandMore
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowHeightSizeClass.Companion.Compact
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.Start
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontStyle
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
import com.example.englishwordspetproject.data.viewModels.WordsWithTranslate
import com.example.englishwordspetproject.data.viewModels.cardTitles
import com.example.englishwordspetproject.data.viewModels.words
import com.example.englishwordspetproject.data.viewModels.wordsStatisticInDictionary
import com.example.englishwordspetproject.ui.theme.in_progress_words_progress_color
import com.example.englishwordspetproject.ui.theme.learned_words_progress_color
import com.example.englishwordspetproject.ui.theme.new_words_progress_color
import com.example.englishwordspetproject.utils.WindowSize


@Composable
fun DictionaryScreen(dictionaryViewModel: DictionaryViewModel = viewModel()) {

    val context = LocalContext.current

    val expanded by dictionaryViewModel.expanded.collectAsState()

    //val sheetState = rememberModalBottomSheetState()

    val scrollState = rememberScrollState()

    val screenHeight = context.resources.displayMetrics.heightPixels
    val screenWidth = context.resources.displayMetrics.widthPixels

    val mainColumnVerticalPaddingValues = when(WindowSize.windowSizeClass?.heightSizeClass) {
        Compact -> 10.dp
        else -> 30.dp
    }

    val mainColumnHorizontalPaddingValues = when(WindowSize.windowSizeClass?.widthSizeClass) {
        WindowWidthSizeClass.Compact -> 20.dp
        WindowWidthSizeClass.Medium -> 100.dp
        WindowWidthSizeClass.Expanded -> 400.dp
        else -> 300.dp
    }
    
    Box(modifier = Modifier
        .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .padding(
                    horizontal = mainColumnHorizontalPaddingValues,
                    vertical = mainColumnVerticalPaddingValues
                )
                .fillMaxSize(),
            horizontalAlignment = Start
        ) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 30.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = stringResource(id = R.string.my_dictionary),
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp)
                
                Button(onClick = { /*TODO*/ },
                       shape = RoundedCornerShape(20.dp)
                ) {
                    Icon(imageVector = Icons.Rounded.Edit, contentDescription = "")
                    Text(text = "Редактировать",
                        modifier = Modifier.padding(10.dp))
                }
            }

            Column(modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState, enabled = true)
                .padding(bottom = 50.dp),
                    horizontalAlignment = CenterHorizontally
            ) {

                cardTitles.forEach {title ->
                    DictionaryCard(title = stringResource(id = title),
                        dictionaryViewModel = dictionaryViewModel)
                }
            }

//            if (WindowSize.windowSizeClass?.widthSizeClass == WindowWidthSizeClass.Expanded) {
//                Row(modifier = Modifier.fillMaxWidth(),
//                horizontalArrangement = Arrangement.SpaceBetween) {
//                    DictionaryCard("Все слова")
//                    DictionaryCard("Все слова")
//                }
//            } else {
//                DictionaryCard("Все слова")
//            }

//            Surface(shape = RoundedCornerShape(20.dp),
//                color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f),
//                modifier = Modifier.padding(top = 40.dp, bottom = 5.dp)
//            ) {
//                Text(text = stringResource(id = R.string.dictionary_statistics),
//                modifier = Modifier
//                    .align(Start)
//                    .padding(top = 6.dp, bottom = 6.dp, start = 10.dp, end = 10.dp),
//                color = MaterialTheme.colorScheme.onPrimaryContainer)
//            }
//
//            wordsStatisticInDictionary.entries.forEach {set ->
//                WordsStatistic(context, set.value.count, set.key)
//            }
//
//            var textFieldValue by rememberSaveable {
//                mutableStateOf("")
//            }
//
//            var isErrorText by rememberSaveable {
//                mutableStateOf(false)
//            }
//
//            var errorText by rememberSaveable {
//                mutableStateOf(R.string.empty_string)
//            }
//
//            TextField(value = textFieldValue,
//                onValueChange = {
//                    textFieldValue = it
//                    if (!inputTextValidation(it)) {
//                        isErrorText = true
//                        errorText =  R.string.supporting_error_text_search
//                    } else {
//                        isErrorText = false
//                        errorText =  R.string.empty_string
//                    }
//                },
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(top = 30.dp),
//                placeholder = { Text(text = stringResource(id = R.string.dictionary_text_field_search_placeholder))},
//                leadingIcon = {Icon(imageVector = Icons.Rounded.Search, contentDescription = null)},
//                singleLine = true,
//                isError = isErrorText,
//                supportingText = { Text(text = stringResource(id = errorText))},
//                colors = CustomTextFieldColors())

        }
    }

    if (expanded) {
        CustomModalBottomSheet(dictionaryViewModel = dictionaryViewModel)
    }
    
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DictionaryCard(title: String, dictionaryViewModel: DictionaryViewModel) {

    Card(shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(10.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background,
                                         contentColor = MaterialTheme.colorScheme.onBackground),
        modifier = Modifier.padding(bottom = 30.dp),
        onClick = {
            dictionaryViewModel.isExpand(true)
            dictionaryViewModel.selectedTitle(title)
        }
    ) {
        Column(modifier = Modifier
            .padding(20.dp)
            .width(350.dp)
        ){
            Text(text = title, fontWeight = FontWeight.Bold, fontSize = 20.sp)

            wordsStatisticInDictionary.entries.forEach { entry ->
                val string = "${entry.value} ${stringResource(id = entry.key)}"
                val decimalIndex = string.indexOf(char = string.find { it.isLetter() }!!)

                val text = AnnotatedString(text = string,
                    spanStyles = listOf(
                        AnnotatedString.Range(SpanStyle(fontWeight = FontWeight.Bold, fontSize = 18.sp), start = 0, end = decimalIndex)
                    )
                )

                if (entry.key != R.string.all_words) {

                    Text(text = text, modifier = Modifier.padding(top = 20.dp, bottom = 5.dp))
                    LinearProgressIndicator(progress = entry.value.toFloat() / wordsStatisticInDictionary[R.string.all_words]!! ,
                        color = when(entry.key) {
                            R.string.new_words -> new_words_progress_color
                            R.string.in_progress_word -> in_progress_words_progress_color
                            R.string.learned_word -> learned_words_progress_color
                            else -> MaterialTheme.colorScheme.onSurfaceVariant
                        })
                }

            }
        }
    }
}

@Composable
fun InDictionaryWordItem() {
    LazyHorizontalGrid(rows = GridCells.Adaptive(60.dp),
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .heightIn(max = 80.dp)
    ) {
        items(listOf(words[0].original, words[0].translate, words[0].status)) { item ->
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

//@Composable
//fun WordsStatistic(context: Context, count: Int, @StringRes stringRes: Int) {
//    Row(horizontalArrangement = Arrangement.SpaceBetween,
//        verticalAlignment = Alignment.CenterVertically,
//        modifier = Modifier
//            .fillMaxWidth(0.45f)
//            .padding(top = 10.dp)
//    ) {
//        val string = "$count  ${context.getString(stringRes)}"
//
//        val decimalIndex = string.indexOf(char = string.find { it.isLetter() }!!)
//
//        val annotatedString = AnnotatedString(text = string,
//            spanStyles = listOf(
//                AnnotatedString.Range(SpanStyle(fontWeight = FontWeight.Bold, fontSize = 18.sp), start = 0, end = decimalIndex)
//            ))
//
//        Text(text = annotatedString,
//            textAlign = TextAlign.Center,
//            fontSize = 18.sp)
//
//        Icon(painterResource(id = when(stringRes) {
//                                R.string.new_word -> R.drawable.new_word_icon
//                                R.string.in_progress_word -> R.drawable.in_progress_icon
//                                R.string.learned_word -> R.drawable.learned_icon
//                                else -> R.drawable.new_word_icon
//                            }),
//            contentDescription = stringResource(id = stringRes),
//            Modifier
//                .rotate(90f),
//            tint = when(stringRes) {
//                R.string.new_word -> new_word_icon_color
//                R.string.in_progress_word -> in_progress_icon_color
//                R.string.learned_word -> learned_icon_color
//                else -> MaterialTheme.colorScheme.onSecondaryContainer
//            })
//    }
//}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CustomModalBottomSheet(dictionaryViewModel: DictionaryViewModel) {

    val selectedTitle by dictionaryViewModel.selectedTitle.collectAsState()

    ModalBottomSheet(onDismissRequest = {dictionaryViewModel.isExpand(false)},
        sheetState = dictionaryViewModel.sheetState,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.5f),
        containerColor = MaterialTheme.colorScheme.background,
        contentColor = MaterialTheme.colorScheme.onBackground
    ) {
        Column(
            horizontalAlignment = CenterHorizontally,
            modifier = Modifier.padding(horizontal = 40.dp)
        ) {
            Text(text = selectedTitle,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                fontStyle = FontStyle(R.font.noto_sans_cypro_minoan_regular))
            Divider()
            DictionaryWordsList(words)
        }
    }
}

@Composable
private fun DictionaryWordsList(words: List<WordsWithTranslate>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 30.dp)
    ) {
        items(words.size) {index ->
            Row(verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp)
            ) {
                Column(verticalArrangement = Arrangement.Center,
                    modifier = Modifier.width(200.dp)
                ) {
                    Text(text = words[index].original,
                        fontStyle = FontStyle(R.font.noto_sans_cypro_minoan_regular),
                        fontSize = 20.sp)
                    Text(text = words[index].translate,
                        fontWeight = FontWeight.Light,
                        fontStyle = FontStyle(R.font.noto_sans_cypro_minoan_regular),
                        color = MaterialTheme.colorScheme.outline,
                        fontSize = 16.sp)
                }
                Text(text = stringResource(id = words[index].status.stringRes),
                    color = words[index].status.color,
                    fontStyle = FontStyle(R.font.noto_sans_cypro_minoan_regular),
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center)

                Icon(imageVector = Icons.Rounded.DeleteOutline,
                    contentDescription = "delete",
                    Modifier.size(24.dp))
            }
        }
    }
}

@Composable
fun ExpandedLanguageChooser(languageViewModel: LanguageViewModel) {
    val context = LocalContext.current

    val languages = mapOf("English" to R.drawable.usa_flag_icon, "Russian" to R.drawable.russian_flag_icon)

    var expanded by rememberSaveable { mutableStateOf(false) }
    val selectedLanguage by rememberSaveable { mutableStateOf("English") }

    Column(horizontalAlignment = CenterHorizontally,
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

@Preview(name = "Phone", showBackground = true, device = Devices.PIXEL_4_XL)
@Composable
fun DictionaryScreenPreviewPhone() {
    DictionaryScreen()
}

@Preview(name = "Tablet", showBackground = true, device = Devices.NEXUS_10)
@Composable
fun DictionaryScreenPreviewTablet() {
    DictionaryScreen()
}

//@Preview(showBackground = true, device = Devices.TV_1080p)
//@Composable
//fun DictionaryScreenPreviewTablet() {
//    DictionaryScreen()
//}

fun inputTextValidation(inputText: String): Boolean {
    return inputText.all { it.isLetter() }
}