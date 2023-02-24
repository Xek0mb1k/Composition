package com.xekombik.composition.domain.repository

import com.xekombik.composition.domain.entity.GameSettings
import com.xekombik.composition.domain.entity.Level
import com.xekombik.composition.domain.entity.Question

interface GameRepository {
    fun generateQuestion(
        maxSumValue: Int,
        countOfOptions: Int
    ): Question

    fun getGameSettings(level: Level): GameSettings
}