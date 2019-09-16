package com.example.crs_ro.ui.clothes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.crs_ro.data.AppDatabase
import com.example.crs_ro.data.CategorySubCategoryRepository
import com.example.crs_ro.data.category.Category
import com.example.crs_ro.data.category.CategoryRepository
import com.example.crs_ro.data.subcategory.SubCategory

class ClothesViewModel(application: Application) : AndroidViewModel(application) {

    private val categoryRepository: CategoryRepository
    private val categorySubCategoryRepository: CategorySubCategoryRepository

    val allCategories: LiveData<List<Category>>
    val hashMap: LiveData<HashMap<Category, List<SubCategory>>>

    init {
        val instance = AppDatabase.getDatabaseInstance(application)

        val categoryDao = instance.categoryDao()
        val subCategoryDao = instance.subcategoryDao()

        categoryRepository = CategoryRepository(categoryDao)
        categorySubCategoryRepository = CategorySubCategoryRepository(categoryDao, subCategoryDao)

        allCategories = categoryRepository.allCategories
        hashMap = categorySubCategoryRepository.getHashMap()
    }

}