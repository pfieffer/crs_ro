package com.example.crs_ro.data.category

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData

class CategoryRepository(private val categoryDao: CategoryDao) {
//    companion object {
//
//        private var categoryRepository: CategoryRepository? = null
//
//        val instance: CategoryRepository
//            get() {
//                if (categoryRepository == null) {
//                    categoryRepository = CategoryRepository(categoryDao = cate)
//                }
//                return categoryRepository as CategoryRepository
//            }
//    }

    val allCategories: LiveData<List<Category>> = categoryDao.getAll()


    /*
    Called on the DAO
     */
    @WorkerThread
    suspend fun insert(category: Category) {
        categoryDao.insert(category)
    }
}