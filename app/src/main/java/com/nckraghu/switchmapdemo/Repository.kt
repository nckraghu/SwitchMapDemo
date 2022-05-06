package com.nckraghu.switchmapdemo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.util.*

class Repository() {

    private val selectedName: MutableLiveData<String> = MutableLiveData()

    private val selectedName2: MutableLiveData<String> = MutableLiveData()

    private val nounsWithSelection: MutableLiveData<String> = MutableLiveData()

    private val nounsWithSelection2: MutableLiveData<String> = MutableLiveData()

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

    fun loadMoreNames() {
        if (selectedName.value == "A") {
            nounsWithSelection.value = NounList.nounsWithA.joinToString() + ", " + NounList.moreNounsWithA.joinToString()
        }
        else if(selectedName.value == "B"){
            nounsWithSelection.value = NounList.nounsWithB.joinToString() + ", " + NounList.moreNounsWithB.joinToString()
        }
        else {
            nounsWithSelection.value = ""
        }
    }

    fun setSelectedName2(s: String) {
        selectedName2.value = s
    }

    fun getSelectedName2(): LiveData<String> {
        return selectedName2
    }

    fun loadMoreNames2() {
        if (selectedName2.value == "A") {
            nounsWithSelection2.value = NounList.nounsWithA.joinToString() + ", " + NounList.moreNounsWithA.joinToString()
        }
        else if(selectedName2.value == "B"){
            nounsWithSelection2.value = NounList.nounsWithB.joinToString() + ", " + NounList.moreNounsWithB.joinToString()
        }
        else {
            nounsWithSelection2.value = ""
        }
    }

    fun getNamesWithSelection2(s: String): LiveData<String> {

        if(s == "A") {
            nounsWithSelection2.value = NounList.nounsWithA.joinToString()
        }
        else if(s == "B") {
            nounsWithSelection2.value = NounList.nounsWithB.joinToString()
        }
        else {
            nounsWithSelection2.value = ""
        }

        return nounsWithSelection2
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