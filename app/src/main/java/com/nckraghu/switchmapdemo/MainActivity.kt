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

        switchMapViewModel.nameLength.observe(this) {
            _binding.nameLengthTextView.text = "Name is of length ${it.toString()}"
        }

        switchMapViewModel.customNameLength.observe(this) {
            _binding.customNameLengthTextView.text = "Custom name is of length ${it.toString()}"
        }

        switchMapViewModel.wrongNameLength.observe(this) {
            _binding.wrongNameLengthTextView.text = "Wrong name is of length ${it.toString()}"
        }


        switchMapViewModel.wrongNameLength.observe(this) {

        }

        switchMapViewModel.nameList.observe(this) {
            _binding.nameList.text = it
        }

        _binding.editTextTextPersonName.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//                TODO("Not yet implemented")
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//                TODO("Not yet implemented")
            }

            override fun afterTextChanged(s: Editable?) {
                val userName = _binding.editTextTextPersonName.text.toString()
                switchMapViewModel.setUserName(userName)
            }

        })
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