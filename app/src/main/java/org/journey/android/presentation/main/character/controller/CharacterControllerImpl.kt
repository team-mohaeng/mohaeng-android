package org.journey.android.presentation.main.character.controller

import io.reactivex.rxjava3.core.Single
import org.journey.android.presentation.main.character.data.dto.request.RequestChangeCharacterDTO
import org.journey.android.presentation.main.character.data.dto.response.ResponseChangeCharacterDTO
import org.journey.android.presentation.main.character.data.dto.response.ResponseGetCharacterDTO
import org.journey.android.data.network.RetrofitInterface
import org.journey.android.data.network.qualifier.AuthRetrofitService
import javax.inject.Inject

class CharacterControllerImpl @Inject constructor(
    @AuthRetrofitService private val retrofitInterface: RetrofitInterface
) : CharacterController {
    override fun changeCharacter(requestChangeCharacterDTO: RequestChangeCharacterDTO): Single<ResponseChangeCharacterDTO> =
        retrofitInterface.changeCharacter(requestChangeCharacterDTO)

    override fun getCharacter(client : String): Single<ResponseGetCharacterDTO> =
        retrofitInterface.getCharacter(client)
}