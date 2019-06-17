package com.example.atomic.ui.fragment

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.atomic.R
import com.example.atomic.ui.activity.CentralAtcivity

class BeginningFragment: Fragment(),View.OnClickListener {



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_beginning, container, false)
        view.findViewById<Button>(R.id.map_selection).setOnClickListener(this)
        return view
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.map_selection ->{
                (activity as CentralAtcivity).changeFragment(SelectionLevelFragment())
            }
        }
    }
}