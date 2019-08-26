package com.efhems.socialvue.viewmodel

import android.app.Application
import androidx.lifecycle.*

class AuthViewModel(application: Application): AndroidViewModel(application){

    private val _professional = MutableLiveData<Boolean>()
    val professional: LiveData<Boolean> get() = _professional


    init {
        _professional.value = false
    }

    fun isProfessional(prof: Boolean) {
        _professional.value = prof
    }

    /**
     * Factory for constructing AuthViewModel with parameter
     */
    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return AuthViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }

}