package com.nckraghu.switchmapdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.nckraghu.switchmapdemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var switchMapViewModel: SwitchMapViewModel

    private lateinit var _binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)

        val view: View = _binding.root
        setContentView(view)

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


}