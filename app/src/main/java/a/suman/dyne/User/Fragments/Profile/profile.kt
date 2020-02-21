package a.suman.dyne.User.Views.Fragments.Profile

import a.suman.dyne.R
import a.suman.dyne.User.Fragments.Profile.Data.ProfileDataClass
import android.os.Bundle
import android.util.Log.d
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.fragment_profile.*


class profile : Fragment(), AdapterView.OnItemSelectedListener {



var gender:String="Male"
    var gNum:Int=0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel=ViewModelProviders.of(this).get(p_viewmodel::class.java)
        viewModel.getData()
        viewModel.profileData.observe(viewLifecycleOwner, Observer { words->words.let { textView27.text=it.Name
            textView26.text=it.Email
            if(it.Mobile=="null"){
                imageView16.visibility=View.GONE
            }else{
                imageView16.visibility=View.VISIBLE
                textView18.text=it.Mobile
            }

            textView32.text=it.Gender
            textView34.text=it.Height
            textView36.text=it.Weight
            editText3.setText(it.Weight.replace("kg", ""))
            editText7.setText(it.Height.replace("cm",""))
            gender=it.Gender
            when(gender){
                "Male" -> gNum=0
                "Female" -> gNum=1
                "Others" -> gNum=2
            }
            setSpinnerItem(gNum)
        } })

        val arrayAdapter= ArrayAdapter.createFromResource(context!!, R.array.gender_spinner, R.layout.gender_spinner)
        arrayAdapter.setDropDownViewResource(R.layout.gender_spinner_dropdown)
        spinner2.adapter=arrayAdapter
        spinner2.setSelection(gNum)
        spinner2.onItemSelectedListener=this
        imageView17.setOnClickListener{
            cardviewedit.visibility=View.VISIBLE
        }
        textView58.setOnClickListener {
            if(!(editText3.text.isBlank())&&!(editText7.text.isBlank())){
            var profileDataClass=ProfileDataClass(
                textView26.text.toString(),
                textView27.text.toString(),
                textView18.text.toString(),
                gender,
                editText3.text.toString()+"kg",
                editText7.text.toString()+"cm")
                d("email","${textView26.text.toString()}")
            viewModel.update(profileDataClass)
            cardviewedit.visibility=View.GONE}
            else{
                Toast.makeText(context!!, "Enter details", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
       gender=arrayListOf("Male", "Female", "Others")[position]
        gNum=position
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        gender="Male"
        gNum=0
    }
    fun setSpinnerItem(position: Int){
        spinner2.setSelection(position)
    }
}
