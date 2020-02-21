package a.suman.dyne.User.Views.Fragments.Fragment_home

import a.suman.dyne.User.DatabaseDyne
import a.suman.dyne.User.Fragments.Fragment_home.Data.HomeDataClass
import a.suman.dyne.User.Fragments.Fragment_home.Data.HomeRepository
import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.os.AsyncTask
import android.util.Log.d
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class h_viewmodel(application: Application): AndroidViewModel(application){

    val homeRepository=HomeRepository(application)
    val Gym:LiveData<List<HomeDataClass>> =MutableLiveData()
    init {
        refresh()
        d("ViewModel", "Viewmodelcreated")
    }



    fun refresh(){
        homeRepository.fetchGymData()
    }

    fun getDataforView(){
        homeRepository.getGymData().subscribe ({
        (Gym as MutableLiveData).postValue(it)},{
            d("Error", "$it")
        })
    }
}