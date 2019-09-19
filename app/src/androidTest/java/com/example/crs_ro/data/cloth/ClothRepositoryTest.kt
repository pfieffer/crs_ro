package com.example.crs_ro.data.cloth

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.crs_ro.data.AppDatabase
import com.example.crs_ro.data.category.CategoryDao
import com.example.crs_ro.data.category.SampleCategories
import com.example.crs_ro.data.subcategory.SampleSubCategories
import com.example.crs_ro.data.subcategory.SubCategoryDao

import com.example.crs_ro.data.waitForValue
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.instanceOf
import org.junit.*

import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ClothRepositoryTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var clothRepository: ClothRepository
    private lateinit var clothDao: ClothDao
    private lateinit var categoryDao: CategoryDao
    private lateinit var subCategoryDao: SubCategoryDao
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
        categoryDao = db.categoryDao()
        subCategoryDao = db.subcategoryDao()

        clothRepository = ClothRepository(clothDao, categoryDao, subCategoryDao)

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

    /*
    Test if we get the HashMao using getHashMapOfCategoriesAndSubCategories
     */
    @Test
    fun test_if_returns_hashmap() {
        Assert.assertThat(
            clothRepository.getHashMapOfCategoriesAndSubCategories().waitForValue(),
            instanceOf(HashMap::class.java)
        )
    }


    @Test
    fun test_if_returned_hashMap_contains_expected_keys_and_values() {
        Assert.assertEquals(
            clothRepository.getHashMapOfCategoriesAndSubCategories().waitForValue().keys.toList().
                sortedBy { it.id },
            SampleCategories.getSampleCategories().toList()
        )
//        Assert.assertEquals(
//            clothRepository.getHashMapOfCategoriesAndSubCategories().waitForValue().values.toList(),
//            SampleSubCategories.getSampleSubCategories().toList()
//        )
    }

}