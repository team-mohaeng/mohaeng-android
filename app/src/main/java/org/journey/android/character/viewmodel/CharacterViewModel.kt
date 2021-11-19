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
import org.journey.android.character.data.entity.CharacterInfoEntity
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

    private val _currentUserCharacter = MutableLiveData<CurrentCharacterDTO>()
    val currentUserCharacter: LiveData<CurrentCharacterDTO>
        get() = _currentUserCharacter

    private val _currentUserSkin = MutableLiveData<CurrentSkinDTO>()
    val currentUserSkin: LiveData<CurrentSkinDTO>
        get() = _currentUserSkin

    private val _getCharacter = MutableLiveData<CardDTO>()
    val getCharacter : LiveData<CardDTO>
        get() = _getCharacter

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

    fun loadCharacterList() {
        addDisposable(
            characterRepository.getCharacter(client = "aos")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                      _characterInfo.postValue(it)
                },{
                    it.printStackTrace()
                })
        )
    }

    init {
//        fetchCharacterList()
        fetchCharacterOptionList()
    }

    private fun fetchCharacterList() {
        val characterList = listOf(
            MohaengCharacterEntity(
                R.drawable.ic_char1
            ),
            MohaengCharacterEntity(
                R.drawable.ic_char2
            ),
            MohaengCharacterEntity(
                R.drawable.ic_char3
            ),
            MohaengCharacterEntity(
                R.drawable.ic_char4
            ),
            MohaengCharacterEntity(
                R.drawable.ic_char5
            ),
            MohaengCharacterEntity(
                R.drawable.ic_char4
            ),
            MohaengCharacterEntity(
                R.drawable.ic_char5
            )
        )
        _characterList.value = characterList
    }

    private fun fetchCharacterOptionList() {
        var optionList = listOf(
            MohaengCharacterOptionEntity(
                R.drawable.ic_option_1
            ),
            MohaengCharacterOptionEntity(
                R.drawable.ic_option_2
            ),
            MohaengCharacterOptionEntity(
                R.drawable.ic_none_char
            ),
            MohaengCharacterOptionEntity(
                R.drawable.ic_option_4
            ),
            MohaengCharacterOptionEntity(
                R.drawable.ic_option_2
            ),
            MohaengCharacterOptionEntity(
                R.drawable.ic_option_3
            ),
            MohaengCharacterOptionEntity(
                R.drawable.ic_option_4
            ),
            MohaengCharacterOptionEntity(
                R.drawable.ic_option_2
            ),
            MohaengCharacterOptionEntity(
                R.drawable.ic_option_3
            ),
            MohaengCharacterOptionEntity(
                R.drawable.ic_option_4
            ),
            MohaengCharacterOptionEntity(
                R.drawable.ic_option_2
            ),
            MohaengCharacterOptionEntity(
                R.drawable.ic_option_3
            )
        )
        _optionList.value = optionList
    }
}