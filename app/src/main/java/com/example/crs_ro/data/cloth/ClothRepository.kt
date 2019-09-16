package com.example.crs_ro.data.cloth

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class ClothRepository(private val clothDao: ClothDao) {
    val allClothes: LiveData<List<Cloth>> = clothDao.getAll()

    @WorkerThread
    suspend fun insert(cloth: Cloth){
        clothDao.insert(cloth)
    }

    @WorkerThread
    suspend fun update(cloth: Cloth){
        clothDao.updateClothes(cloth)
    }

    @WorkerThread
    suspend fun delete(cloth: Cloth){
        clothDao.delete(cloth)
    }

    @WorkerThread
    suspend fun deleteById(clothId: Int){
        clothDao.deleteById(clothId)
    }

    fun getNumberOfClothesInCategory(categoryId: Int): LiveData<Int>{
       return clothDao.getNumberOfClothesInCategory(categoryId)
    }

    fun getNumberOfClothesInSubCategory(subCategoryId: Int): LiveData<Int>{
        return clothDao.getNumberOfClothesInSubCategory(subCategoryId)
    }

}