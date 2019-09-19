package com.example.crs_ro.data.cloth

import androidx.room.*
import com.example.crs_ro.data.category.Category
import com.example.crs_ro.data.subcategory.SubCategory


@Entity(
    tableName = "clothes",
    foreignKeys = arrayOf(
        ForeignKey(
            entity = Category::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("category_id")
        ),
        ForeignKey(
            entity = SubCategory::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("sub_category_id")
        )
    ),
    indices = arrayOf(
        Index(
            value = ["category_id"]
        ),
        Index(
            value = ["sub_category_id"]
        )
    )
)
data class Cloth(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "image")
    val image: String,
    @ColumnInfo(name = "category_id")
    val categoryId: Int,
    @ColumnInfo(name = "sub_category_id")
    val subCategoryId: Int
) {
}