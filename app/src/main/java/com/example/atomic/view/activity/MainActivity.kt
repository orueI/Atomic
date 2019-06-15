package com.example.atomic.view.activity

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.atomic.*
import com.example.atomic.data.CurrentMap
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd
import com.google.android.gms.ads.MobileAds


class MainActivity : AppCompatActivity() {

    companion object {
        lateinit var act: Activity
        lateinit var mInterstitialAd: InterstitialAd

        fun start() {
            // Request a new ad if one isn't already loaded, hide the button, and kick off the timer.
            if (!mInterstitialAd?.isLoading!! && !mInterstitialAd?.isLoaded!!) {
                val adRequest = AdRequest.Builder()
                    .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                    .build()
                mInterstitialAd?.loadAd(adRequest)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        act = this
        setContentView(R.layout.activity_main)

        MobileAds.initialize(this, "ca-app-pub-1459945936645861~1274435601" )
        mInterstitialAd = InterstitialAd(this)
        mInterstitialAd!!.adUnitId =
            "ca-app-pub-3940256099942544/1033173712"
//            getString(R.string.ad_unit_id)

        mInterstitialAd!!.adListener = object : AdListener() {
            override fun onAdLoaded() {
                Toast.makeText(baseContext, "onAdLoaded()", Toast.LENGTH_SHORT).show()
            }

            override fun onAdFailedToLoad(errorCode: Int) {
                Toast.makeText(
                    baseContext,
                    "onAdFailedToLoad() with error code: $errorCode",
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onAdClosed() {
                start()
            }
        }
        val numLevel = intent.getIntExtra("level", 1)
        CurrentMap.getCurrentMap(numLevel)




//        val s = gson.toJson(CreateLevels().getLevel())
//        l(s)
    }
}
