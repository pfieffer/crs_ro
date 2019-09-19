package com.example.crs_ro.ui.clothes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import com.example.crs_ro.R
import com.example.crs_ro.data.category.Category
import com.example.crs_ro.data.subcategory.SubCategory
import kotlinx.android.synthetic.main.item_category.view.*
import kotlinx.android.synthetic.main.item_sub_cateogry.view.*


/*
For ExpandableListAdapter: the Category is Group, Sub Category is Child.
 */
class CategoryListAdapter internal constructor(private val context: Context) :
    BaseExpandableListAdapter() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var categoryList: List<Category> = emptyList()
    private var hashMap: Map<Category, List<SubCategory>> = emptyMap()


    internal fun setCategoryList(categories: List<Category>) {
        this.categoryList = categories
    }

    internal fun setCategorySubCategoriesMap(categorySubCategoriesMap: Map<Category, List<SubCategory>>) {
        this.hashMap = categorySubCategoriesMap
    }

    override fun getGroup(groupPosition: Int): Category {
        return this.categoryList[groupPosition]
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return true
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun getGroupView(
        groupPosition: Int,
        isExpanded: Boolean,
        _convertView: View?,
        parent: ViewGroup?
    ): View {
        val category = getGroup(groupPosition)

        val categoryName = category.name
        val numberOfClothesInCategory = category.clothesCount
        val numberOfClothesInCategoryString = context.resources.getQuantityString(
            R.plurals.numberOfClothes,
            numberOfClothesInCategory,
            numberOfClothesInCategory
        )

        return if (_convertView == null) {
            val convertView = inflater.inflate(R.layout.item_category, parent, false)

            convertView.tv_category_name.text = categoryName
            convertView.tv_category_clothes_count.text = numberOfClothesInCategoryString
            convertView
        } else {
            _convertView.tv_category_name.text = categoryName
            _convertView.tv_category_clothes_count.text = numberOfClothesInCategoryString

            _convertView
        }

    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return if (hashMap[categoryList[groupPosition]] != null) {
            //Using !! because it cannot be null
            hashMap.getValue(categoryList[groupPosition]).size
        } else {
            0
        }

    }

    override fun getChild(groupPosition: Int, childPosition: Int): SubCategory {
        return hashMap.getValue(categoryList[groupPosition])[childPosition]
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getChildView(
        groupPosition: Int,
        childPosition: Int,
        isLastChild: Boolean,
        _convertView: View?,
        parent: ViewGroup?
    ): View {
        val subCategory = getChild(groupPosition, childPosition)

        val subCategoryName = subCategory.name
        val numberOfClothesInSubCategory = subCategory.clothesCount
        val numberOfClothesInSubCategoryString = context.resources.getQuantityString(
            R.plurals.numberOfClothes,
            numberOfClothesInSubCategory,
            numberOfClothesInSubCategory
        )

        if (_convertView == null) {
            val convertView = LayoutInflater.from(parent?.context)
                .inflate(R.layout.item_sub_cateogry, parent, false)
            convertView.tv_sub_category_name.text = subCategoryName
            convertView.tv_sub_category_clothes_count.text = numberOfClothesInSubCategoryString
            return convertView

        } else {

            _convertView.tv_sub_category_name.text = subCategoryName
            _convertView.tv_sub_category_clothes_count.text = numberOfClothesInSubCategoryString
            return _convertView
        }

    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong() //not sure if Long is going to work since Sub-Category will have an id field of Int
    }

    override fun getGroupCount(): Int {
        return categoryList.size
    }

}