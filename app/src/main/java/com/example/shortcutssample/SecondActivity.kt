package com.example.shortcutssample

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.pm.ShortcutInfoCompat
import androidx.core.content.pm.ShortcutManagerCompat
import androidx.core.graphics.drawable.IconCompat
import com.example.shortcutssample.databinding.ActivitySecondBinding
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.dynamicButton.setOnClickListener {
            createDynamicShortCut()
        }
    }

    private fun createDynamicShortCut() {
        val shortCut = ShortcutInfoCompat.Builder(this, "dynamicId")
            .setShortLabel("DynamicShortCut")
            .setShortLabel("Dyna")
            .setLongLabel("Open dynamic shortcut")
            .setIcon(IconCompat.createWithResource(this, R.drawable.ic_baseline_local_fire_department_24))
            .setIntent(Intent(Intent.ACTION_VIEW, Uri.parse("https://www.carvana.com")))
            .build()
        ShortcutManagerCompat.pushDynamicShortcut(this, shortCut)
        Snackbar.make(binding.secondContainer, "Created dynamic shortcut", BaseTransientBottomBar.LENGTH_SHORT).show()
    }
}