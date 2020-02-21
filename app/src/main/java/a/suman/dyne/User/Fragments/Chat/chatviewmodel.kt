package a.suman.dyne.User.Fragments.Chat

import a.suman.dyne.User.Fragments.Chat.Data.chatdataclass
import a.suman.dyne.User.Fragments.Chat.Data.chatrepository
import a.suman.dyne.User.Fragments.Diet.Data.DietDataClass
import a.suman.dyne.User.Fragments.Fragment_home.Data.DietRepository
import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class chatviewmodel(application: Application): AndroidViewModel(application) {

    val chatrepository = chatrepository(application)
    val chats: LiveData<List<chatdataclass>> = MutableLiveData()

    init {
        refresh()
        Log.d("ViewModel", "Viewmodelcreated")
    }


    fun refresh() {
        chatrepository.fetchData()
    }

    fun getDataforView() {
        chatrepository.getData().subscribe({
            (chats as MutableLiveData).postValue(it)
        }
            , { Log.d("ErrorDiet", "$it") })
    }
}