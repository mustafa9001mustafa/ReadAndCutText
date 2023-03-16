package com.konden.readandcuttext.fragment

import android.content.Context
import android.content.res.Resources
import android.graphics.Insets
import android.graphics.Point
import android.graphics.Rect
import android.hardware.display.DisplayManager
import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.*
import android.widget.FrameLayout
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.DialogFragment
import com.konden.readandcuttext.R
import com.konden.readandcuttext.databinding.FragmentCloseBinding
import com.konden.readandcuttext.listener.ListenerCallClose


private lateinit var listener: ListenerCallClose
private var style: Int = DialogFragment.STYLE_NO_TITLE
private var themes: Int = R.style.MyDialog
private var bind: FragmentCloseBinding? = null
private val binding get() = bind!!

class CloseFragment : DialogFragment() {

    // interface
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ListenerCallClose)
            listener = context
        else
            throw ClassCastException(requireContext().toString() + " must implement OnTimeListener.")
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
        bind = FragmentCloseBinding.inflate(inflater, container, false)


        binding.ok.setOnClickListener {
            listener.close()
        }
        return binding.root
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            CloseFragment().apply {
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