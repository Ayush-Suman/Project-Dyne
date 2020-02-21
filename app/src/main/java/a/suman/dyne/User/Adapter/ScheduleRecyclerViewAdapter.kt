package a.suman.dyne.User.Adapter

import a.suman.dyne.R
import a.suman.dyne.User.Fragments.Schedule.Data.ScheduleDataClass
import a.suman.dyne.User.Fragments.Schedule.Data.ScheduleRepository
import a.suman.dyne.User.Views.Fragments.Schedule.schedule
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_schedule.*

class ScheduleRecyclerViewAdapter(var onClick: onClick):RecyclerView.Adapter<ScheduleViewHolder>() {

    var scheduleData= emptyList<ScheduleDataClass>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleViewHolder {
        return ScheduleViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.schedule_recycler, parent, false))
    }

    override fun getItemCount(): Int {
    return scheduleData.size
    }

    override fun onBindViewHolder(holder: ScheduleViewHolder, position: Int) {
        holder.textView13.text=scheduleData[position].Name
        holder.textView14.text=scheduleData[position].Reps
        holder.textView15.text=scheduleData[position].Sets
        holder.textView16.text=scheduleData[position].VideoTitle
        holder.textView16.setOnClickListener {
            onClick.onClickLink(scheduleData[position].VideoLink)
        }
    }
    fun setData(scheduleData:List<ScheduleDataClass>){
        this.scheduleData=scheduleData
        notifyDataSetChanged()
    }


}
class ScheduleViewHolder(view: View): RecyclerView.ViewHolder(view){
    val textView13=view.findViewById<TextView>(R.id.textView13)
    val textView15=view.findViewById<TextView>(R.id.textView14)
    val textView14=view.findViewById<TextView>(R.id.textView14)
    val textView16=view.findViewById<TextView>(R.id.textView16)
    val recyclerViewTip=view.findViewById<RecyclerView>(R.id.recyclerViewTip)
}

interface onClick{
    fun onClickLink(url:String)
}