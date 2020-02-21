package a.suman.dyne.User.Fragments.Diet


import a.suman.dyne.R
import a.suman.dyne.User.Adapter.DietRecyclerViewAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_diet.*


class diet : Fragment(), AdapterView.OnItemSelectedListener {

    var day:String="Monday"


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_diet, container, false)
    }
    val fragment:Fragment=this

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dietRecyclerViewAdapter= DietRecyclerViewAdapter()
        recyclerViewDiet.adapter=dietRecyclerViewAdapter
        recyclerViewDiet.layoutManager=LinearLayoutManager(context)
        var d_viewmodel=ViewModelProviders.of(this).get(d_viewmodel::class.java)
        d_viewmodel.getDataforView(day)
        spinner.onItemSelectedListener=this


        d_viewmodel.Diet.observe(viewLifecycleOwner, Observer{words->words.let{
            dietRecyclerViewAdapter.setData(it)
        }})
        val arrayAdapter:ArrayAdapter<CharSequence> = ArrayAdapter.createFromResource(context!!, R.array.schedule_spinner_items, R.layout.spinner)
        arrayAdapter.setDropDownViewResource(R.layout.spinner_dropdown)
        spinner.adapter=arrayAdapter
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        day= arrayListOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")[position]
        var d_viewmodel=ViewModelProviders.of(fragment).get(d_viewmodel::class.java)
        d_viewmodel.getDataforView(day)
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }

}
