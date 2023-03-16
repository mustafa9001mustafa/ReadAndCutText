package com.konden.readandcuttext.ui

import android.content.Intent
import android.os.Bundle
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.konden.readandcuttext.R
import com.konden.readandcuttext.Shard.ShardPreferans
import com.konden.readandcuttext.databinding.ActivitySettingsBinding
import com.konden.readandcuttext.fragment.LanguagesFragment
import com.konden.readandcuttext.listener.ListenerCallLanguage
import com.yariksoffice.lingver.Lingver

class SettingActivities : AppCompatActivity(), ListenerCallLanguage {

    private lateinit var binding: ActivitySettingsBinding
    private lateinit var dialog: LanguagesFragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)


        if (ShardPreferans.getInstance(this@SettingActivities).dark == true)
            binding.switchDark.isChecked = true
        else
            binding.switchDark.isChecked = false


        if (ShardPreferans.getInstance(this@SettingActivities).Last_str == true)
            binding.switchLastStr.isChecked = true
        else
            binding.switchLastStr.isChecked = false
    }

    override fun onStart() {
        super.onStart()
        all_Method()
        dark_mode()
        last_str()
    }

    private fun dark_mode() {
        binding.switchDark.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { compoundButton, b ->

            if (b == true) {
                ShardPreferans.getInstance(this@SettingActivities).saveDark(true)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                ShardPreferans.getInstance(this@SettingActivities).saveDark(false)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
            overridePendingTransition(R.anim.fadein, R.anim.fadeout)
        })
    }

    private fun last_str() {
        binding.switchLastStr.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { compoundButton, b ->
            if (b == true)
                ShardPreferans.getInstance(this@SettingActivities).saveLast_str(true)
            else
                ShardPreferans.getInstance(this@SettingActivities).saveLast_str(false)
        })
    }

    private fun all_Method() {
        binding.cardLan.setOnClickListener {
            dialog = LanguagesFragment.newInstance(
                getString(R.string.choosethelanguage),
                getString(R.string.Arabic),
                getString(R.string.english)
            )//show dialog description according to user type
            this@SettingActivities.supportFragmentManager.beginTransaction()
                .let { it1 ->
                    dialog.show(it1, LanguagesFragment::class.java.name)
                }
        }
    }

    override fun lis(lan: Boolean) {
        super.lis(lan)
        if (lan == true)
            Lingver.getInstance().setLocale(getApplicationContext(), "ar")
        else
            Lingver.getInstance().setLocale(getApplicationContext(), "en")
        overridePendingTransition(0, 0)
        dialog.dismiss()
    }

    override fun close() {
        dialog.dismiss()
    }

    override fun onBackPressed() {
        startActivity(Intent(this@SettingActivities, Strart::class.java))
    }

    override fun onStop() {
        super.onStop()
        finish()
        binding.leftIcon.cancelAnimation()

    }

    override fun recreate() {
        finish()
        overridePendingTransition(R.anim.fadein, R.anim.fadeout)
        startActivity(intent)
    }
}