package com.den.culinaryatlas.data.recipe

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDao {
    @Upsert
    suspend fun upsertRecipe(recipe: Recipe)

    @Delete
    suspend fun deleteRecipe(recipe: Recipe)

    @Query("SELECT * FROM recipe WHERE RecipeId = :RecipeId")
    fun getRecipeById(RecipeId: String): Recipe

    @Query("SELECT * FROM recipe ORDER BY title DESC")
    fun getRecipesOrderedByLastTitle(): Flow<List<Recipe>>
}