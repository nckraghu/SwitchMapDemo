package com.nckraghu.switchmapdemo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class Repository() {

    private val selectedLetter: MutableLiveData<String> = MutableLiveData()

    private val selectedLetter2: MutableLiveData<String> = MutableLiveData()

    private val nounsWithSelection: MutableLiveData<String> = MutableLiveData()

    private val nounsWithSelection2: MutableLiveData<String> = MutableLiveData()

    fun setSelectedLetter(s: String) {
        selectedLetter.value = s
    }

    fun getSelectedLetter(): LiveData<String> {
        return selectedLetter
    }

    fun getNounsWithSelection(letterL: String): LiveData<String> {
        if(letterL == "A") {
            nounsWithSelection.value = NounList.nounsWithA.joinToString()
        }
        else if(letterL == "B") {
            nounsWithSelection.value = NounList.nounsWithB.joinToString()
        }
        else {
            nounsWithSelection.value = ""
        }

        return nounsWithSelection
    }

    fun loadMoreNouns() {
        if (selectedLetter.value == "A") {
            nounsWithSelection.value = NounList.nounsWithA.joinToString() + ", " + NounList.moreNounsWithA.joinToString()
        }
        else if(selectedLetter.value == "B"){
            nounsWithSelection.value = NounList.nounsWithB.joinToString() + ", " + NounList.moreNounsWithB.joinToString()
        }
        else {
            nounsWithSelection.value = ""
        }
    }

    fun setSelectedLetter2(s: String) {
        selectedLetter2.value = s
    }

    fun getSelectedLetter2(): LiveData<String> {
        return selectedLetter2
    }

    fun loadMoreNouns2() {
        if (selectedLetter2.value == "A") {
            nounsWithSelection2.value = NounList.nounsWithA.joinToString() + ", " + NounList.moreNounsWithA.joinToString()
        }
        else if(selectedLetter2.value == "B"){
            nounsWithSelection2.value = NounList.nounsWithB.joinToString() + ", " + NounList.moreNounsWithB.joinToString()
        }
        else {
            nounsWithSelection2.value = ""
        }
    }

    fun getNounsWithSelection2(letterL: String): LiveData<String> {

        if(letterL == "A") {
            nounsWithSelection2.value = NounList.nounsWithA.joinToString()
        }
        else if(letterL == "B") {
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