package com.example.crs_ro.data.subcategory

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData

class SubCategoryRepository(private val subCategoryDao: SubCategoryDao) {

    val allSubCategories: LiveData<List<SubCategory>> = subCategoryDao.getAll()
    /*
    Called on the DAO
     */
    @WorkerThread
    suspend fun insert(category: SubCategory) {
        subCategoryDao.insert(category)
    }
}