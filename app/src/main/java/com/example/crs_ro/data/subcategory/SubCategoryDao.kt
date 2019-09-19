package com.example.crs_ro.data.subcategory

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface SubCategoryDao {
    @Query("SELECT * FROM sub_categories ORDER BY id")
    fun getAll(): LiveData<List<SubCategory>>

    @Query("SELECT * FROM sub_categories WHERE category_id = :categoryId")
    fun getSubCategoriesForCategory(categoryId: Int): LiveData<List<SubCategory>>

    @Query("SELECT * FROM sub_categories WHERE category_id = :categoryId")
    fun getSubCategoriesListForCategory(categoryId: Int): List<SubCategory>

    @Insert
    suspend fun insert(subCategory: SubCategory)

    @Insert
    fun insertAll(subCategories: List<SubCategory>)

    @Delete
    fun delete(subCategory: SubCategory)
}