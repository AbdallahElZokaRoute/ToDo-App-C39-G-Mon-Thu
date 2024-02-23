package com.route.todoappc39g_mon_wed.fragments

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat.recreate
import androidx.fragment.app.Fragment
import com.route.todoappc39g_mon_wed.databinding.FragmentSettingsBinding
import java.util.Locale

class SettingsFragment : Fragment() {
    lateinit var binding: FragmentSettingsBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSettingsBinding.inflate(inflater)
        return binding.root
    }

    private fun setLocal(languageCode: String) {
        val local = Locale(languageCode)
        val config = Configuration()
        Locale.setDefault(local)
        config.setLocale(local)
        activity?.let {
            requireActivity().baseContext.resources.updateConfiguration(
                config,
                requireActivity().baseContext.resources.displayMetrics
            )
        }
    }

    private fun arabicLang(active: Boolean) {
        setLocal(if (active) "ar" else "en")
        activity?.let { recreate(it) }
    }


}