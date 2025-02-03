package com.example.todo.ui.Home.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatDelegate
import com.example.todo.Constants.SpinnerItems
import com.example.todo.databinding.FragmentSettingsBinding
import com.example.todo.ui.adapters.SpinnerAdapter

class SettingsFragment : Fragment() {
    private lateinit var binding: FragmentSettingsBinding
    private var isFirst = true
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSettingsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initSpinners()
        setInitialSelections()
        initListeners()
    }

    private fun initSpinners() {
        binding.langSp.adapter = SpinnerAdapter(requireContext(), SpinnerItems.languages)
        binding.modeSp.adapter = SpinnerAdapter(requireContext(), SpinnerItems.Modes)
    }

    private fun setInitialSelections() {
        // Set initial mode selection
        val currentNightMode = AppCompatDelegate.getDefaultNightMode()
        val modePosition = when (currentNightMode) {
            AppCompatDelegate.MODE_NIGHT_NO -> 0
            AppCompatDelegate.MODE_NIGHT_YES -> 1
            else -> 0
        }
        binding.modeSp.setSelection(modePosition)
    }

    private fun initListeners() {
        binding.langSp.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, pos: Int, p3: Long) {
                val selectedLanguage = SpinnerItems.languages[pos]
                changeLang(selectedLanguage.title)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }

        binding.modeSp.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, pos: Int, p3: Long) {
                val newMode = if (pos == 0) {
                    AppCompatDelegate.MODE_NIGHT_NO
                } else {
                    AppCompatDelegate.MODE_NIGHT_YES
                }

                val currentMode = AppCompatDelegate.getDefaultNightMode()
                if (newMode != currentMode) {
                    changeMode(newMode)
                    if (!isFirst) {
                        requireActivity().recreate()
                    } else {
                        isFirst = false
                    }
                }
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }
    }

    private fun changeLang(language: String) {

    }

    private fun changeMode(mode: Int) {
        AppCompatDelegate.setDefaultNightMode(mode)
    }
}