package com.den.culinaryatlas.data.recipe

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDao {
    @Upsert
    suspend fun upsertRecipe(recipe: Recipe)

    @Delete
    suspend fun deleteRecipe(recipe: Recipe)

    @Update
    suspend fun updateRecipe(recipe: Recipe)

    @Query("SELECT * FROM recipe WHERE RecipeId = :recipeId")
    fun getRecipeById(recipeId: String): Recipe

    @Query("SELECT COUNT(*) FROM recipe INNER JOIN recipeInFolder ON recipe.recipeId = recipeInFolder.recipeId WHERE recipeInFolder.folderId = :folderId")
    fun getRecipeCountInFolder(folderId: Int): Flow<Int>

    @Query("SELECT recipe.* FROM recipe JOIN recipeinfolder ON recipe.RecipeId = recipeinfolder.recipeId WHERE recipeinfolder.folderId = :folderId")
    fun getRecipesInFolder(folderId: String): Flow<List<Recipe>>

    @Query("SELECT * FROM recipe ORDER BY title DESC")
    fun getRecipesOrderedByLastTitle(): Flow<List<Recipe>>
}