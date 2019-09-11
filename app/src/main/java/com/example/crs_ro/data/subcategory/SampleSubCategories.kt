package com.example.crs_ro.data.subcategory

class SampleSubCategories {
    companion object{
        fun getSampleSubCategories(): ArrayList<SubCategory>{
            val arrayList = ArrayList<SubCategory>()
            //Tops
            arrayList.add(SubCategory(1, "Blazers",1))
            arrayList.add(SubCategory(2, "Shirts",1))
            arrayList.add(SubCategory(3, "Sleeveless",1))
            arrayList.add(SubCategory(4, "Sweaters",1))
            arrayList.add(SubCategory(5, "T-Shirts",1))
            //Bottoms
            arrayList.add(SubCategory(6, "Shorts",2))
            arrayList.add(SubCategory(7, "Trousers",2))
            arrayList.add(SubCategory(8, "Skirts",2))
            arrayList.add(SubCategory(9, "Jeans",2))
            //Shoes
            arrayList.add(SubCategory(10, "Boots",3))
            arrayList.add(SubCategory(11, "Flats",3))
            arrayList.add(SubCategory(12, "Heels",3))
            arrayList.add(SubCategory(13, "Sandals",3))
            //Dresses
            arrayList.add(SubCategory(14, "Evening Gowns",4))
            arrayList.add(SubCategory(15, "Cocktail Dresses",4))
            arrayList.add(SubCategory(16, "Strapless Dresses",4))
            arrayList.add(SubCategory(17, "Sundresses",4))
            arrayList.add(SubCategory(18, "Shirt Dresses",4))
            //Bags
            arrayList.add(SubCategory(19, "Satchels",5))
            arrayList.add(SubCategory(20, "Totes",5))
            arrayList.add(SubCategory(21, "Briefcases",5))
            arrayList.add(SubCategory(22, "Clutches",5))
            //Accessories
            arrayList.add(SubCategory(23, "Watches",6))
            arrayList.add(SubCategory(24, "Sunglasses",6))
            arrayList.add(SubCategory(25, "Belts",6))
            arrayList.add(SubCategory(26, "Hats",6))
            arrayList.add(SubCategory(27, "Necklaces",6))
            arrayList.add(SubCategory(28, "Bracelets",6))
            arrayList.add(SubCategory(29, "Rings",6))
            arrayList.add(SubCategory(30, "Headbands",6))
            arrayList.add(SubCategory(31, "Earrings",6))
            arrayList.add(SubCategory(32, "Scarves",6))
            //Outerwear
            arrayList.add(SubCategory(33, "Jackets",7))
            arrayList.add(SubCategory(34, "Coats",7))


            return arrayList

        }
    }
}