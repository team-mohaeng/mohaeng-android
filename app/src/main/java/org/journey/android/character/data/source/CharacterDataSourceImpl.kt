package org.journey.android.character.data.source

import io.reactivex.rxjava3.core.Single
import org.journey.android.character.data.dto.request.RequestChangeCharacterDTO
import org.journey.android.character.data.dto.response.ResponseChangeCharacterDTO
import org.journey.android.character.data.dto.response.ResponseGetCharacterDTO
import org.journey.android.network.RetrofitInterface
import javax.inject.Inject

class CharacterDataSourceImpl @Inject constructor(
    private val retrofitInterface: RetrofitInterface
)  : CharacterDataSource {
    override fun changeCharacter(requestChangeCharacterDTO: RequestChangeCharacterDTO): Single<ResponseChangeCharacterDTO> =
        retrofitInterface.changeCharacter(requestChangeCharacterDTO)

    override fun getCharacter(client: String): Single<ResponseGetCharacterDTO> =
        retrofitInterface.getCharacter(client)
}