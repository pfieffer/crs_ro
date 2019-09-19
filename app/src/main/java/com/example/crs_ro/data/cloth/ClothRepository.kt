package com.example.crs_ro.data.cloth

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.crs_ro.data.category.Category
import com.example.crs_ro.data.category.CategoryDao
import com.example.crs_ro.data.subcategory.SubCategory
import com.example.crs_ro.data.subcategory.SubCategoryDao
import java.util.concurrent.Executors

class ClothRepository(
    private val clothDao: ClothDao,
    private val categoryDao: CategoryDao,
    private val subCategoryDao: SubCategoryDao
) {
    val allClothes: LiveData<List<Cloth>> = clothDao.getAll()

    @WorkerThread
    fun insert(cloth: Cloth) {
        clothDao.insert(cloth)
    }

    @WorkerThread
    suspend fun update(cloth: Cloth) {
        clothDao.updateClothes(cloth)
    }

    @WorkerThread
    suspend fun delete(cloth: Cloth) {
        clothDao.delete(cloth)
    }

    @WorkerThread
    suspend fun deleteById(clothId: Int) {
        clothDao.deleteById(clothId)
    }

    fun getHashMapOfCategoriesAndSubCategories(): LiveData<HashMap<Category, List<SubCategory>>> {
        val data = MutableLiveData<HashMap<Category, List<SubCategory>>>()
        Executors.newSingleThreadExecutor().execute {
            val hashMap: HashMap<Category, List<SubCategory>> = hashMapOf()
            for (category in categoryDao.getList()) {
                // For every Category in our Database, set the clothesCount member variable
                category.clothesCount = clothDao.getNumberOfClothesInCategory(category.id)
                //new list of subcategories for every  Category in our list
                val lisOfSubCategories: ArrayList<SubCategory> = ArrayList()
                for (subCategory in subCategoryDao.getSubCategoriesListForCategory(category.id)){
                    // For every SubCategory in our Database, set the clothesCount member variable
                    subCategory.clothesCount = clothDao.getNumberOfClothesInSubCategory(subCategory.id)
                    //Add the list of SubCategories, with each SubCategory having the clothesCount variable set
                    lisOfSubCategories.add(subCategory)
                }
                hashMap[category] = lisOfSubCategories
            }
            data.postValue(hashMap)
        }
        return data
    }

}