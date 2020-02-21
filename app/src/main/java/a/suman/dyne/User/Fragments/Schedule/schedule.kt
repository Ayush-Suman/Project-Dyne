package a.suman.dyne.User.Views.Fragments.Schedule

import a.suman.dyne.R
import a.suman.dyne.User.Adapter.ScheduleRecyclerViewAdapter
import a.suman.dyne.User.Adapter.onClick
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_schedule.*
import kotlinx.android.synthetic.main.fragment_schedule.spinner


class schedule : Fragment(), onClick, AdapterView.OnItemSelectedListener {

    var day="Monday"
    val fragment:Fragment=this

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_schedule, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerViewSchedule.layoutManager=LinearLayoutManager(context)
        val scheduleAdapter=ScheduleRecyclerViewAdapter(this)
        recyclerViewSchedule.adapter=scheduleAdapter
        val scViewmodel=ViewModelProviders.of(this).get(sc_viewmodel::class.java)
        scViewmodel.getData(day)
        scViewmodel.scheduleData.observe(viewLifecycleOwner, Observer { scheduleAdapter.setData(it) })
        val arrayAdapter: ArrayAdapter<CharSequence> = ArrayAdapter.createFromResource(context!!, R.array.schedule_spinner_items, R.layout.spinner)
        arrayAdapter.setDropDownViewResource(R.layout.spinner_dropdown)
        spinner.adapter=arrayAdapter
    }

    override fun onClickLink(url:String) {
        startActivity(
            Intent(Intent.ACTION_VIEW, Uri.parse(url))
        )
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        day= arrayListOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")[position]
        var scviewmodel=ViewModelProviders.of(fragment).get(sc_viewmodel::class.java)
        scviewmodel.getData(day)
    }
}
