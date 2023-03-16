package com.konden.readandcuttext.ui

import android.app.PendingIntent.getActivity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.konden.readandcuttext.databinding.ActivityStrartBinding
import com.konden.readandcuttext.fragment.CloseFragment
import com.konden.readandcuttext.fragment.LanguagesFragment
import com.konden.readandcuttext.listener.ListenerCallClose

class Strart : AppCompatActivity(), ListenerCallClose {
    private lateinit var binding: ActivityStrartBinding
    private lateinit var dialog: CloseFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStrartBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        start_add()
        setting()
    }

    private fun setting() {
        binding.leftIconDelete.setOnClickListener {
            startActivity(Intent(this, SettingActivities::class.java))
        }
    }

    private fun start_add() {
        binding.card2.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        })
    }

    override fun onStop() {
        super.onStop()
        finish()
        binding.leftIconDelete.cancelAnimation()

    }

    override fun onBackPressed() {
        dialog = CloseFragment.newInstance()
        this@Strart.supportFragmentManager.beginTransaction().let { it1 ->
            dialog.show(it1, CloseFragment::class.java.name)
        }
    }

    override fun close() {
        dialog.dismiss()
        finish()
    }
}