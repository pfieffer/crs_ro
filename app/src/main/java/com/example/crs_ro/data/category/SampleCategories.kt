package com.example.crs_ro.data.category

class SampleCategories {
    companion object{
        fun getSampleCategories(): ArrayList<Category>{
            val arrayList = ArrayList<Category>()
            arrayList.add(Category(1, "Tops"))
            arrayList.add(Category(2, "Bottoms"))
            arrayList.add(Category(3, "Shoes"))
            arrayList.add(Category(4, "Dresses"))
            arrayList.add(Category(5, "Bags"))
            arrayList.add(Category(6, "Accessories"))
            arrayList.add(Category(7, "Wishlist"))
            return arrayList
        }
    }
}