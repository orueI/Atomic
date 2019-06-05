package com.example.atomic.view.activity

import android.app.Fragment
import android.app.FragmentManager
import android.app.FragmentTransaction
import android.content.Intent
import android.os.Bundle
import com.example.atomic.R
import com.example.atomic.common.BaseActivity
import com.example.atomic.view.fragment.BeginningFragment
import com.example.atomic.view.fragment.SelectionLevelFragment


class CentralAtcivity : BaseActivity() {

    val beginningF = BeginningFragment()
    val selectionF = SelectionLevelFragment()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_central)

        val fTransaction = getFragmentManager().beginTransaction()
        fTransaction
            .add(R.id.container, beginningF)
            .add(R.id.container, selectionF)
            .show(beginningF)
            .commit()
    }

    fun changeFragment(fragment: Fragment) {
        val fTransaction = getFragmentManager().beginTransaction()
        fTransaction
            .replace(R.id.container, fragment)
            .commit()
    }

    fun changeActivity(int: Int){
//val intent = Intent()
        val intent = Intent(this@CentralAtcivity, MainActivity::class.java)
        intent.putExtra("level",int)
        startActivity(intent)
    }

}