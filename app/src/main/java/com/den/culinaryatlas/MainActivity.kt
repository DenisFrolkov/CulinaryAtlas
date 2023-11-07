package com.den.culinaryatlas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.den.culinaryatlas.data.MainDatabase
import com.den.culinaryatlas.data.folder.FolderViewModel
import com.den.culinaryatlas.data.recipe.RecipeViewModel
import com.den.culinaryatlas.navigation.Navigation

class MainActivity : ComponentActivity() {
    private val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            MainDatabase::class.java,
            "recipes.db"
        ).build()
    }
    private val viewRecipeModel by viewModels<RecipeViewModel>(
        factoryProducer = {
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return RecipeViewModel(db.recipeDao) as T
                }
            }
        }
    )

    private val viewFolderModel by viewModels<FolderViewModel>(
        factoryProducer = {
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return FolderViewModel(db.folderDao) as T
                }
            }
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val stateRecipe by viewRecipeModel.state.collectAsState()
            val stateFolder by viewFolderModel.state.collectAsState()
            Navigation(stateRecipe, viewRecipeModel::onRecipeEvent, stateFolder, viewFolderModel::onFolderEvent, viewRecipeModel)
        }
    }
}