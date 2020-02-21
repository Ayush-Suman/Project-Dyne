package a.suman.dyne.User.Views.Fragments.Stats

import a.suman.dyne.User.Fragments.Schedule.Data.ScheduleDataClass
import a.suman.dyne.User.Fragments.Schedule.Data.ScheduleRepository
import a.suman.dyne.User.Fragments.Stats.Data.StatsDataClass
import a.suman.dyne.User.Fragments.Stats.Data.StatsRepository
import android.app.Application
import android.util.Log
import android.util.Log.d
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData


class st_viewmodel(application: Application): AndroidViewModel(application){

    val statsRepository= StatsRepository(application)
    var statsData: LiveData<StatsDataClass> = MutableLiveData()
    init {
        d("Viewmodel" ,"Viewmodelcreated")
        refresh()
    }

    fun refresh(){
        statsRepository.fetchStatsData()
    }

    fun getData(){
        statsRepository.getStatsData().subscribe({
            (statsData as MutableLiveData).postValue(it)
        },{ Log.d("ErrorSchedule", "$it") })
    }
}