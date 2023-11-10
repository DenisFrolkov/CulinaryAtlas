package com.den.culinaryatlas.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.den.culinaryatlas.R
import com.den.culinaryatlas.data.folder.FolderEvent
import com.den.culinaryatlas.data.folder.FolderState
import com.den.culinaryatlas.data.recipe.RecipeEvent
import com.den.culinaryatlas.data.recipe.RecipeState
import com.den.culinaryatlas.navigation.NavigationRoute
import com.den.culinaryatlas.tab_navigation.TabItem
import com.den.culinaryatlas.ui.theme.BasicOrange


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TabRowScreen(
    navController: NavController,
    stateRecipeState: RecipeState,
    onRecipeEvent: (RecipeEvent) -> Unit,
    stateFolderState: FolderState,
    onFolderEvent: (FolderEvent) -> Unit
) {
    val tabItems = listOf(
        TabItem.MyRecipeScreen,
        TabItem.FolderRecipeScreen
    )
    val pagerState = rememberPagerState { tabItems.size }
    TabRowContent(navController, tabItems, pagerState, stateRecipeState, onRecipeEvent, stateFolderState, onFolderEvent)
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TabRowContent(
    navController: NavController,
    tabItems: List<TabItem>,
    pagerState: PagerState,
    stateRecipeState: RecipeState,
    onRecipeEvent: (RecipeEvent) -> Unit,
    stateFolderState: FolderState,
    onFolderEvent: (FolderEvent) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TabRowContent(pagerState, tabItems)
        MyHorizontalPager(navController, pagerState, stateRecipeState, onRecipeEvent, stateFolderState, onFolderEvent)
    }
    FAB(navController)
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TabRowContent(
    pagerState: PagerState,
    tabItems: List<TabItem>) {
    var selectedTabIndex by remember { mutableStateOf(0) }
    LaunchedEffect(selectedTabIndex) { pagerState.animateScrollToPage(selectedTabIndex) }
    LaunchedEffect(pagerState.currentPage, pagerState.isScrollInProgress) {
        if (!pagerState.isScrollInProgress) selectedTabIndex = pagerState.currentPage
    }
    TabRow(
        modifier = Modifier
            .padding(start = 16.dp, top = 16.dp, end = 16.dp)
            .clip(shape = RoundedCornerShape(12.dp)),
        selectedTabIndex = selectedTabIndex
    ) {
        tabItems.forEachIndexed { index, tabItem ->
            Tab(
                selected = index == selectedTabIndex,
                onClick = { selectedTabIndex = index },
                modifier = Modifier.background(BasicOrange),
                icon = {
                    Icon(
                        modifier = Modifier.size(30.dp),
                        painter = painterResource(id = tabItem.icon),
                        contentDescription = tabItem.title,
                        tint = Color.Black
                    )
                }
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MyHorizontalPager(
    navController: NavController,
    pagerState: PagerState,
    stateRecipeState: RecipeState,
    onRecipeEvent: (RecipeEvent) -> Unit,
    stateFolderState: FolderState,
    onFolderEvent: (FolderEvent) -> Unit
) {
    HorizontalPager(
        state = pagerState,
        modifier = Modifier.fillMaxSize()
    ) { index ->
        Box {
            when (index) {
                0 -> MyRecipeScreen(navController, stateRecipeState, onRecipeEvent )
                1 -> FolderRecipeScreen(navController, stateFolderState)
            }
        }
    }
}

@Composable
fun FAB(navController: NavController) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = androidx.compose.ui.Alignment.BottomEnd
    ) {
        FloatingActionButton(
            onClick = { navController.navigate(NavigationRoute.CreatingRecipeScreen.route) },
            backgroundColor = BasicOrange,
            modifier = Modifier
                .padding(16.dp)
        ) {
            Icon(
                modifier = Modifier.size(30.dp),
                imageVector = Icons.Default.Add,
                contentDescription = "Создание рецепта"
            )
        }
    }
}