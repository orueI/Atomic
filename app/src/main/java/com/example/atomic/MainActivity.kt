package com.example.atomic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.atomic.controler.Atom
import com.example.atomic.controler.Wall
import com.example.atomic.controler.XY
import com.example.atomic.data.CurrentMap
import com.example.atomic.view.MapView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }


}
