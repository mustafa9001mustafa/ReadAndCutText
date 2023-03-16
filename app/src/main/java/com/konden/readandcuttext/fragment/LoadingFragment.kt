package com.konden.readandcuttext.fragment

import android.content.Context
import android.content.Intent
import android.graphics.Insets
import android.graphics.Point
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.*
import android.widget.FrameLayout
import androidx.fragment.app.DialogFragment
import com.konden.readandcuttext.R
import com.konden.readandcuttext.databinding.FragmentLodingBinding
import com.konden.readandcuttext.listener.ListenerCallLoading
import com.konden.readandcuttext.ui.Strart


private var _binding: FragmentLodingBinding? = null
private val binding get() = _binding!!
private var style: Int = DialogFragment.STYLE_NO_TITLE
private var themes: Int = R.style.MyDialog
private lateinit var listener: ListenerCallLoading


class LoadingFragment : DialogFragment() {


    // interface
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ListenerCallLoading) {
            listener = context
        } else {
            throw ClassCastException(requireContext().toString() + " must implement Listener.")
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            setStyle(style, themes)


        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLodingBinding.inflate(inflater, container, false)

        Handler(Looper.getMainLooper()).postDelayed({
            listener.loading()
        }, 2000)


        return binding.root
    }

    companion object {
        fun newInstance() =
            LoadingFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.window?.attributes?.width = FrameLayout.LayoutParams.MATCH_PARENT
        dialog?.window?.attributes?.height = FrameLayout.LayoutParams.WRAP_CONTENT
    }
}