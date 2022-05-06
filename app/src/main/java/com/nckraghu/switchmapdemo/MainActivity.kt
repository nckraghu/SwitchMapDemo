package com.nckraghu.switchmapdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.nckraghu.switchmapdemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener  {

    private lateinit var switchMapViewModel: SwitchMapViewModel

    private lateinit var _binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)

        val view: View = _binding.root
        setContentView(view)

        ArrayAdapter.createFromResource(
            this,
            R.array.names_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            _binding.nameSpinner.adapter = adapter
        }

        _binding.nameSpinner.onItemSelectedListener = this

        _binding.loadMoreBtn.setOnClickListener {
            switchMapViewModel.loadMoreNames()
        }

        switchMapViewModel = SwitchMapViewModel(Repository.getRepositoryInstance())

        switchMapViewModel.nameList.observe(this) {
            _binding.nameList.text = it
        }

    }

    override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
        // An item was selected. You can retrieve the selected item using
        if (pos == 0) {
            //A is selected
            switchMapViewModel.setSelectedName("A")
        }
        else {
            switchMapViewModel.setSelectedName("B")
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>) {
        switchMapViewModel.setSelectedName("")
    }


}