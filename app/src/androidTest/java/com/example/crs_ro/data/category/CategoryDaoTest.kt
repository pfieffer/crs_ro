package com.example.crs_ro.data.category

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.crs_ro.data.AppDatabase
import com.example.crs_ro.data.waitForValue
import kotlinx.coroutines.runBlocking
import org.junit.*
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class CategoryDaoTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var categoryDao: CategoryDao
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
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetCategory() = runBlocking {
        //create a category in our table
        val category = Category(1, "Tops")
        categoryDao.insert(category)
        val allCategories = categoryDao.getAll().waitForValue()
        //check if category just inserted is equal retrieved from the table
        Assert.assertEquals(allCategories[0].name, category.name)
    }

    @Test
    @Throws(Exception::class)
    fun getAllCategories() = runBlocking {
        //insert two categories in table
        val category1 = Category(1, "Tops")
        categoryDao.insert(category1)
        val category2 = Category(2, "Bottom")
        categoryDao.insert(category2)
        //get all categories from the table
        val allCategories = categoryDao.getAll().waitForValue()
        //check if they are equal to above inserted values
        Assert.assertEquals(allCategories[0].name, category1.name)
        Assert.assertEquals(allCategories[1].name, category2.name)
    }

    //Test pre populating data
    @Test
    @Throws(Exception::class)
    fun prepopulateData() = runBlocking {
        //TODO Test functionality to populate database from SampleCategories, retrieve all categories and check if they match
        //insert from sample categories
        categoryDao.insertAll(SampleCategories.getSampleCategories())
        //get all categories from the table
        val allCategories = categoryDao.getAll().waitForValue()
        //check if they are equal
        Assert.assertEquals(allCategories, SampleCategories.getSampleCategories())
    }

}