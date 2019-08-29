package com.example.crs_ro.ui.clothes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.crs_ro.R

class ClothesFragment : Fragment() {

    private lateinit var clothesViewModel: ClothesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        clothesViewModel =
            ViewModelProviders.of(this).get(ClothesViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_clothes, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        clothesViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}