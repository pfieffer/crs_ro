package com.example.crs_ro.data.subcategory

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.crs_ro.data.AppDatabase
import com.example.crs_ro.data.category.SampleCategories
import com.example.crs_ro.data.waitForValue
import kotlinx.coroutines.runBlocking
import org.junit.*
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class SubCategoryDaoTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

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
        subCategoryDao = db.subcategoryDao()
        /*
        For sub categories to be tested, there needs to be Categories in the table, for Foreign Key
        constraints to pass.
         */
        db.categoryDao().insertAll(SampleCategories.getSampleCategories())
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    /*
    Test to insert one sub-category and retrieve the same
     */
    @Test
    @Throws(Exception::class)
    fun insertAndGetSubCategory() = runBlocking {
        //create a sub-category in our table
        val subCategory = SubCategory(1, "Blazers", 1)
        subCategoryDao.insert(subCategory)
        val allSubCategories = subCategoryDao.getAll().waitForValue()
        //check if category just inserted is equal retrieved from the table
        Assert.assertEquals(allSubCategories[0].name, subCategory.name)
    }

    /*
    Test to insert multiple sub-categories and retrieve them
     */
    @Test
    @Throws(Exception::class)
    fun getAllSubCategories() = runBlocking {
        //insert four sub-categories belonging to two categories in the table
        val subCategory1 = SubCategory(1, "Blazers", 1)
        val subCategory2 = SubCategory(2, "Shirts", 1)
        val subCategory3 = SubCategory(3, "Pants", 2)
        val subCategory4 = SubCategory(4, "Skirts", 2)
        subCategoryDao.insertAll(listOf(subCategory1, subCategory2, subCategory3, subCategory4))
        //get all sub-categories from the table
        val allSubCategories = subCategoryDao.getAll().waitForValue()
        //check if they are equal to above inserted values
        Assert.assertEquals(allSubCategories[0].name, subCategory1.name)
        Assert.assertEquals(allSubCategories[1].name, subCategory2.name)
        Assert.assertEquals(allSubCategories[2].name, subCategory3.name)
        Assert.assertEquals(allSubCategories[3].name, subCategory4.name)
    }

    //Test pre populating data
    @Test
    @Throws(Exception::class)
    fun prepopulateData() = runBlocking {
        //Test functionality to populate database from SampleSubCategories, retrieve all Sub-categories
        // and check if they match
        subCategoryDao.insertAll(SampleSubCategories.getSampleSubCategories())
        //get all categories from the table
        val allCategories = subCategoryDao.getAll().waitForValue()
        //check if they are equal
        Assert.assertEquals(allCategories, SampleSubCategories.getSampleSubCategories())
    }

    @Test
    fun getSubCategoriesForCategory() = runBlocking {
        //insert all sample sub-categories
        subCategoryDao.insertAll(SampleSubCategories.getSampleSubCategories())
        //get sub categories for 1st category
        val subCategoriesForFirstCategory = subCategoryDao.getSubCategoriesForCategory(1).waitForValue()

        //check if the size of subCategoriesForFirstCategory is actually 5 which is the number of sub categories
        //for category 1
        Assert.assertEquals(subCategoriesForFirstCategory.size, 5)
    }
}