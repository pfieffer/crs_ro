package com.example.crs_ro.ui.more

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.crs_ro.R
import kotlinx.android.synthetic.main.fragment_more.*

class MoreFragment: Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_more, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()

        btn_add_clothes.setOnClickListener {
            Toast.makeText(context, "Add cloth", Toast.LENGTH_SHORT).show()
        }

        btn_from_gallery.setOnClickListener {
            Toast.makeText(context, "gallery", Toast.LENGTH_SHORT).show()
        }

        btn_scan_color.setOnClickListener {
            Toast.makeText(context, "scan color", Toast.LENGTH_SHORT).show()
        }

        btn_generate_outfits.setOnClickListener {
            Toast.makeText(context, "generate outfit", Toast.LENGTH_SHORT).show()
        }

        btn_settings.setOnClickListener {
            Toast.makeText(context, "Settings", Toast.LENGTH_SHORT).show()
        }

        btn_help_and_feedback.setOnClickListener {
            Toast.makeText(context, "Help and Feedback", Toast.LENGTH_SHORT).show()
        }


    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onDetach() {
        super.onDetach()
    }
}