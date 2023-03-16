package com.konden.readandcuttext.ui

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.konden.readandcuttext.R
import com.konden.readandcuttext.Shard.ShardPreferans
import com.konden.readandcuttext.databinding.ActivityEditBinding

class EditActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditBinding
    private lateinit var text_to_change: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        All_Method()
    }

    private fun All_Method() {
        get_text()
        Replis_btn()
        imag_save()
        spinner_adapter()
        btn_remove()
    }

    private fun btn_remove() {

        binding.changeBtnSpace.setOnClickListener {
            if (!binding.changeSpase.text.toString().equals("")) {


                binding.editText.setText(removeWhiteSpace(binding.editText.text.toString()))
                ShardPreferans.getInstance(this@EditActivity).saveStr(
                    removeWhiteSpace(binding.editText.text.toString())
                )
            } else
                Snackbar.make(
                    binding.root,
                    getString(R.string.entertochange),
                    Snackbar.LENGTH_SHORT
                ).show()
        }
    }

    fun removeWhiteSpace(menuName: String): String {
        return menuName.replace(" ", binding.changeSpase.text.toString())

    }

    private fun spinner_adapter() {
        val text_c = resources.getStringArray(R.array.text_c)

        if (binding.spinner != null) {
            val adapter = ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item, text_c
            )
            binding.spinner.adapter = adapter
            binding.spinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View, position: Int, id: Long
                ) {

                    val s = binding.editText.text.toString()

                    if (position == 0) {
                        binding.editText.setText("")
                        binding.editText.setText(ShardPreferans.getInstance(this@EditActivity).str.toString())

                    } else if (position == 1) {
                        val x1 = binding.editText.text.toString()
                        binding.editText.setText("")
                        binding.editText.setText(x1.uppercase())

                    } else if (position == 2) {
                        val x2 = binding.editText.text.toString()
                        binding.editText.setText("")
                        binding.editText.setText(x2.lowercase())

                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }
    }

    private fun imag_save() {
        binding.copy.setOnClickListener(View.OnClickListener {
            copyToClipBoard(this@EditActivity, binding.editText.text.toString())
        })
    }

    private fun Replis_btn() {
        binding.editBtn.setOnClickListener(View.OnClickListener {


            if (!binding.repliesTextBefore.text.toString()
                    .equals("") && !binding.repliesTextNew.text.toString().equals("")
            ) {
                val re_before = binding.repliesTextBefore.text.toString()
                val re_new = binding.repliesTextNew.text.toString()
                val sfs = binding.editText.text.toString().replace(re_before, re_new)
                binding.editText.setText(sfs)
                ShardPreferans.getInstance(this@EditActivity).saveStr(sfs)
            } else {
                Snackbar.make(
                    binding.root,
                    getString(R.string.enter_text_replies),
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        })
    }

    private fun copyToClipBoard(context: Context, message: String) {
        val clipBoard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clipData = ClipData.newPlainText("label", message)
        clipBoard.setPrimaryClip(clipData)
        Snackbar.make(binding.root, getString(R.string.copy), Snackbar.LENGTH_SHORT).show()
    }

    private fun get_text() {
        val strText: String = intent.getStringExtra("text_to_change").toString()
        text_to_change = strText
        binding.editText.setText(strText)
    }

    override fun onStop() {
        super.onStop()
        finish()
    }

    override fun onBackPressed() {
        startActivity(Intent(this@EditActivity, ReadText::class.java))
        ShardPreferans.getInstance(this@EditActivity).saveStr(binding.editText.text.toString())
    }
}