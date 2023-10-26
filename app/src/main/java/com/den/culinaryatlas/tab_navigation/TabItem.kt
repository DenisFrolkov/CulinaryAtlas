package com.den.culinaryatlas.tab_navigation

import com.den.culinaryatlas.R

sealed class TabItem(val title: String, val icon: Int){
    object MyRecipeScreen: TabItem("my_recipe_screen", R.drawable.my_recipe_icon, )
    object FolderRecipeScreen: TabItem("folder_recipe_screen", R.drawable.folder_recipe_icon)
}
