package com.den.culinaryatlas.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.den.culinaryatlas.R
import com.den.culinaryatlas.cookbooktabitem.CookbookTabItem
import com.den.culinaryatlas.ui.theme.Orange1

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CookbookScreen() {
    val roundedFont = FontFamily(Font(R.font.brushscriptmtrusbyme_italic))
    val tabItems = listOf(
        CookbookTabItem(
            title = "Мои рецепты",
            item = R.drawable.apron_item,

        ),
        CookbookTabItem(
            title = "Сохранненые рецепты",
            item = R.drawable.cookbook_item
        )
    )
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    val pagerState = rememberPagerState { tabItems.size }
    LaunchedEffect(selectedTabIndex) { pagerState.animateScrollToPage(selectedTabIndex) }
    LaunchedEffect(pagerState.currentPage, pagerState.isScrollInProgress) {
        if(!pagerState.isScrollInProgress) selectedTabIndex = pagerState.currentPage
    }
    Column(modifier = Modifier
        .fillMaxSize()
    ) {
        TabRow(
            selectedTabIndex = selectedTabIndex,
            modifier = Modifier
                .clip(RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp)),
            backgroundColor = Orange1
        ) {
            tabItems.forEachIndexed { index, tabItem ->
                Tab(
                    selected = index == selectedTabIndex,
                    onClick = {
                        selectedTabIndex = index
                    },
                    content = {
                        Row(
                            modifier = Modifier
                                .height(66.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                painter = painterResource(id = tabItem.item),
                                contentDescription = tabItem.title
                            )
                            Spacer(
                                modifier = Modifier
                                    .width(10.dp)
                            )
                            Text(
                                text = tabItem.title,
                                fontFamily = roundedFont,
                                fontSize = 20.sp,
                                softWrap = true,
                                color = Color.Black
                            )
                        }
                    },
                )
            }
        }
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
        ) { index ->
            Box(
                modifier = Modifier
                .fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                Text(text = tabItems[index].title)
            }
        }
    }
}