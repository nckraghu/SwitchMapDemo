package com.nckraghu.switchmapdemo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class SwitchMapViewModel(private val repository: Repository): ViewModel() {


    private val selectedName: LiveData<String> = repository.getSelectedName()

    private val selectedName2: LiveData<String> = repository.getSelectedName2()

    val nameList: LiveData<String> = CustomTransformations.customSwitchMap(selectedName) {
        repository.getNamesWithSelection(it)
    }

    val nameList2: LiveData<String> = CustomTransformations.wrongSwitchMap(selectedName2) {
        repository.getNamesWithSelection2(it)
    }

    fun setSelectedName(s: String) {
        repository.setSelectedName(s)
    }

    fun loadMoreNames() {
        repository.loadMoreNames()
    }

    fun setSelectedName2(s: String) {
        repository.setSelectedName2(s)
    }

    fun loadMoreNames2() {
        repository.loadMoreNames2()
    }

}