package com.example.lesson14

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainVM : ViewModel() {

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://uselessfacts.jsph.pl/api/v2/facts/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val api = retrofit.create(RandomFactApi::class.java)

    private val _data = MutableLiveData<List<RandomFact>>()
    val data: LiveData<List<RandomFact>> get() = _data

    fun getFacts(count: Int){
        viewModelScope.launch {
            val toReturn = mutableListOf<RandomFact>()
            for (i in 1..count) {
                toReturn.add(api.getFacts())
            }
            _data.value = toReturn
        }
    }
}
