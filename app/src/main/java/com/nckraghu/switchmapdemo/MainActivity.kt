package com.nckraghu.switchmapdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.nckraghu.switchmapdemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var switchMapViewModel: SwitchMapViewModel

    private lateinit var _binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)

        val view: View = _binding.root
        setContentView(view)

        initializeSpinnerAdapter()

        initializeSpinnerAdapter2()

    }

    private fun initializeSpinnerAdapter() {
        ArrayAdapter.createFromResource(
            this,
            R.array.nouns_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            _binding.nounSpinner.adapter = adapter
        }

        _binding.nounSpinner.onItemSelectedListener  = object: AdapterView.OnItemSelectedListener {

            override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
                // An item was selected. You can retrieve the selected item using
                if (pos == 0) {
                    //A is selected
                    switchMapViewModel.setSelectedLetter("A")
                }
                else {
                    switchMapViewModel.setSelectedLetter("B")
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                switchMapViewModel.setSelectedLetter("")
            }

        }

        _binding.loadMoreBtn.setOnClickListener {
            switchMapViewModel.loadMoreNouns()
        }

        switchMapViewModel = SwitchMapViewModel(Repository.getRepositoryInstance())

        switchMapViewModel.nounList.observe(this) {
            _binding.nounListTextView.text = it
        }

    }

    private fun initializeSpinnerAdapter2() {
        ArrayAdapter.createFromResource(
            this,
            R.array.nouns_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            _binding.nounSpinner2.adapter = adapter
        }

        _binding.nounSpinner2.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (position == 0) {
                    switchMapViewModel.setSelectedLetter2("A")
                }
                else {
                    switchMapViewModel.setSelectedLetter2("B")
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                switchMapViewModel.setSelectedLetter2("")
            }

        }

        _binding.loadMoreBtn2.setOnClickListener {
            switchMapViewModel.loadMoreNoun2()
        }

        switchMapViewModel = SwitchMapViewModel(Repository.getRepositoryInstance())

        switchMapViewModel.nounList2.observe(this) {
            _binding.nounListTextView2.text = it
        }

    }

}