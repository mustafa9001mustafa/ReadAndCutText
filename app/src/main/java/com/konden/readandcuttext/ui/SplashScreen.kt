package com.konden.readandcuttext.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.konden.readandcuttext.Shard.ShardPreferans
import com.konden.readandcuttext.databinding.ActivitySplashScreenBinding

class SplashScreen : AppCompatActivity() {
    private lateinit var binding: ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        call_one()

        binding.leftIconSplach.alpha = 0f
        binding.leftIconSplach.animate().setDuration(3000).alpha(1f).withEndAction(Runnable {
            if (ShardPreferans.getInstance(this@SplashScreen).dark == true)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            else if (ShardPreferans.getInstance(this@SplashScreen).dark == false)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

            startActivity(Intent(this, Strart::class.java))
        })
        binding.appname.animate().alpha(1f).setDuration(3000).alpha(1f)
    }


    private fun call_one() {
        if (ShardPreferans.getInstance(this@SplashScreen).isFirstTimeOther()) {
            ShardPreferans.getInstance(this@SplashScreen).saveLast_str(true)

            if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES)
                ShardPreferans.getInstance(this@SplashScreen).saveDark(true)
            else if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_NO)
                ShardPreferans.getInstance(this@SplashScreen).saveDark(false)
        }
    }

    override fun onStop() {
        super.onStop()
        finish()
    }

    override fun onBackPressed() {
        finish()
        System.exit(0)

    }

    override fun recreate() {
        finish()
        startActivity(intent)
        finish()
    }
}