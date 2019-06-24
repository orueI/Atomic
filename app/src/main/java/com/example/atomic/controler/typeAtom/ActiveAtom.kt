package com.example.atomic.controler.typeAtom

import com.example.atomic.Atom
import com.example.atomic.Direction
import com.example.atomic.Vector
import com.example.atomic.XY
import com.example.atomic.data.CurrentMap
import com.example.atomic.interfaces.InterfaceMapView
import com.example.atomic.utils.l
import com.example.atomic.ui.activity.MainActivity.Companion.mInterstitialAd
import com.example.atomic.ui.activity.MainActivity.Companion.start
import com.google.android.gms.ads.AdRequest


class ActiveAtom {
    var view: InterfaceMapView
    var checkedList = ArrayList<Atom>()

    constructor(view: InterfaceMapView) {
        this.view = view
    }

    fun clickOnVector(vector: Vector) {
        val map = CurrentMap.getCurrentMap()
        when (vector.vector.name) {
            "right" -> {
                map.listAtoms.find { it!!.equals(vector.atom) }?.xy?.x =
                    map.getFirstNoNPassabilityCAll(vector.atom.xy, 1, 0).x - 1
            }
            "left" -> {
                map.listAtoms.find { it!!.equals(vector.atom) }?.xy?.x =
                    map.getFirstNoNPassabilityCAll(vector.atom.xy, -1, 0).x + 1
            }
            "top" -> {
                map.listAtoms.find { it!!.equals(vector.atom) }?.xy?.y =
                    map.getFirstNoNPassabilityCAll(vector.atom.xy, 0, 1).y - 1
            }
            "dawn" -> {
                map.listAtoms.find { it!!.equals(vector.atom) }?.xy?.y =
                    map.getFirstNoNPassabilityCAll(vector.atom.xy, 0, -1).y + 1
            }
        }
        map.listVector = ArrayList()
        PassiveAtom(view).clickOnAtom(vector.atom)
        chackResalt(vector.atom)
        view.render()
    }

    private fun levelPassed() {
        l("You are right!")
        if (mInterstitialAd != null && mInterstitialAd?.isLoaded!!) {
            mInterstitialAd?.show()
            mInterstitialAd.loadAd(AdRequest.Builder().build())
        } else {
            start()
            l("The interstitial wasn't loaded yet.")
        }
        CurrentMap.getCurrentMap(CurrentMap.getCurrentMap().numLevel + 1)
    }

    fun chackResalt(realAtom: Atom) {
        val map = CurrentMap.getCurrentMap()
        checkedList = ArrayList()

        for (needAtom in map.listResultAtoms) {
            if (needAtom.type == realAtom.type && needAtom.vectorConnects.contentEquals(realAtom.vectorConnects)) {
                checkedList = ArrayList()
                checkAtom(realAtom, needAtom)
            }
        }
    }

    private fun checkAtom(realAtom: Atom, needAtom: Atom) {
        val map = CurrentMap.getCurrentMap()

        if (checkedList.find { it.equals(realAtom) } == null) {
            checkedList.add(realAtom)
            for (connection in realAtom.vectorConnects) {
                val checkingAtomNeed = otherAtomInConnection(needAtom, connection, map.listResultAtoms)
                val checkingAtom = otherAtomInConnection(realAtom, connection, map.listAtoms)
                if (checkingAtom != null && checkingAtom.type == checkingAtomNeed?.type
                    && checkingAtom.vectorConnects.contentEquals(
                        checkingAtomNeed.vectorConnects
                    )
                ) {
                    checkAtom(checkingAtom, checkingAtomNeed)
                }
            }
        } else{
            if(checkedList.size == map.listResultAtoms.size) {
                levelPassed()

            }
        }
    }

    fun otherAtomInConnection(needAtom: Atom, vector: Direction, list: ArrayList<Atom>): Atom? =
        list.find {
            it.xy == XY(
                needAtom.xy.x + vector.getCos(),
                needAtom.xy.y + vector.getSin()
            )
        }
}