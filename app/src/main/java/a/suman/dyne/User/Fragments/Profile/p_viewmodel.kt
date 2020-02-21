package a.suman.dyne.User.Views.Fragments.Profile

import a.suman.dyne.User.Fragments.Profile.Data.ProfileDataClass
import a.suman.dyne.User.Fragments.Profile.Data.ProfileRepository
import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class p_viewmodel(application: Application) : AndroidViewModel(application){
    private val profileRepository =ProfileRepository(application)
    var profileData:LiveData<ProfileDataClass> =MutableLiveData()

    init{
        Log.d("ViewModel", "Viewmodelcreated")
        refresh()
    }

    fun refresh(){
        profileRepository.fetchProfileData()
    }

    fun update(profileDataClass: ProfileDataClass){
        profileRepository.updateData(profileDataClass)
    }


    fun getData(){
        profileRepository.getProfileData().subscribe ({
            (profileData as MutableLiveData).postValue(it)},{ Log.d("ErrorProfile", "$it") })
        }

    }



