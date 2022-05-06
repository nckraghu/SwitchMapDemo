package com.nckraghu.switchmapdemo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class SwitchMapViewModel(private val repository: Repository): ViewModel() {

//    private val userName: MutableLiveData<String> = MutableLiveData()

    private val userName: LiveData<String> = repository.getUserName()

    val nameLength: LiveData<Int> = Transformations.switchMap(userName) {
                                                repository.getUserNameLength(it)
    }

    val customNameLength: LiveData<Int> = CustomTransformations.customSwitchMap(userName) {
        repository.getUserNameLength(it)
    }

    val wrongNameLength: LiveData<Int> = CustomTransformations.wrongSwitchMap(userName) {
        repository.getUserNameLength(it)
    }

    fun setUserName(string: String) {
//        userName.value = string
        repository.setUserName(string)
    }

}