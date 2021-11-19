package org.journey.android.character.data.entity

import org.journey.android.character.data.dto.CharacterDTO
import org.journey.android.character.data.dto.CurrentCharacterDTO
import org.journey.android.character.data.dto.CurrentSkinDTO
import org.journey.android.character.data.dto.SkinDTO

data class CharacterInfoEntity(
    val currentCharacterImage: CurrentCharacterDTO,
    val currentCharacterSkin: CurrentSkinDTO,
    val characterList: List<CharacterEntity>,
    val skinList: List<SkinDTO>
)