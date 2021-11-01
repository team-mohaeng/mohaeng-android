package org.journey.android.character.data.repository

import io.reactivex.rxjava3.core.Single
import org.journey.android.character.data.entity.MohaengCharacterEntity
import org.journey.android.character.data.entity.MohaengCharacterOptionEntity

interface CharacterRepository {
    fun getCharacterOption() : Single<List<MohaengCharacterOptionEntity>>
    fun getCharacterSkin() : Single<List<MohaengCharacterEntity>>
}