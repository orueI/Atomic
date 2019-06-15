package com.example.atomic.view.fragment

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.atomic.R
import com.example.atomic.adapter.AdapterRecyclerviewMap

class SelectionLevelFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_level_selection, container, false)

        val adapter = AdapterRecyclerviewMap(activity)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        val staggeredGridLayoutManager = StaggeredGridLayoutManager(
            3,
            LinearLayoutManager.VERTICAL
        )
        recyclerView.layoutManager = staggeredGridLayoutManager
        recyclerView.adapter = adapter

        return view
    }

}