package com.den.culinaryatlas.data.folder

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface FolderDao {

    @Upsert
    suspend fun upsertFolder(folder: Folder)

    @Delete
    suspend fun deleteFolder(folder: Folder)

    @Update
    suspend fun updateFolder(folder: Folder)

    @Query("SELECT * FROM folder WHERE FolderId = :FolderId")
    fun getFolderById(FolderId: String): Folder

    @Query("SELECT * FROM folder ORDER BY recipeQuantity DESC")
    fun getFoldersOrderedByLastTitle(): Flow<List<Folder>>
}