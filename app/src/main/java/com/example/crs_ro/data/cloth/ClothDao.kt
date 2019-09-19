package com.example.crs_ro.data.cloth

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ClothDao {
    @Insert
    fun insert(cloth: Cloth)

    @Query("SELECT * FROM clothes")
    fun getAll(): LiveData<List<Cloth>>

    @Update
    fun updateClothes(vararg clothes: Cloth)

    @Delete
    fun delete(cloth: Cloth)

    @Query("DELETE FROM clothes WHERE id = :clothId")
    fun deleteById(clothId: Int)

    //get clothes belonging to a subcategory
    @Query("SELECT * FROM clothes WHERE sub_category_id= :subCategoryId")
    fun getClothesForSubCategory(subCategoryId: Int): LiveData<List<Cloth>>

    //get number of clothes belonging to a subcategory
    @Query("SELECT count(*) FROM clothes WHERE sub_category_id= :subCategoryId")
    fun getNumberOfClothesInSubCategory(subCategoryId: Int): Int

    //get number of clothes belonging to a Category
    @Query("SELECT count(*) FROM clothes WHERE category_id= :categoryId")
    fun getNumberOfClothesInCategory(categoryId: Int): Int

}