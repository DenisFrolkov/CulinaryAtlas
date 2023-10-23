package com.den.culinaryatlas.cookbooktabitem

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.den.culinaryatlas.R
import com.den.culinaryatlas.screens.CookbookScreen.MyRecipeScreen
import com.den.culinaryatlas.screens.CookbookScreen.SavedRecipeScreen
import com.den.culinaryatlas.ui.theme.Orange1

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CookbookTabRow() {
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
                    }
                },
            )
        }
    }
    HorizontalPager(
        state = pagerState,
        modifier = Modifier
            .fillMaxSize()
    ) {index -> 
        Box {
            when (index) {
                0 -> MyRecipeScreen()
                1 -> SavedRecipeScreen()
            }
        }
    }
}