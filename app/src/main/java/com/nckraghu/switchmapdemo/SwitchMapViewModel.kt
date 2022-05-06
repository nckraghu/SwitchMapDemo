package com.nckraghu.switchmapdemo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class SwitchMapViewModel(private val repository: Repository): ViewModel() {

    private val selectedName: LiveData<String> = repository.getSelectedName()

    val nameList: LiveData<String> = Transformations.switchMap(selectedName) {
        repository.getNamesWithSelection(it)
    }

    fun setSelectedName(s: String) {
        repository.setSelectedName(s)
    }

    fun loadMoreNames() {
        repository.loadMoreNames()
    }

}