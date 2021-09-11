package org.journey.android.character

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import org.journey.android.R
import org.journey.android.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(): BaseViewModel() {
    private val _characterList = MutableLiveData<List<MohaengCharacterEntity>>()
    val characterList : LiveData<List<MohaengCharacterEntity>>
        get() = _characterList

    private val _optionList = MutableLiveData<List<MohaengCharacterOptionEntity>>()
    val optionList : LiveData<List<MohaengCharacterOptionEntity>>
        get() = _optionList

    init {
        fetchCharacterList()
    }

    private fun fetchCharacterList(){
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
}