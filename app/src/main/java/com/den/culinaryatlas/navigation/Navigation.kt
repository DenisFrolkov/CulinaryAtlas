package com.den.culinaryatlas.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.den.culinaryatlas.data.folder.FolderEvent
import com.den.culinaryatlas.data.folder.FolderState
import com.den.culinaryatlas.data.folder.FolderViewModel
import com.den.culinaryatlas.data.recipe.RecipeEvent
import com.den.culinaryatlas.data.recipe.RecipeState
import com.den.culinaryatlas.data.recipe.RecipeViewModel
import com.den.culinaryatlas.data.recipe_in_folder.RecipeInFolderEvent
import com.den.culinaryatlas.data.recipe_in_folder.RecipeInFolderState
import com.den.culinaryatlas.data.recipe_in_folder.RecipeInFolderViewModel
import com.den.culinaryatlas.screens.AddRecipeInFolderScreen
import com.den.culinaryatlas.screens.CreatingFolderRecipeScreen
import com.den.culinaryatlas.screens.CreatingRecipeScreen
import com.den.culinaryatlas.screens.TabRowScreen
import com.den.culinaryatlas.screens.ViewFolderScreen
import com.den.culinaryatlas.screens.ViewRecipeScreen

@Composable
fun Navigation(
    stateRecipeState: RecipeState,
    onRecipeEvent: (RecipeEvent) -> Unit,
    stateFolderState: FolderState,
    onFolderEvent: (FolderEvent) -> Unit,
    stateRecipeInFolder: RecipeInFolderState,
    onRecipeInFolderEvent: (RecipeInFolderEvent) -> Unit,
    viewRecipeModel: RecipeViewModel,
    viewFolderModel: FolderViewModel,
    recipeInFolderViewModel: RecipeInFolderViewModel
) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = NavigationRoute.TabRowScreen.route) {
        composable(NavigationRoute.TabRowScreen.route) {
            TabRowScreen(navController,stateRecipeState, onRecipeEvent, stateFolderState, onFolderEvent)
        }
        composable(NavigationRoute.ViewRecipeScreen.route + "/{recipeId}") { backStackEntry ->
            val arguments = backStackEntry.arguments
            val recipeId = arguments?.getString("recipeId")
            recipeId?.let {
                ViewRecipeScreen(navController, viewRecipeModel, onRecipeEvent, it)
            }
        }
        composable(NavigationRoute.ViewFolderScreen.route + "/{folderId}") { backStackEntry ->
            val arguments = backStackEntry.arguments
            val folderId = arguments?.getString("folderId")
            folderId?.let {
                ViewFolderScreen(navController, viewFolderModel, it, stateRecipeInFolder, onFolderEvent, stateRecipeState)
            }
        }
        composable(NavigationRoute.CreatingRecipeScreen.route) {
            CreatingRecipeScreen(navController, stateRecipeState, onRecipeEvent)
        }
        composable(NavigationRoute.CreatingFolderRecipeScreen.route) {
            CreatingFolderRecipeScreen(navController, stateFolderState, onFolderEvent)
        }
        composable(NavigationRoute.AddRecipeInFolderScreen.route + "/{folderId}") { backStackEntry ->
            val arguments = backStackEntry.arguments
            val folderId = arguments?.getString("folderId")
            folderId?.let {
                AddRecipeInFolderScreen(navController, stateRecipeState,  onRecipeInFolderEvent, viewFolderModel, it)
            }
        }
    }
}
