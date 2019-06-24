package com.example.atomic.ui.activity

import android.app.Activity
import android.app.Fragment
import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import com.example.atomic.R
import com.example.atomic.ui.fragment.BeginningFragment
import com.example.atomic.ui.fragment.SelectionLevelFragment


class CentralAtcivity : Activity() {

    val beginningFragment = BeginningFragment()
    val selectionFragment = SelectionLevelFragment()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_central)

        val fTransaction = getFragmentManager().beginTransaction()
        fTransaction
            .add(R.id.container, beginningFragment)
            .add(R.id.container, selectionFragment)
            .show(beginningFragment)
            .commit()
    }

    fun changeFragment(fragment: Fragment) {
        val fTransaction = getFragmentManager().beginTransaction()
        fTransaction
            .replace(R.id.container, fragment)
            .commit()
    }

    fun changeActivity(int: Int){
        val pd = ProgressDialog(this)
        pd.setMessage("Loading of level");
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd.isIndeterminate = true;
        pd.show();
        val intent = Intent(this@CentralAtcivity, MainActivity::class.java)
        intent.putExtra("level",int)
        startActivity(intent)
    }

}