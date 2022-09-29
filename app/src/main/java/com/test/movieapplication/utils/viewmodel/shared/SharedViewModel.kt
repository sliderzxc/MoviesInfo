package com.test.movieapplication.utils.viewmodel.shared

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.movieapplication.network.model.Result

class SharedViewModel : ViewModel(){

    private val _result = MutableLiveData<Result>()
    var result: LiveData<Result> = _result

    fun changeResult(result: Result) {
        _result.value = result
    }

}