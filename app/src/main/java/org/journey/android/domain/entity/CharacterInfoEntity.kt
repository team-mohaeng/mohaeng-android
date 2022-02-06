package org.journey.android.domain.entity

import org.journey.android.presentation.main.character.data.dto.CurrentCharacterDTO
import org.journey.android.presentation.main.character.data.dto.CurrentSkinDTO
import org.journey.android.presentation.main.character.data.dto.SkinDTO

data class CharacterInfoEntity(
    val currentCharacterImage: CurrentCharacterDTO,
    val currentCharacterSkin: CurrentSkinDTO,
    val characterList: List<CharacterEntity>,
    val skinList: List<SkinDTO>
)