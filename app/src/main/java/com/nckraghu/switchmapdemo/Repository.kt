package com.nckraghu.switchmapdemo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.util.*

class Repository() {

    private val userName: MutableLiveData<String> = MutableLiveData()

    private val userNameLength: MutableLiveData<Int> = MutableLiveData()

    fun getUserNameLength(userName: String): LiveData<Int> {
        userNameLength.value = userName.length

        return userNameLength
    }

    fun setUserName(userNameString: String) {
        userName.value = userNameString
    }

    fun getUserName(): LiveData<String> {
        return userName
    }

    private val selectedName: MutableLiveData<String> = MutableLiveData()

    private val nounsWithSelection: MutableLiveData<String> = MutableLiveData()

    fun setSelectedName(s: String) {
        selectedName.value = s
    }

    fun getSelectedName(): LiveData<String> {
        return selectedName
    }

    fun getNamesWithSelection(s: String): LiveData<String> {
        if(s == "A") {
            nounsWithSelection.value = NounList.nounsWithA.joinToString()
        }
        else if(s == "B") {
            nounsWithSelection.value = NounList.nounsWithB.joinToString()
        }
        else {
            nounsWithSelection.value = ""
        }

        return nounsWithSelection
    }

    companion object {

        private var REPOSITORY: Repository? = null

        fun getRepositoryInstance(): Repository {

            if (REPOSITORY == null) {
                synchronized(Repository::class) {
                    if (REPOSITORY == null) {
                        REPOSITORY = Repository()
                    }
                }
            }

            return REPOSITORY!!
        }

    }

}