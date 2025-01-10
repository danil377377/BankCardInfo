package com.example.bankcardinfo.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bankcardinfo.data.network.BinlistResponse
import com.example.bankcardinfo.domain.api.CardInfoInteractor
import com.example.bankcardinfo.domain.api.HistoryInteractor
import com.example.bankcardinfo.domain.model.CardInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CardInfoViewModel(private val interactor: CardInfoInteractor, val historyInteractor: HistoryInteractor) : ViewModel() {

    private var _binInfo = MutableStateFlow<CardInfo?>(null)
    val binInfo: StateFlow<CardInfo?> = _binInfo
    private val _binInput = MutableStateFlow<String>("")
    val binInput: StateFlow<String> = _binInput
    private var _isLoading = MutableStateFlow<Boolean>(false)
    val isLoading: StateFlow<Boolean> = _isLoading
    private var _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun onInputChange(input: String){
        _binInput.value = input
    }
    fun getCardInfo(input: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.value = true
            interactor.getCardInfo(input).collect { pair ->
                withContext(Dispatchers.Main) {
                    when {
                        pair.first != null -> {
                            _binInfo.value = pair.first
                            _error.value = null
                            historyInteractor.addInfoToHistory(pair.first!!.copy(bin = binInput.value.toInt()))
                        }
                        pair.second != null -> _error.value = pair.second
                    }
                }
            }
            _isLoading.value = false
        }
    }
}