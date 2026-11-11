package com.example.beastmode_2.Data.RoomDatabase

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface Dao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipe(recipe: AddRecipe)

    @Update
    suspend fun updateRecipe(recipe: AddRecipe)

    @Query("SELECT * FROM recipes")
    suspend fun getAllRecipes(): List<AddRecipe>

    @Query("SELECT * FROM recipes WHERE id = :id")
    suspend fun getRecipeById(id: Int): AddRecipe

    @Delete
    suspend fun deleteRecipe(recipe: AddRecipe)
}