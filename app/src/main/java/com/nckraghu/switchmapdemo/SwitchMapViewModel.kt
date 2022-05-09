package com.nckraghu.switchmapdemo

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class SwitchMapViewModel(private val repository: Repository): ViewModel() {


    private val selectedLetter: LiveData<String> = repository.getSelectedLetter()

    private val selectedLetter2: LiveData<String> = repository.getSelectedLetter2()

    val nounList: LiveData<String> = CustomTransformations.correctSwitchMap(selectedLetter) {
        repository.getNounsWithSelection(it)
    }

    val nounList2: LiveData<String> = CustomTransformations.incorrectSwitchMap(selectedLetter2) {
        repository.getNounsWithSelection2(it)
    }

    fun setSelectedLetter(letterL: String) {
        repository.setSelectedLetter(letterL)
    }

    fun loadMoreNouns() {
        repository.loadMoreNouns()
    }

    fun setSelectedLetter2(letterL: String) {
        repository.setSelectedLetter2(letterL)
    }

    fun loadMoreNoun2() {
        repository.loadMoreNouns2()
    }

}