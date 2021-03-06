package com.example.crs_ro.data.subcategory

import androidx.room.*
import com.example.crs_ro.data.category.Category

/**
 * One to many Relationship from Category to SubCategory
 */
@Entity(
    tableName = "sub_categories",
    foreignKeys = arrayOf(
        ForeignKey(
            entity = Category::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("category_id")
        )
    ), indices = arrayOf(
        Index(
            value = ["category_id"]
        )
    )
)
data class SubCategory(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "category_id")
    val categoryId: Int
) {
    @Ignore
    var clothesCount: Int = 0
}