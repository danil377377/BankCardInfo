package com.example.bankcardinfo.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bankcardinfo.domain.api.HistoryInteractor
import com.example.bankcardinfo.domain.model.CardInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HistoryViewModel(val historyInteractor: HistoryInteractor): ViewModel() {
val _history = MutableStateFlow<List<CardInfo>>(emptyList())
val history: StateFlow<List<CardInfo>> = _history
    init {
        getHistory()
    }
    fun getHistory(){
        viewModelScope.launch {
            historyInteractor.getHistory().collect{
                _history.value = it
            }
        }
    }
}