package com.example.shortcutssample

import android.app.Application
import android.util.Log
import androidx.core.content.pm.ShortcutManagerCompat

class ShortCutSampleApp: Application() {

    override fun onCreate() {
        super.onCreate()
        val numOfStaticShortCuts = ShortcutManagerCompat.getShortcuts(this, ShortcutManagerCompat.FLAG_MATCH_MANIFEST).size
        val numOfDynamicShortCuts = ShortcutManagerCompat.getDynamicShortcuts(this).size
        Log.i("ShortCutLog", "Num of static shortcuts: $numOfStaticShortCuts \n Num of dynamic shortcuts:$numOfDynamicShortCuts")
    }
}