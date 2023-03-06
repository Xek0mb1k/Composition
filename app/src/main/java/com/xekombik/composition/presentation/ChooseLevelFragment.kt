package com.xekombik.composition.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.xekombik.composition.R
import com.xekombik.composition.databinding.FragmentChooseLevelBinding
import com.xekombik.composition.domain.entity.Level

class ChooseLevelFragment : Fragment() {
    private var _binding: FragmentChooseLevelBinding? = null
    private val binding: FragmentChooseLevelBinding
        get() = _binding ?: throw RuntimeException("FragmentChooseLevelBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentChooseLevelBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            testLevelButton.setOnClickListener {
                launchChooseLevelFragment(Level.TEST)
            }
            easyLevelButton.setOnClickListener {
                launchChooseLevelFragment(Level.EASY)
            }
            normalLevelButton.setOnClickListener {
                launchChooseLevelFragment(Level.NORMAL)
            }
            hardLevelButton.setOnClickListener {
                launchChooseLevelFragment(Level.HARD)
            }
        }


    }

    private fun launchChooseLevelFragment(level: Level) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, GameFragment.newInstance(level))
            .addToBackStack(GameFragment.NAME)
            .commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        const val NAME = "ChooseLevelFragment"

        fun newInstance(): ChooseLevelFragment {
            return ChooseLevelFragment()
        }
    }
}