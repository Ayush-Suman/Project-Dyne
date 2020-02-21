package a.suman.dyne.User.Views.Fragments.Stats

import a.suman.dyne.R
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import kotlinx.android.synthetic.main.fragment_stats.*


class stats : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_stats, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var lineDataSet= LineDataSet(emptyList(), "Weight")
        var weight = mutableListOf<Entry>()
        val viewmodel=ViewModelProviders.of(this).get(st_viewmodel::class.java)
        viewmodel.getData()
        viewmodel.statsData.observe(viewLifecycleOwner, Observer {
            weight = mutableListOf(
                Entry(0f, it.M1.toFloat()),
                Entry(1f, it.M2.toFloat()),
                Entry(2f, it.M3.toFloat()),
                Entry(3f, it.M4.toFloat())
            )
            lineDataSet =LineDataSet(weight, "Weight")
            var data= LineData(lineDataSet)
            linechart.data = data
            linechart.invalidate()
            textView25.text= it.MaintenanceC
            textView22.text =it.C
            textView20.text = it.TrainersC
            textView21.text = it.TrainersN
        })

    }

}
