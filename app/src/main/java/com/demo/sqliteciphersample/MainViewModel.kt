package com.demo.sqliteciphersample

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(private val dao: NoteDao) : ViewModel() {

    private val _noteList = MutableLiveData<List<NoteDto>>()
    val noteList: LiveData<List<NoteDto>> get() = _noteList

    init {
//        insertInitialDataIfEmpty()
        updateList()
    }

    private fun insertInitialDataIfEmpty() {
        viewModelScope.launch(Dispatchers.IO) {
            val list = dao.getAll()
            if (list.size > 3) return@launch
            dao.insert(NoteDto(0, "Hi1", Date().time))
            dao.insert(NoteDto(1, "Hi2", Date().time))
            dao.insert(NoteDto(2, "Hi3", Date().time))
            val newList = dao.getAll()
            _noteList.postValue(newList)
        }
    }

    private fun updateList() {
        viewModelScope.launch(Dispatchers.IO) {
            val list = dao.getAll()
            _noteList.postValue(list)
        }
    }
}