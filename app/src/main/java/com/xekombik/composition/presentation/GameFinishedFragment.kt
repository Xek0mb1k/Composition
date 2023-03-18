package com.xekombik.composition.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import com.xekombik.composition.R
import com.xekombik.composition.databinding.FragmentGameBinding
import com.xekombik.composition.databinding.FragmentGameFinishedBinding
import com.xekombik.composition.domain.entity.GameResult

class GameFinishedFragment : Fragment() {




    private lateinit var gameResult: GameResult

    private var _binding: FragmentGameFinishedBinding? = null
    private val binding: FragmentGameFinishedBinding
        get() = _binding ?: throw RuntimeException("FragmentGameFinishedBinding == null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseArgs()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGameFinishedBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    retryGame()
                }
            })


        bindViews()

        binding.tryAgainButton.setOnClickListener {
            retryGame()
        }

    }

    private fun bindViews() {
        with(binding){
            emojiResult.setImageResource(getSmileResource())
            tvRequiredAnswers.text = String.format(
                getString(R.string.required_answers),
                gameResult.gameSettings.minCountOfRightAnswers
            )
            tvScore.text = String.format(
                getString(R.string.your_score),
                gameResult.countOfRightAnswers
            )
            tvRequiredPercentAnswers.text = String.format(
                getString(R.string.required_percent_true_answers),
                gameResult.gameSettings.minPercentOfRightAnswers
            )
            tvPercentTrueAnswers.text = String.format(
                getString(R.string.percent_true_answers),
                getPercentOfRightAnswers()

            )


        }
    }

    private fun getPercentOfRightAnswers() = with(gameResult) {
        if (countOfQuestions == 0){
            0
        }else
            ((countOfRightAnswers / countOfQuestions.toDouble()) * 100).toInt()
    }

    private fun getSmileResource(): Int {
        return if(gameResult.winner)
            R.drawable.smile
        else
            R.drawable.sad
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun parseArgs() {
        requireArguments().getParcelable<GameResult>(KEY_GAME_RESULT)?.let {
            gameResult = it
        }
    }

    private fun retryGame() {
        requireActivity().supportFragmentManager.popBackStack(
            GameFragment.NAME,
            FragmentManager.POP_BACK_STACK_INCLUSIVE
        )
    }


    companion object {

        private const val KEY_GAME_RESULT = "game_result"
        fun newInstance(gameResult: GameResult): GameFinishedFragment {
            return GameFinishedFragment().apply {
                arguments = Bundle().apply {

                    putParcelable(KEY_GAME_RESULT, gameResult)

                }
            }
        }
    }
}