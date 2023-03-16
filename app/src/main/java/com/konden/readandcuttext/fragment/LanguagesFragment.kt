package com.konden.readandcuttext.fragment

import android.content.Context
import android.graphics.Insets
import android.graphics.Point
import android.os.Build
import android.os.Bundle
import android.view.*
import android.widget.FrameLayout
import androidx.fragment.app.DialogFragment
import com.konden.readandcuttext.R
import com.konden.readandcuttext.databinding.FragmentLanguagesBinding
import com.konden.readandcuttext.listener.ListenerCallLanguage

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private const val ARG_PARAM3 = "param3"
    private lateinit var listener: ListenerCallLanguage
private var b1: Boolean = false
private var b2: Boolean = false

private var style: Int = DialogFragment.STYLE_NO_TITLE
private var themes: Int = R.style.MyDialog
private var _binding: FragmentLanguagesBinding? = null
private val binding get() = _binding!!


class LanguagesFragment : DialogFragment() {
    private var param1: String? = null
    private var param2: String? = null
    private var param3: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
            param3 = it.getString(ARG_PARAM3)
            setStyle(style, themes)

        }
    }


    // interface
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ListenerCallLanguage)
            listener = context
        else
            throw ClassCastException(requireContext().toString() + " must implement OnTimeListener.")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLanguagesBinding.inflate(inflater, container, false)

        binding.lan1.setOnClickListener(View.OnClickListener {
            binding.lan1.setBackgroundResource(R.drawable.shaponclick)
            binding.lan2.setBackgroundResource(R.drawable.shapnotonclick)
            b1 = true
            b2 = false
        })

        binding.lan2.setOnClickListener(View.OnClickListener {
            binding.lan2.setBackgroundResource(R.drawable.shaponclick)
            binding.lan1.setBackgroundResource(R.drawable.shapnotonclick)
            b1 = false
            b2 = true
        })

        binding.itTextLan.text = param1
        binding.lan1.text = param2
        binding.lan2.text = param3

        binding.btnSaveData.setOnClickListener(View.OnClickListener {
            if (b1 == true)
                listener.lis(true)
            else
                listener.lis(false)})

        binding.cloIcon.setOnClickListener(View.OnClickListener {
            listener.close()})

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String, param3: String) =
            LanguagesFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                    putString(ARG_PARAM3, param3)
                }
            }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.window?.attributes?.width = FrameLayout.LayoutParams.MATCH_PARENT
        dialog?.window?.attributes?.height = FrameLayout.LayoutParams.WRAP_CONTENT
    }}