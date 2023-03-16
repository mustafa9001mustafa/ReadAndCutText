package com.konden.readandcuttext.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.PrecomputedTextCompat
import androidx.core.widget.TextViewCompat
import com.konden.readandcuttext.R
import com.konden.readandcuttext.Shard.ShardPreferans
import com.konden.readandcuttext.databinding.ActivityReadTextBinding
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.ref.WeakReference

class ReadText : AppCompatActivity() {
    private lateinit var binding: ActivityReadTextBinding
    private lateinit var text_all: String
    private var b_lan: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReadTextBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        all_Method()
    }

    private fun all_Method() {
        Get_Text_Info()
        Number_Word()
        ChartAt_All()
        setWordsCounter()
        longestWordText()
        minWordText()
        Edit_btn()
        languages()
    }

    private fun languages() {
        binding.language.setOnClickListener(View.OnClickListener {
            if (b_lan == false) {

                binding.chartAtA.text =
                    getString(R.string.chart_at_a_ar) + " " + countOccurrences(text_all, 'ا')
                binding.chartAtT.text =
                    getString(R.string.chart_at_t_ar) + " " + countOccurrences(text_all, 'ت')
                binding.chartAtR.text =
                    getString(R.string.chart_at_r_ar) + " " + countOccurrences(text_all, 'ر')
                binding.chartAtI.text =
                    getString(R.string.chart_at_i_ar) + " " + countOccurrences(text_all, 'ي')
                binding.chartAtN.text =
                    getString(R.string.chart_at_n_ar) + " " + countOccurrences(text_all, 'ن')
                binding.chartAtG.text =
                    getString(R.string.chart_at_g_ar) + " " + countOccurrences(text_all, 'ج')
                binding.chartAtM.text =
                    getString(R.string.chart_at_m_ar) + " " + countOccurrences(text_all, 'م')
                binding.chartAtS.text =
                    getString(R.string.chart_at_s_ar) + " " + countOccurrences(text_all, 'س')
                binding.language.text = getString(R.string.Arabic)
                b_lan = true
            } else {
                get_all_text()

                b_lan = false
            }
        })

    }

    private fun Edit_btn() {
        binding.edit.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@ReadText, EditActivity::class.java)
            intent.putExtra("text_to_change", text_all)
            startActivity(intent)
        })
    }

    private fun minWordText() {
        binding.minTextWord.text = MintWordText(text_all)
    }

    private fun MintWordText(word: String): String? {
        return word.split(" ")
            .filter { it.isNotBlank() }
            .minByOrNull { it.length }
    }

    private fun longestWordText() {
        binding.longestWord.text = longestWord(text_all)
    }


    private fun longestWord(word: String): String? {
        return word.split(" ")
            .filter { it.isNotBlank() }
            .maxByOrNull { it.length }
    }

    private fun setWordsCounter() {
        for (c in text_all) {
            if ("$c" == " ") {
                val text_no_spac = text_all.replace("$c", "")
                binding.numberChartAtAllWithoutSpec.text = text_no_spac.length.toString()
            }
        }

        if (binding.numberChartAtAllWithoutSpec.text.toString().equals(""))
            binding.numberChartAtAllWithoutSpec.text = text_all.length.toString()
    }

    private fun ChartAt_All() {
        binding.numberChartAtAll.text = text_all.length.toString()
    }

    private fun Number_Word() {
        val count = Regex("""(\s+|(\r\n|\r|\n))""").findAll(text_all.trim()).count() + 1
        binding.numberWord.text = count.toString()
    }

    private fun Get_Text_Info() {
//        val strText: String = intent.getStringExtra("text").toString()
        binding.getText.text = All_text()
        text_all = All_text()
        get_all_text()








    }
    private fun All_text(): String {
        return ShardPreferans.getInstance(this@ReadText).str.toString()
    }


    private fun get_all_text(){
        binding.chartAtA.text =
            getString(R.string.chart_at_a) + " " + countOccurrences(text_all, 'a')
        binding.chartAtT.text =
            getString(R.string.chart_at_t) + " " + countOccurrences(text_all, 't')
        binding.chartAtR.text =
            getString(R.string.chart_at_r) + " " + countOccurrences(text_all, 'r')
        binding.chartAtI.text =
            getString(R.string.chart_at_i) + " " + countOccurrences(text_all, 'i')
        binding.chartAtN.text =
            getString(R.string.chart_at_n) + " " + countOccurrences(text_all, 'n')
        binding.chartAtG.text =
            getString(R.string.chart_at_g) + " " + countOccurrences(text_all, 'g')
        binding.chartAtM.text =
            getString(R.string.chart_at_m) + " " + countOccurrences(text_all, 'm')
        binding.chartAtS.text =
            getString(R.string.chart_at_s) + " " + countOccurrences(text_all, 's')
        binding.language.text = getString(R.string.english)

    }

    private fun countOccurrences(s: String, ch: Char): Int {
        return s.filter { it == ch }.count()
    }

    override fun onStop() {
        super.onStop()
        finish()
    }

    override fun onBackPressed() {
        startActivity(Intent(this@ReadText,MainActivity::class.java))
    }
}