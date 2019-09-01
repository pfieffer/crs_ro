package com.example.crs_ro.ui.clothes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.crs_ro.data.AppDatabase
import com.example.crs_ro.data.category.Category
import com.example.crs_ro.data.category.CategoryRepository
import kotlinx.coroutines.launch

class ClothesViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: CategoryRepository
    // Repository is completely separated from the UI through the ViewModel.
    val allCategories: LiveData<List<Category>>

    init {
        val categoryDao = AppDatabase.getDatabaseInstance(application).categoryDao()
        repository = CategoryRepository(categoryDao)
        allCategories = repository.allCategories
    }

}