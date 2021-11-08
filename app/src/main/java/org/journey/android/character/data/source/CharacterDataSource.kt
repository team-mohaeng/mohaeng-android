package org.journey.android.character.data.source

import io.reactivex.rxjava3.core.Single
import org.journey.android.character.data.dto.RequestChangeCharacterDTO
import org.journey.android.character.data.dto.ResponseChangeCharacterDTO
import org.journey.android.character.data.dto.ResponseGetCharacterDTO

interface CharacterDataSource {
    fun changeCharacter( requestChangeCharacterDTO: RequestChangeCharacterDTO) : Single<ResponseChangeCharacterDTO>
    fun getCharacter(client : String) : Single<ResponseGetCharacterDTO>
}