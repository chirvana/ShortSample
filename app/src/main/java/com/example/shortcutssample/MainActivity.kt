package com.example.shortcutssample

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.pm.ShortcutManagerCompat
import com.example.shortcutssample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button1.setOnClickListener {
            startActivity(Intent(this, SecondActivity::class.java))
        }
        if (intent.hasExtra("firstSc")) {
            binding.textView1.text = "From Static SC1"
            ShortcutManagerCompat.reportShortcutUsed(this, "sc1")
        } else {
            binding.textView1.text = "Activity I"
        }
    }
}