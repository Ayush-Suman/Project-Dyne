package a.suman.dyne.User.Fragments.Diet

import a.suman.dyne.User.Fragments.Diet.Data.DietDataClass
import a.suman.dyne.User.Fragments.Diet.Data.DietList
import a.suman.dyne.User.Fragments.Fragment_home.Data.DietRepository
import android.app.Application
import android.util.Log
import android.util.Log.d
import androidx.lifecycle.*
import io.reactivex.schedulers.Schedulers

class d_viewmodel(application: Application): AndroidViewModel(application) {

    val dietRepository = DietRepository(application)
    val Diet: LiveData<List<DietDataClass>> = MutableLiveData()

    init {
        refresh()
        d("ViewModel", "Viewmodelcreated")
    }


    fun refresh() {
        dietRepository.fetchDietData()
    }

    fun getDataforView(day:String) {
        dietRepository.getDietData(day).subscribe({
            (Diet as MutableLiveData).postValue(it)
        }
            , { d("ErrorDiet", "$it") })
    }
}
