package com.example.crs_ro.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.crs_ro.data.category.CategoryDao
import com.example.crs_ro.data.category.SampleCategories
import com.example.crs_ro.data.subcategory.SampleSubCategories
import com.example.crs_ro.data.subcategory.SubCategoryDao
import org.hamcrest.CoreMatchers.instanceOf
import org.junit.*
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class CategorySubCategoryRepositoryTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var categorySubCategoryRepo: CategorySubCategoryRepository
    private lateinit var categoryDao: CategoryDao
    private lateinit var subCategoryDao: SubCategoryDao
    private lateinit var db: AppDatabase

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        // Using an in-memory database because the information stored here disappears when the
        // process is killed.
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            // Allowing main thread queries, just for testing.
            .allowMainThreadQueries()
            .build()
        categoryDao = db.categoryDao()
        subCategoryDao = db.subcategoryDao()
        /*
        For this repository to be tested, there needs to be Categories and SubCategories in the table.
         */
        db.categoryDao().insertAll(SampleCategories.getSampleCategories())
        db.subcategoryDao().insertAll(SampleSubCategories.getSampleSubCategories())
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    fun test_if_returns_hashmap() {
        categorySubCategoryRepo = CategorySubCategoryRepository(categoryDao, subCategoryDao)
        Assert.assertThat(
            categorySubCategoryRepo.getHashMap().waitForValue(),
            instanceOf(HashMap::class.java)
        )
    }

    fun test_if_returned_hashMap_contains_expected_keys_and_values() {
        categorySubCategoryRepo = CategorySubCategoryRepository(categoryDao, subCategoryDao)
        Assert.assertEquals(
            categorySubCategoryRepo.getHashMap().waitForValue().keys.toList(),
            SampleCategories.getSampleCategories().toList()
        )
        Assert.assertEquals(
            categorySubCategoryRepo.getHashMap().waitForValue().values.toList(),
            SampleSubCategories.getSampleSubCategories().toList()
        )
    }
}