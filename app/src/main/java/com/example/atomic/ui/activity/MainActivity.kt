package com.example.atomic.ui.activity

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import com.example.atomic.*
import com.example.atomic.data.CurrentMap
import com.example.atomic.utils.l
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd
import com.google.android.gms.ads.MobileAds


class MainActivity : Activity() {

    companion object {
        lateinit var act: Activity
        lateinit var mInterstitialAd: InterstitialAd

        fun start() {
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

        MobileAds.initialize(this, "ca-app-pub-1459945936645861~1274435601"
//            getString(R.string.ad_app_id)
        )
        mInterstitialAd = InterstitialAd(this)
        mInterstitialAd!!.adUnitId = "ca-app-pub-1459945936645861/7665320227"
//            getString(R.string.ad_advertising_id)

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
                l("onAdFailedToLoad() with error code: $errorCode")
            }

            override fun onAdClosed() {
                start()
            }
        }
        mInterstitialAd.loadAd(AdRequest.Builder().build())
        val numLevel = intent.getIntExtra("level", 1)
        CurrentMap.getCurrentMap(numLevel)
    }
}
