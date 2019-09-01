package com.example.crs_ro.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.crs_ro.data.category.Category
import com.example.crs_ro.data.category.CategoryDao
import com.example.crs_ro.data.category.SampleCategories
import kotlinx.coroutines.CoroutineScope
import java.util.concurrent.Executors

@Database(entities = arrayOf(Category::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun categoryDao(): CategoryDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        private var DATABASE_NAME = "crs_ro_database"

        fun getDatabaseInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).build()
            }


        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java, DATABASE_NAME
        )
            .addCallback(object : Callback() {
                /**
                 * Override the onCreate method to populate the database.
                 * onCreate will be called when the database is created for the first time, after the
                 * tables have been created
                 */
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    //insert data on the IO thread
                    Executors.newSingleThreadExecutor().execute {
                        // Populate database
                        getDatabaseInstance(context).categoryDao()
                            .insertAll(SampleCategories.getSampleCategories())
                    }
                }

            })
    }
}