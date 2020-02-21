package a.suman.dyne.User.Views.Fragments.Schedule

import a.suman.dyne.User.Fragments.Schedule.Data.ScheduleDataClass
import a.suman.dyne.User.Fragments.Schedule.Data.ScheduleRepository
import android.app.Application
import android.util.Log.d
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class sc_viewmodel(application: Application):AndroidViewModel(application){

    val scheduleRepository=ScheduleRepository(application)
    var scheduleData:LiveData<List<ScheduleDataClass>> = MutableLiveData()
   init {
       d("ViewModel", "Viewmodelcreated")
        refresh()
    }

    fun refresh(){
        scheduleRepository.fetchScheduleData()
    }

    fun getData(day:String){

        scheduleRepository.getScheduleData(day).subscribe({
            d("data schedule", "$it")
            (scheduleData as MutableLiveData).postValue(it)
        },{d("ErrorSchedule", "$it")})
    }
}