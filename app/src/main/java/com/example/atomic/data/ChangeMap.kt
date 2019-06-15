package com.example.atomic.data

import com.example.atomic.*
import com.example.atomic.Map
import com.google.gson.GsonBuilder
import com.example.atomic.utils.l
import com.example.atomic.view.activity.MainActivity


object ChangeMap {
   private fun readJsonMap(levels: Levels): String = MainActivity.act.getString(
//       R.string.level_4
       levels.getInt()
   )

    fun changeLevel( levels: Levels){
        val jsonLevel = readJsonMap(levels)
        val builder = GsonBuilder()
        val gson = builder.create()


        val s = gson.fromJson(jsonLevel, Map::class.java)
        l(s.toString())
        val map: Map = s

        CurrentMap.getCurrentMap().changeMap(map,levels.numForLevel)
    }

}