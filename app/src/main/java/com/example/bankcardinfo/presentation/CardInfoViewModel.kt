package com.example.bankcardinfo.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bankcardinfo.data.network.BinlistResponse
import com.example.bankcardinfo.domain.api.CardInfoInteractor
import com.example.bankcardinfo.domain.model.CardInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CardInfoViewModel(private val interactor: CardInfoInteractor) : ViewModel() {
    private var _binInfo = MutableLiveData<CardInfo>()
    val binInfo: LiveData<CardInfo> = _binInfo
    private var _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean> = _isLoading
    private var _error = MutableLiveData<String?>(null)
    val error: LiveData<String?> = _error

    fun getCardInfo(input: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.postValue(true)
            interactor.getCardInfo(input).collect { pair ->
                withContext(Dispatchers.Main) {
                    when {
                        pair.first != null -> _binInfo.postValue(pair.first)
                        pair.second != null -> _error.postValue(pair.second)
                    }
                }
            }
            _isLoading.postValue(false)
        }
    }
}