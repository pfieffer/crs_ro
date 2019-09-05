package com.example.crs_ro.ui.clothes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.crs_ro.R
import kotlinx.android.synthetic.main.fragment_clothes.*

class ClothesFragment : Fragment() {

    private lateinit var clothesViewModel: ClothesViewModel
    private lateinit var adapter: CategoryListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        clothesViewModel = ViewModelProviders.of(this).get(ClothesViewModel::class.java)
        adapter = CategoryListAdapter(activity!!.applicationContext)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_clothes, container, false)

        clothesViewModel.allCategories.observe(this, Observer {
            adapter.setCategoryList(it)
            elv_categories.setAdapter(adapter)

            adapter.notifyDataSetChanged()
        })

        return rootView
    }
}