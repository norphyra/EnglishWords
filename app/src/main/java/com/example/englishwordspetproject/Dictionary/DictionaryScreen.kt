package com.example.englishwordspetproject.Dictionary

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material.icons.rounded.DeleteOutline
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterEnd
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterStart
import androidx.compose.ui.Alignment.Companion.Start
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.englishwordspetproject.R
import com.example.englishwordspetproject.reusable_components.CardGrid
import com.example.englishwordspetproject.ui.theme.EnglishWordspetProjectTheme
import com.example.englishwordspetproject.ui.theme.in_progress_words_progress_color
import com.example.englishwordspetproject.ui.theme.learned_words_progress_color
import com.example.englishwordspetproject.ui.theme.new_words_progress_color
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DictionaryScreen(dictionaryViewModel: DictionaryViewModel = viewModel()) {

    val context = LocalContext.current

    val expanded by dictionaryViewModel.expanded.collectAsState()

    //val sheetState = rememberModalBottomSheetState()

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .wrapContentHeight()
            .verticalScroll(scrollState, enabled = true),
        horizontalAlignment = Start,
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        val pagerState = rememberPagerState()

        Carousel(dictionaryViewModel, pagerState)

        ScrollIndicator(pagerState = pagerState)

        Statistic()

        CardGrid(titles = cardGridTitles,
            modifier = Modifier.heightIn(min = 240.dp, max = 480.dp))

    }

    if (expanded) {
        CustomModalBottomSheet(dictionaryViewModel = dictionaryViewModel)
    }
    
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun DictionaryCard(page: Int, pagerState: PagerState, dictionaryViewModel: DictionaryViewModel) {

    OutlinedCard(shape = RoundedCornerShape(10.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background,
            contentColor = MaterialTheme.colorScheme.onBackground),
            modifier = Modifier
                .graphicsLayer {

                    val pageOffset = (
                            (pagerState.currentPage - page) + pagerState
                                .currentPageOffsetFraction
                            ).absoluteValue

                    lerp(
                        start = 0.65f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    ).also { scale ->
                        scaleX = scale
                        scaleY = scale
                    }

                    alpha = lerp(
                        start = 0.5f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    )

                }
                .height(200.dp),
            onClick = {dictionaryViewModel.isExpand(true)}
    ) {
        Column(horizontalAlignment = CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize(),
        ){
            Image(painter = painterResource(id = CardTitle.values()[page].icon),
                contentDescription = stringResource(id = CardTitle.values()[page].title),
                modifier = Modifier.size(100.dp))

            Text(text = stringResource(id = CardTitle.values()[page].title),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 15.dp),
                textAlign = TextAlign.Center,
                maxLines = 1)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Carousel(dictionaryViewModel: DictionaryViewModel, pagerState: PagerState) {

    HorizontalPager(pageCount = CardTitle.values().size,
        state = pagerState,
        contentPadding = PaddingValues(horizontal = 100.dp)
    ) { page ->
        DictionaryCard(page = page, pagerState = pagerState, dictionaryViewModel = dictionaryViewModel)
    }
}

@Composable
fun Statistic() {
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
                },
            modifier = Modifier
                .height(8.dp)
                .clip(RoundedCornerShape(4.dp))
                .fillMaxWidth(),
            trackColor = Color.LightGray.copy(0.2f))
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CustomModalBottomSheet(dictionaryViewModel: DictionaryViewModel) {

    var textFieldValue by rememberSaveable {
        mutableStateOf("")
    }

    var isError by rememberSaveable {
        mutableStateOf(false)
    }

    ModalBottomSheet(onDismissRequest = {dictionaryViewModel.isExpand(false)},
        sheetState = dictionaryViewModel.sheetState,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.5f),
        containerColor = MaterialTheme.colorScheme.background
    ) {
        TextField(value = textFieldValue,
            onValueChange = {
                textFieldValue = it
                isError = inputTextValidation(textFieldValue) },
            isError = isError,
            leadingIcon = { Icon(imageVector = Icons.Rounded.Search, contentDescription = "search")},
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 10.dp)
                .fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = TextFieldColors.focusedIndicatorColor(),
                unfocusedIndicatorColor = TextFieldColors.unfocusedIndicatorColor(),
                focusedLeadingIconColor = TextFieldColors.focusedLeadingIconColor(),
                unfocusedLeadingIconColor = TextFieldColors.unfocusedLeadingIconColor()
        ),
        singleLine = true,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search)
        )
        Column(
            horizontalAlignment = CenterHorizontally,
            modifier = Modifier.padding(horizontal = 30.dp)
        ) {
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
            
            Box(modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
            ) {
                Icon(imageVector = Icons.Filled.Verified,
                    contentDescription = null,
                    modifier = Modifier
                        .size(54.dp)
                        .align(CenterStart)
                        .padding(bottom = 20.dp),
                    tint = words[index].status.color)

                Column(verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .align(CenterStart)
                        .padding(start = 64.dp, bottom = 20.dp)
                ) {
                    Text(text = words[index].original,
                        fontStyle = FontStyle(R.font.noto_sans_cypro_minoan_regular),
                        fontSize = 24.sp)
                    Text(text = words[index].translate,
                        fontWeight = FontWeight.Light,
                        fontStyle = FontStyle(R.font.noto_sans_cypro_minoan_regular),
                        color = MaterialTheme.colorScheme.outline,
                        fontSize = 20.sp)
                }

                Icon(imageVector = Icons.Rounded.DeleteOutline,
                    contentDescription = "delete",
                    Modifier
                        .size(50.dp)
                        .align(CenterEnd)
                        .padding(bottom = 20.dp))
            }

        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ScrollIndicator(pagerState: PagerState) {

    val scope = rememberCoroutineScope()

    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 20.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(CardTitle.values().size) {
            val color = if (pagerState.currentPage == it) Color.DarkGray else Color.LightGray
            Box(modifier = Modifier
                .padding(2.dp)
                .clip(CircleShape)
                .size(10.dp)
                .background(color)
                .clickable {
                    scope.launch {
                        pagerState.animateScrollToPage(it)
                    }
                }
            ) {

            }
        }
    }
}

@Composable
fun SearchTextField(){

    var textFieldValue by rememberSaveable {
        mutableStateOf("")
    }

    var isError by rememberSaveable {
        mutableStateOf(false)
    }

    TextField(value = textFieldValue,
        onValueChange = {
            textFieldValue = it
            isError = inputTextValidation(textFieldValue) },
        isError = isError,
        leadingIcon = { Icon(imageVector = Icons.Rounded.Search, contentDescription = "search")},
        modifier = Modifier
            .padding(horizontal = 20.dp, vertical = 30.dp)
            .fillMaxWidth(),
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = TextFieldColors.focusedIndicatorColor(),
            unfocusedIndicatorColor = TextFieldColors.unfocusedIndicatorColor(),
            focusedLeadingIconColor = TextFieldColors.focusedLeadingIconColor(),
            unfocusedLeadingIconColor = TextFieldColors.unfocusedLeadingIconColor()
        ),
        singleLine = true,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search)
    )
}

fun inputTextValidation(inputText: String): Boolean {
    return !inputText.all { it.isLetter() }
}

object TextFieldColors {
    @Composable
    fun focusedIndicatorColor() = MaterialTheme.colorScheme.primary

    @Composable
    fun unfocusedIndicatorColor() = MaterialTheme.colorScheme.primaryContainer

    @Composable
    fun focusedLeadingIconColor() = MaterialTheme.colorScheme.primary

    @Composable
    fun unfocusedLeadingIconColor() = MaterialTheme.colorScheme.primaryContainer
}

@Preview(device = Devices.PHONE, showBackground = true, name = "Phone")
//@Preview(device = Devices.TABLET, showBackground = true, name = "TABLET")
//@Preview(device = Devices.DESKTOP, showBackground = true, name = "DESKTOP")
@Composable
fun DictionaryScreenPreview() {
    EnglishWordspetProjectTheme {
        BoxWithConstraints {
            DictionaryScreen()
        }
    }
}