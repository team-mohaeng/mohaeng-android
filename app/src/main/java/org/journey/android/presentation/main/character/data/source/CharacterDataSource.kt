package org.journey.android.presentation.main.character.data.source

import io.reactivex.rxjava3.core.Single
import org.journey.android.presentation.main.character.data.dto.request.RequestChangeCharacterDTO
import org.journey.android.presentation.main.character.data.dto.response.ResponseChangeCharacterDTO
import org.journey.android.presentation.main.character.data.dto.response.ResponseGetCharacterDTO

interface CharacterDataSource {
    fun changeCharacter( requestChangeCharacterDTO: RequestChangeCharacterDTO) : Single<ResponseChangeCharacterDTO>
    fun getCharacter(client : String) : Single<ResponseGetCharacterDTO>
}