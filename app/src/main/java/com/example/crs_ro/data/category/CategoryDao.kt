package com.example.crs_ro.data.category

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CategoryDao {
    @Query("SELECT * FROM categories ORDER BY id")
    fun getAll(): LiveData<List<Category>>

    @Query("SELECT * FROM categories ORDER BY id")
    fun getList(): List<Category>

    @Insert
    suspend fun insert(category: Category)

    @Insert
    fun insertAll(categories: List<Category>)

    @Delete
    fun delete(category: Category)
}