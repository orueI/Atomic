package com.example.atomic.utils

import com.example.atomic.interfaces.CallBack
import java.util.ArrayList

class ArrayListCustom<E> : ArrayList<E> {
    var callBack: CallBack? = null

    constructor(callBack: CallBack?) {
        this.callBack = callBack ?: return
    }

    override fun add(element: E): Boolean {
        val b = super.add(element)
//        callBack?.callBack()
        return b
    }

    override fun add(index: Int, element: E) {
        super.add(index, element)
//        callBack?.callBack()
    }

    override fun remove(element: E): Boolean {
        val b = super.remove(element)
//        callBack?.callBack()
        return b
    }

    fun update(element: E): E? {
        val resalt = this.find { it!!.equals(element) }
//        if (resalt != null)
//            callBack?.callBack()
        return resalt


    }

}

