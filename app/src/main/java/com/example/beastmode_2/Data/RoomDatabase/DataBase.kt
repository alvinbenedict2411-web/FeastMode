package com.example.beastmode_2.Data.RoomDatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [AddRecipe::class], version = 1)
@TypeConverters(Converters::class)
abstract class DataBase : RoomDatabase() {
    //connect dao to database
    abstract fun recipeDao(): Dao
    companion object{
        private var INSTANCE: DataBase?=null
        fun getInstance(context: Context): DataBase{
            return INSTANCE?: synchronized(this){
                Room.databaseBuilder(
                    context.applicationContext,
                    DataBase::class.java,
                    "recipe_database"
                ).fallbackToDestructiveMigration().build().also {
                    INSTANCE=it
                }
            }
        }
    }
}