package com.example.crs_ro.data.cloth

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.crs_ro.data.AppDatabase
import com.example.crs_ro.data.category.SampleCategories
import com.example.crs_ro.data.subcategory.SampleSubCategories

import com.example.crs_ro.data.waitForValue
import kotlinx.coroutines.runBlocking
import org.junit.*

import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ClothRepositoryTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var clothRepository: ClothRepository
    private lateinit var clothDao: ClothDao
    private lateinit var db: AppDatabase

    @Before
    fun setUp() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        // Using an in-memory database because the information stored here disappears when the
        // process is killed.
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            // Allowing main thread queries, just for testing.
            .allowMainThreadQueries()
            .build()
        clothDao = db.clothDao()
        clothRepository = ClothRepository(clothDao)

        //For clothes repository to be tested, seed database with Sample Categories and Sub-Categories
        db.categoryDao().insertAll(SampleCategories.getSampleCategories())
        db.subcategoryDao().insertAll(SampleSubCategories.getSampleSubCategories())
    }

    @After
    fun tearDown() {
        db.close()
    }

    /*
    Test getting all clothes from the ClothRepository
     */
    @Test
    @Throws(Exception::class)
    fun getAllClothes() = runBlocking{
        val cloth1 = Cloth(1, "Shark T-Shirt", "test_image_url", 1, 5)
        clothRepository.insert(cloth1)
        val cloth2 = Cloth(2, "Chicago Bulls Cap", "test_image_url_3", 6, 26)
        clothRepository.insert(cloth2)

        Assert.assertEquals(listOf(cloth1, cloth2), clothRepository.allClothes.waitForValue())
    }

    /*
    Testing Insert functionality from ClothRepository
     */
    @Test
    @Throws(Exception::class)
    fun insert() = runBlocking{
        val cloth = Cloth(1, "Shark T-Shirt", "test_image_url", 1, 5)
        //insert
        clothRepository.insert(cloth)
        //check
        Assert.assertEquals(cloth, clothRepository.allClothes.waitForValue()[0])
    }

    /*
    Testing Update functionality from ClothRepository
     */
    @Test
    @Throws(Exception::class)
    fun update() = runBlocking{
        val cloth = Cloth(1, "Shark T-Shirt", "test_image_url", 1, 5)
        clothRepository.insert(cloth)
        Assert.assertEquals(cloth, clothRepository.allClothes.waitForValue()[0])

        //update cloth
        val updateCloth = Cloth(1, "Shart Tees","test_image_url_2", 1, 5)
        clothRepository.update(updateCloth)
        //check
        Assert.assertEquals(updateCloth, clothRepository.allClothes.waitForValue()[0])
    }

    /*
    Testing Delete functionality from ClothRepository
     */
    @Test
    @Throws(Exception::class)
    fun delete() = runBlocking{
        val cloth = Cloth(1, "Shark Tees","test_image_url_2", 1, 5)
        clothRepository.insert(cloth)
        Assert.assertEquals(1, clothRepository.allClothes.waitForValue().size)

        clothRepository.delete(cloth)
        Assert.assertEquals(0, clothRepository.allClothes.waitForValue().size )
    }

    /*
    Testing Delete by ID functionality from ClothRepository
     */
    @Test
    @Throws(Exception::class)
    fun deleteById() = runBlocking{
        val cloth = Cloth(1, "Shark Tees","test_image_url_2", 1, 5)
        clothRepository.insert(cloth)
        Assert.assertEquals(1, clothRepository.allClothes.waitForValue().size)

        clothRepository.deleteById(cloth.id)
        Assert.assertEquals(0, clothRepository.allClothes.waitForValue().size )
    }

    @Test
    fun getNumberOfClothesInCategory() = runBlocking{
        Assert.assertEquals(0, clothRepository.getNumberOfClothesInCategory(1).waitForValue())
        //insert three clothes
        val cloth1 = Cloth(1, "Shark Tees","test_image_url_1", 1, 5)
        val cloth2 = Cloth(2, "Brown SweatShirt","test_image_url_2", 1, 4)
        val cloth3 = Cloth(3, "Metallica Sleeveless","test_image_url_3", 1, 3)
        clothRepository.insert(cloth1)
        clothRepository.insert(cloth2)
        clothRepository.insert(cloth3)

        Assert.assertEquals(3, clothRepository.getNumberOfClothesInCategory(1).waitForValue())
    }

    @Test
    fun getNumberOfClothesInSubCategory() = runBlocking{
        Assert.assertEquals(0, clothRepository.getNumberOfClothesInSubCategory(5).waitForValue())
        //insert three clothes
        val cloth1 = Cloth(1, "Shark Tees","test_image_url_1", 1, 5)
        val cloth2 = Cloth(2, "Hollister Greeen T-Shirt","test_image_url_2", 1, 5)
        val cloth3 = Cloth(3, "Save Humanity T-Shirt","test_image_url_3", 1, 5)
        clothRepository.insert(cloth1)
        clothRepository.insert(cloth2)
        clothRepository.insert(cloth3)

        Assert.assertEquals(3, clothRepository.getNumberOfClothesInSubCategory(5).waitForValue())
    }

}