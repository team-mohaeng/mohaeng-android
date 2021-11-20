package org.journey.android.character.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import org.journey.android.R
import org.journey.android.base.BaseViewModel
import org.journey.android.character.controller.CharacterController
import org.journey.android.character.data.dto.CardDTO
import org.journey.android.character.data.dto.CurrentCharacterDTO
import org.journey.android.character.data.dto.CurrentSkinDTO
import org.journey.android.character.data.dto.request.RequestChangeCharacterDTO
import org.journey.android.character.data.dto.response.ResponseChangeCharacterDTO
import org.journey.android.character.data.entity.CharacterInfoEntity
import org.journey.android.character.data.entity.CharacterSkinEntity
import org.journey.android.character.data.entity.MohaengCharacterEntity
import org.journey.android.character.data.entity.MohaengCharacterOptionEntity
import org.journey.android.character.data.repository.CharacterRepository
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val characterController: CharacterController,
    private val characterRepository: CharacterRepository
) : BaseViewModel() {
    private val _characterList = MutableLiveData<List<MohaengCharacterEntity>>()
    val characterList: LiveData<List<MohaengCharacterEntity>>
        get() = _characterList

    private val _optionList = MutableLiveData<List<MohaengCharacterOptionEntity>>()
    val optionList: LiveData<List<MohaengCharacterOptionEntity>>
        get() = _optionList

    private val _characterInfo = MutableLiveData<CharacterInfoEntity>()
    val characterInfo: LiveData<CharacterInfoEntity>
        get() = _characterInfo

    private val _characterSkin = MutableLiveData<List<CharacterSkinEntity>>()
    val characterSkin: LiveData<List<CharacterSkinEntity>>
        get() = _characterSkin

    private val _currentUserCharacter = MutableLiveData<CurrentCharacterDTO>()
    val currentUserCharacter: LiveData<CurrentCharacterDTO>
        get() = _currentUserCharacter

    private val _currentUserSkin = MutableLiveData<CurrentSkinDTO>()
    val currentUserSkin: LiveData<CurrentSkinDTO>
        get() = _currentUserSkin

    private val _getCharacter = MutableLiveData<CardDTO>()
    val getCharacter: LiveData<CardDTO>
        get() = _getCharacter

    private val _changeCharacter = MutableLiveData<ResponseChangeCharacterDTO>()
    val changeCharacter: LiveData<ResponseChangeCharacterDTO>
        get() = _changeCharacter

    private val _selectedType = MutableLiveData<Int>()
    val selectedType: LiveData<Int>
        get() = _selectedType

    private val _selectedSkinType = MutableLiveData<Int>()
    val selectedSkinType: LiveData<Int>
        get() = _selectedSkinType

    fun changeSelectedType(type: Int) {
        _selectedType.value = type
    }

    fun changeSelectedSkinType(skin: Int) {
        _selectedSkinType.value = skin
    }

    fun loadUserCurrentSkin() {
        addDisposable(
            characterRepository.getCharacter(client = "aos")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _characterInfo.postValue(it)
                }, {
                    it.printStackTrace()
                })
        )
    }

    fun changeUserCharacter() {
        addDisposable(
            characterRepository.changeCharacter(
                requestChangeCharacterDTO = RequestChangeCharacterDTO(
                    1, 1, 1
                )
            )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _changeCharacter.postValue(it)
                }, {
                    it.printStackTrace()
                })
        )
    }

    init {
        fetchCharacterList()
        fetchSkinList()
    }

    private fun fetchCharacterList() {
        val characterList = listOf(
            MohaengCharacterEntity(
                1,
                R.drawable.stylechactab1
            ),
            MohaengCharacterEntity(
                2,
                R.drawable.stylechactab2
            ),
            MohaengCharacterEntity(
                3,
                R.drawable.stylechactab3
            ),
            MohaengCharacterEntity(
                4,
                R.drawable.stylechactab4
            ),
            MohaengCharacterEntity(
                5,
                R.drawable.stylechactab5
            ),
            MohaengCharacterEntity(
                6,
                R.drawable.stylechactab6
            ),
            MohaengCharacterEntity(
                null,
                R.drawable.stylechactablock7
            ),
            MohaengCharacterEntity(
                null,
                R.drawable.stylechactablock8
            ),
            MohaengCharacterEntity(
                null,
                R.drawable.stylechactablock9
            ),
            MohaengCharacterEntity(
                null,
                R.drawable.stylechactablock10
            ),
            MohaengCharacterEntity(
                null,
                R.drawable.stylechactablock11
            )
        )
        _characterList.value = characterList
    }

    private fun fetchSkinList() {
        val characterSkin = listOf(
            CharacterSkinEntity(
                6,
                R.drawable.pre133
            ),
            CharacterSkinEntity(
                5,
                R.drawable.pre132
            ),
            CharacterSkinEntity(
                4,
                R.drawable.pre131
            ),
            CharacterSkinEntity(
                3,
                R.drawable.pre130
            ),
            CharacterSkinEntity(
                2,
                R.drawable.pre129
            ),
            CharacterSkinEntity(
                1,
                R.drawable.pre128
            ),
            CharacterSkinEntity(
                null,
                R.drawable.nightlock
            ),
            CharacterSkinEntity(
                null,
                R.drawable.spreadlock
            ),
            CharacterSkinEntity(
                null,
                R.drawable.cloudlock
            ),
            CharacterSkinEntity(
                null,
                R.drawable.fieldlock
            ),
            CharacterSkinEntity(
                null,
                R.drawable.figurelock
            )
        )
        _characterSkin . value = characterSkin
    }

}