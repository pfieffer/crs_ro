package com.example.crs_ro.ui.clothes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.crs_ro.data.AppDatabase
import com.example.crs_ro.data.category.Category
import com.example.crs_ro.data.category.CategoryRepository
import com.example.crs_ro.data.cloth.ClothRepository
import com.example.crs_ro.data.subcategory.SubCategory

class ClothesViewModel(application: Application) : AndroidViewModel(application) {

    private val categoryRepository: CategoryRepository
//    private val categorySubCategoryRepository: CategorySubCategoryRepository
    private val clothRepository: ClothRepository

    val allCategories: LiveData<List<Category>>
    val hashMap: LiveData<HashMap<Category, List<SubCategory>>>

    init {
        val instance = AppDatabase.getDatabaseInstance(application)

        val categoryDao = instance.categoryDao()
        val subCategoryDao = instance.subcategoryDao()
        val clothDao = instance.clothDao()

        categoryRepository = CategoryRepository(categoryDao)
//        categorySubCategoryRepository = CategorySubCategoryRepository(categoryDao, subCategoryDao)
        clothRepository = ClothRepository(clothDao, categoryDao, subCategoryDao)

        allCategories = categoryRepository.allCategories
//        hashMap = categorySubCategoryRepository.getHashMapOfCategoriesAndSubCategories()
        hashMap = clothRepository.getHashMapOfCategoriesAndSubCategories()
        //TODO: Get the number of clothes in a category and pass it to the adapter,
        //TODO: Get the number of clothes in a subcategory and pass it to the adapter.
    }

}