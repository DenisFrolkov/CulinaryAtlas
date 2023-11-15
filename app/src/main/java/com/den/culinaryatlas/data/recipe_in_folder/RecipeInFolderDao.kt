package com.den.culinaryatlas.data.recipe_in_folder

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import com.den.culinaryatlas.data.recipe.Recipe
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeInFolderDao {
    @Insert()
    suspend fun upsert(recipeInFolder: RecipeInFolder)
    @Delete
    suspend fun delete(recipeInFolder: RecipeInFolder)


    @Query("SELECT * FROM RecipeInFolder ORDER BY recipeId DESC")
    fun getRecipeInFolderByLastFolderId(): Flow<List<RecipeInFolder>>
}