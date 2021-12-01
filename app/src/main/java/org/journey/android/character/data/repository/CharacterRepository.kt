package org.journey.android.character.data.repository

import io.reactivex.rxjava3.core.Single
import org.journey.android.character.data.dto.request.RequestChangeCharacterDTO
import org.journey.android.character.data.dto.response.ResponseChangeCharacterDTO
import org.journey.android.character.data.dto.response.ResponseGetCharacterDTO
import org.journey.android.character.data.entity.CharacterInfoEntity
import org.journey.android.character.data.entity.MohaengCharacterEntity
import org.journey.android.character.data.entity.MohaengCharacterOptionEntity

interface CharacterRepository {
    fun changeCharacter(requestChangeCharacterDTO: RequestChangeCharacterDTO) : Single<ResponseChangeCharacterDTO>
   fun getCharacter(client : String) : Single<CharacterInfoEntity>
}
