package com.example.atomic.view.activity

import android.app.Activity
import android.os.Bundle
import android.view.View
import com.example.atomic.R
import com.example.atomic.common.BaseActivity
import kotlinx.android.synthetic.main.fragment_beginning.*

class CentralAtcivity: BaseActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_beginning)
        map_selection.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.map_selection ->{

            }
        }
    }
}