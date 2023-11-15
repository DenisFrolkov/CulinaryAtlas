package com.den.culinaryatlas.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.den.culinaryatlas.data.folder.Folder
import com.den.culinaryatlas.data.folder.FolderDao
import com.den.culinaryatlas.data.recipe.Recipe
import com.den.culinaryatlas.data.recipe.RecipeDao
import com.den.culinaryatlas.data.recipe_in_folder.RecipeInFolder
import com.den.culinaryatlas.data.recipe_in_folder.RecipeInFolderDao

val MIGRATION_1_8: Migration = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        // Выполните SQL-запрос для добавления нового столбца
        database.execSQL("ALTER TABLE recipe ADD COLUMN description TEXT")
    }
}
@Database(
    entities = [Recipe::class, Folder::class, RecipeInFolder::class],
    version = 13,
    exportSchema = false
)
abstract class MainDatabase : RoomDatabase() {
    abstract val recipeDao: RecipeDao
    abstract val folderDao: FolderDao
    abstract val recipeInFolderDao: RecipeInFolderDao

    companion object {
        @Volatile
        private var INSTANCE: MainDatabase? = null

        fun getInstance(context: Context): MainDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MainDatabase::class.java,
                    "recipe_database"
                )
                    .addMigrations(MIGRATION_1_8)
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}