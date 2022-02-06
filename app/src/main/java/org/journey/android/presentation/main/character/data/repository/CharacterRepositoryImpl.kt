package org.journey.android.presentation.main.character.data.repository

import io.reactivex.rxjava3.core.Single
import org.journey.android.presentation.main.character.data.dto.request.RequestChangeCharacterDTO
import org.journey.android.presentation.main.character.data.dto.response.ResponseChangeCharacterDTO
import org.journey.android.domain.entity.CharacterInfoEntity
import org.journey.android.presentation.main.character.data.source.CharacterDataSource
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val characterDataSource: CharacterDataSource
) : CharacterRepository{
    override fun changeCharacter(requestChangeCharacterDTO: RequestChangeCharacterDTO): Single<ResponseChangeCharacterDTO> =
        characterDataSource.changeCharacter(requestChangeCharacterDTO)

    override fun getCharacter(client: String): Single<CharacterInfoEntity> =
        characterDataSource.getCharacter(client).map { response ->
           response.convertToCharacterEntity()
        }
}