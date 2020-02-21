package a.suman.dyne.User.Adapter

import a.suman.dyne.R
import a.suman.dyne.User.Plans
import android.app.Application
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_gym_payment_interface.*

class MembershipRecyclerViewAdapter(val onClickListener: onClickListenerGym) :RecyclerView.Adapter<MembershipViewHolder>(){
    var listplans:List<Plans> = emptyList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):MembershipViewHolder {
        return MembershipViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_memberships, parent, false))
    }
    override fun getItemCount(): Int {
       return listplans.size
    }

    override fun onBindViewHolder(holder: MembershipViewHolder, position:Int){
        holder.textView51.text=listplans[position].plan
        holder.textView52.text=listplans[position].price
        holder.textView53.setOnClickListener { onClickListener.onClick(position) }
    }

    fun setData(listplans:MutableList<Plans>){
        this.listplans=listplans
        notifyDataSetChanged()
    }

}
class MembershipViewHolder(view:View): RecyclerView.ViewHolder(view){
    val textView51:TextView=view.findViewById(R.id.textView51)
    val textView52:TextView=view.findViewById(R.id.textView52)
    val textView53:TextView=view.findViewById(R.id.textView53)
}
interface onClickListenerGym{
    fun onClick(position: Int)
}