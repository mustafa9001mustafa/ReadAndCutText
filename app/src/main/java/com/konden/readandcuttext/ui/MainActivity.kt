package com.konden.readandcuttext.ui

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.google.android.material.snackbar.Snackbar
import com.konden.readandcuttext.R
import com.konden.readandcuttext.Shard.ShardPreferans
import com.konden.readandcuttext.databinding.ActivityMainBinding
import com.konden.readandcuttext.fragment.LanguagesFragment
import com.konden.readandcuttext.fragment.LoadingFragment
import com.konden.readandcuttext.listener.ListenerCallLoading

class MainActivity : AppCompatActivity(), ListenerCallLoading {
    private lateinit var binding: ActivityMainBinding
    private lateinit var intents: Intent
    private lateinit var dialog: LoadingFragment
    private lateinit var text: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        gobutton()
        delete_btn()
        set_text_switch()
    }

    private fun set_text_switch() {
        if (ShardPreferans.getInstance(this@MainActivity).Last_str == true)
            binding.editText.setText(ShardPreferans.getInstance(this@MainActivity).str.toString())

        if (binding.editText.text.toString().equals("null"))
            binding.editText.setText("")
    }

    private fun delete_btn() {
        binding.editText.addTextChangedListener {
            if (binding.editText.text.toString().equals(""))
            else
                binding.leftIconDelete.visibility = View.VISIBLE
        }

        binding.leftIconDelete.setOnClickListener(View.OnClickListener {
            binding.editText.setText("")
            binding.leftIconDelete.visibility = View.GONE

        })
    }

    private fun gobutton() {
        binding.go.setOnClickListener(View.OnClickListener {
            GetAllText()
        })
    }

    private fun GetAllText() {

        text = binding.editText.text.toString()
        if (!text.equals("")) {
            dialog = LoadingFragment.newInstance()
            this@MainActivity.supportFragmentManager.beginTransaction()
                .let { it1 -> dialog.show(it1, LanguagesFragment::class.java.name) }
        } else {
            val snackbar = Snackbar.make(binding.root, "", Snackbar.LENGTH_LONG)

            val customSnackView: View = layoutInflater.inflate(R.layout.snackbar_custom, null)
            snackbar.view.setBackgroundColor(Color.TRANSPARENT)
            val snackbarLayout = snackbar.view as Snackbar.SnackbarLayout
            snackbarLayout.setPadding(0, 0, 0, 0)
            snackbarLayout.addView(customSnackView, 0)
            snackbar.show()
        }
    }

    override fun onBackPressed() {
        startActivity(Intent(this, Strart::class.java))
    }

    override fun loading() {
        intents = Intent(this, ReadText::class.java)
        intents.putExtra("text", text)
        startActivity(intents)
        ShardPreferans.getInstance(this@MainActivity).saveStr(text)
        dialog.dismiss()
    }

    override fun onStop() {
        super.onStop()
        finish()
        binding.leftIconDelete.cancelAnimation()
    }
}
