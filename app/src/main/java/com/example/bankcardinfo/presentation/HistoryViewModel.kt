package com.example.bankcardinfo.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bankcardinfo.domain.api.HistoryInteractor
import com.example.bankcardinfo.domain.model.CardInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HistoryViewModel(val historyInteractor: HistoryInteractor): ViewModel() {
val _history = MutableLiveData<List<CardInfo>>()
val history:LiveData<List<CardInfo>> = _history
    init {
        getHistory()
    }
    fun getHistory(){
        viewModelScope.launch {
            historyInteractor.getHistory().collect{
                _history.postValue(it)
            }
        }
    }
}