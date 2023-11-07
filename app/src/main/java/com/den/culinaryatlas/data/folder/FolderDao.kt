package com.den.culinaryatlas.data.folder

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface FolderDao {

    @Upsert
    suspend fun upsertFolder(folder: Folder)

    @Delete
    suspend fun deleteFolder(folder: Folder)

    @Query("SELECT * FROM folder ORDER BY recipeQuantity DESC")
    fun getFoldersOrderedByLastTitle(): Flow<List<Folder>>
}