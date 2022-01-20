package com.example.shortcutssample

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.pm.ShortcutInfoCompat
import androidx.core.content.pm.ShortcutManagerCompat
import androidx.core.graphics.drawable.IconCompat
import com.example.shortcutssample.databinding.ActivityMainBinding
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button1.setOnClickListener {
            startActivity(Intent(this, SecondActivity::class.java))
        }
        when {
            intent.action == SHORTCUT_CREATED -> {
                binding.textView1.text = "From Dynamic Pin shortcut"
            }
            intent.hasExtra("firstSc") -> {
                binding.textView1.text = "From Static SC1"
               // ShortcutManagerCompat.reportShortcutUsed(this, "sc1")
            }
            else -> {
                binding.textView1.text = "Activity I"
            }
        }
        binding.pinButton.setOnClickListener {
            if (ShortcutManagerCompat.isRequestPinShortcutSupported(this)) {

                val intent = Intent(this, MainActivity::class.java).apply {
                    action = SHORTCUT_CREATED
                }

                val pinShortcutInfo = ShortcutInfoCompat
                    .Builder(this, "sc1Dynamic")
                    //if you pass in sc1 for id it will use existing sc1 static shortcut
                    .setIntent(intent)
                    .setShortLabel("DynamicPin")
                    .setLongLabel("DynamicPinLabel longName")
                    .setIcon(IconCompat.createWithResource(this, R.drawable.spanner_pin))
                    .build()

/*                // Create the PendingIntent object only if your app needs to be notified
                // that the user allowed the shortcut to be pinned. Note that, if the
                // pinning operation fails, your app isn't notified. We assume here that the
                // app has implemented a method called createShortcutResultIntent() that
                // returns a broadcast intent.
                val pinnedShortcutCallbackIntent = ShortcutManagerCompat.createShortcutResultIntent(this, pinShortcutInfo)

                // Configure the intent so that your app's broadcast receiver gets
                // the callback successfully.For details, see PendingIntent.getBroadcast().
                val flag: Int = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) { PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT } else { PendingIntent.FLAG_UPDATE_CURRENT }
                val successCallback = PendingIntent.getBroadcast(this,  123,
                    pinnedShortcutCallbackIntent, flag)*/

                ShortcutManagerCompat.requestPinShortcut(this, pinShortcutInfo, null)
            } else {
                Snackbar.make(
                    binding.firstContainer,
                    "Pinning not supported",
                    BaseTransientBottomBar.LENGTH_SHORT
                ).show()
            }
        }
    }


    companion object {
        const val SHORTCUT_CREATED = "ShortCutCreated"
    }
}