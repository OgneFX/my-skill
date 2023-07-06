package com.example.finalskillcinema.ui.intro


import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.finalskillcinema.R
import com.example.finalskillcinema.data.IntroResources
import com.example.finalskillcinema.databinding.IntroFragmentBinding
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class IntroFragment : Fragment() {

    private var _binding: IntroFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: IntroPageAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d("Registration token", "создание CreateView")
        _binding = IntroFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("Registration token", "создание ViewCreated")
        val listIntro = listOf(
            IntroResources(
                imageId = R.drawable.wfh_2,
                message = resources.getString(R.string.intro_first_text)
            ),
            IntroResources(
                imageId = R.drawable.wfh_4_1,
                message = resources.getString(R.string.intro_second_text)
            ),
            IntroResources(
                imageId = R.drawable.wfh_8,
                message = resources.getString(R.string.intro_third_text)
            )
        )

        adapter = IntroPageAdapter(listIntro)
        binding.introViewpager.adapter = adapter
        TabLayoutMediator(binding.tab, binding.introViewpager) { _, _ -> }.attach()

        binding.buttonIntroSkip.setOnClickListener { skipIntroClick() }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        findNavController().clearBackStack(R.id.introFragment)
        Log.d("Registration token", "Уничитожение фрагмента")
        _binding = null
    }

    private fun skipIntroClick() {
        PreferenceManager.getDefaultSharedPreferences(context).edit().apply {
            putBoolean(INTRO_PREFS_NAME, true)
            apply()

        }
        Log.d("Registration token", "Нажал на кнопку")
        findNavController().navigate(R.id.action_introFragment_to_menuFragment)
    }

    companion object {
        const val INTRO_PREFS_NAME = "intro pref"
    }
}