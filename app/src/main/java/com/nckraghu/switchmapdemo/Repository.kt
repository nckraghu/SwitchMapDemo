package com.nckraghu.switchmapdemo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

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