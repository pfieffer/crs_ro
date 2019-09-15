package com.example.crs_ro.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.crs_ro.data.category.Category
import com.example.crs_ro.data.category.CategoryDao
import com.example.crs_ro.data.subcategory.SubCategory
import com.example.crs_ro.data.subcategory.SubCategoryDao
import java.util.concurrent.Executors

class CategorySubCategoryRepository(
    private val categoryDao: CategoryDao,
    private val subCategoryDao: SubCategoryDao
) {
    fun getHashMap(): LiveData<HashMap<Category, List<SubCategory>>> {
        val data = MutableLiveData<HashMap<Category, List<SubCategory>>>()
        Executors.newSingleThreadExecutor().execute {
            val hashMap: HashMap<Category, List<SubCategory>> = hashMapOf()
            for (category in categoryDao.getList()) {
                hashMap[category] = subCategoryDao.getSubCategoriesListForCategory(category.id)
            }
            data.postValue(hashMap)
        }
        return data
    }

}