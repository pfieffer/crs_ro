package com.example.crs_ro.ui.clothes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import com.example.crs_ro.R
import com.example.crs_ro.data.category.Category
import kotlinx.android.synthetic.main.item_category.view.*


/*
For ExpandableListAdapter: the Category is Group, Sub Category is Child
 */
class CategoryListAdapter internal constructor(context: Context) : BaseExpandableListAdapter() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var categoryList: List<Category> = emptyList()
//                          subCategoryList: List<SubCategory>


    internal fun setCategoryList(categories: List<Category>) {
        this.categoryList = categories
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
        val headerTitle = getGroup(groupPosition).name

        return if (_convertView == null) {
            val convertView = inflater.inflate(R.layout.item_category, parent, false)

            convertView.tv_category_name.text = headerTitle
            convertView
        } else {
            _convertView.tv_category_name.text = headerTitle
            _convertView
        }

    }

    override fun getChildrenCount(groupPosition: Int): Int {
        //TODO: Return the actual number of children in the group corresponding to the group position
        return 1
    }

    override fun getChild(groupPosition: Int, childPosition: Int): Any {
        //TODO: Return a child (sub category) or a list of Children (Sub-Categories) at that groupPosition and/or childPosition
        return "Hello"
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


        val childText = getChild(groupPosition, childPosition) as String

        if (_convertView == null) {
            val convertView = LayoutInflater.from(parent?.context)
                .inflate(R.layout.item_category, parent, false)
            convertView.tv_category_name.text = childText
            return convertView

        } else {

            _convertView.tv_category_name.text = childText
            return _convertView
        }

    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong() //not sure if Long is going to work since Sub-Category will have an id field of Int
    }

    override fun getGroupCount(): Int {
        return this.categoryList.size
    }

}