package com.xekombik.composition.domain.usecases

import com.xekombik.composition.domain.entity.GameSettings
import com.xekombik.composition.domain.entity.Level
import com.xekombik.composition.domain.repository.GameRepository

class GetGameSettingsUseCase(
    private val repository: GameRepository
) {

    operator fun invoke(level: Level): GameSettings {
        return repository.getGameSettings(level)
    }
}