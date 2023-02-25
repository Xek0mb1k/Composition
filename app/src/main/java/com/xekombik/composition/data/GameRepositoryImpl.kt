package com.xekombik.composition.data

import com.xekombik.composition.domain.entity.GameSettings
import com.xekombik.composition.domain.entity.Level
import com.xekombik.composition.domain.entity.Question
import com.xekombik.composition.domain.repository.GameRepository
import kotlin.random.Random

object GameRepositoryImpl: GameRepository {

    private const val MIN_SUM_VALUE = 2
    private const val MIN_ANSWER_VALUE = 1
    override fun generateQuestion(maxSumValue: Int, countOfOptions: Int): Question {
        val sum = Random.nextInt(MIN_SUM_VALUE, maxSumValue+1)
        val visibleNumber = Random.nextInt(MIN_ANSWER_VALUE, sum)

    }

    override fun getGameSettings(level: Level): GameSettings {
        TODO("Not yet implemented")
    }
}