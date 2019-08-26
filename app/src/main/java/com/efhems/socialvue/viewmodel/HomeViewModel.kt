package com.efhems.socialvue.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.efhems.socialvue.model.Model
import com.efhems.socialvue.utils.extractJson

class HomeViewModel(application: Application):  AndroidViewModel(application){



    private val _listCat = MutableLiveData<List<Model>>()
    val listCat:LiveData<List<Model>> get() = _listCat

    init {
        _listCat.value = extractJson(application.applicationContext)
    }



    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return HomeViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}