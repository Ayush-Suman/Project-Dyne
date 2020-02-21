package a.suman.dyne.User.Views

import a.suman.dyne.User.AdvisoryBoard
import a.suman.dyne.R
import a.suman.dyne.User.AboutUs
import a.suman.dyne.User.DatabaseDyne
import a.suman.dyne.User.Fragments.Chat.chatlist
import a.suman.dyne.User.Fragments.Diet.diet
import a.suman.dyne.User.Views.Fragments.Fragment_home.fragment_home
import a.suman.dyne.User.Views.Fragments.Profile.profile
import a.suman.dyne.User.Views.Fragments.Schedule.schedule
import a.suman.dyne.User.Views.Fragments.Stats.stats
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.Fade
import android.util.Log.d
import android.view.View
import androidx.appcompat.widget.ActionBarContainer
import androidx.fragment.app.FragmentTransaction
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.side_nav_header.*

class Home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        window.decorView.systemUiVisibility=
            View.SYSTEM_UI_FLAG_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        val firebaseAuth=FirebaseAuth.getInstance()
        val firebaseFirebaseAuth=FirebaseFirestore.getInstance()
        firebaseFirebaseAuth.collection("Users").document(firebaseAuth.currentUser!!.uid).get().addOnCompleteListener {
            if(it.isSuccessful){
                textView44.text=it.result!!["name"].toString()
            }
        }
        var homeFragment= "gyms"

        sidenav.setNavigationItemSelectedListener {
            d("item",it.itemId.toString())
            imageView6.setImageDrawable(getDrawable(R.drawable.schedule))
            imageView7.setImageDrawable(getDrawable(R.drawable.diet))
            imageView8.setImageDrawable(getDrawable(R.drawable.profile))
            imageView9.setImageDrawable(getDrawable(R.drawable.stats))
            when (it.itemId){
            R.id.gym->{homeFragment="gyms"
                val fragmentTransation:FragmentTransaction= supportFragmentManager.beginTransaction()
                fragmentTransation.add(R.id.fragment, fragment_home())
                fragmentTransation.disallowAddToBackStack()
                fragmentTransation.commit()
                drawer.closeDrawers()}
                R.id.mem->{//homeFragment="mem"
                    drawer.closeDrawers()}
                R.id.sups->{//homeFragment="sups"
                    drawer.closeDrawers()}
                R.id.chat->{homeFragment="chat"
                    val fragmentTransation:FragmentTransaction= supportFragmentManager.beginTransaction()
                    fragmentTransation.add(R.id.fragment, chatlist())
                    fragmentTransation.disallowAddToBackStack()
                    fragmentTransation.commit()
                drawer.closeDrawers()}
                R.id.adv->{startActivity(Intent(this@Home, AdvisoryBoard::class.java))
                    drawer.closeDrawers()}
                R.id.abu->{startActivity(Intent(this@Home, AboutUs::class.java))
                    drawer.closeDrawers()}
                R.id.logout->{firebaseAuth.signOut()
                    Thread{
                        DatabaseDyne.getInstance(this@Home.applicationContext).getDietDMethods().delete()
                        DatabaseDyne.getInstance(this@Home.applicationContext).getStatsMethods().delete()
                        DatabaseDyne.getInstance(this@Home.applicationContext).getScheduleMethods().delete()
                        DatabaseDyne.getInstance(this@Home.applicationContext).getProfileMethods().delete()
                        DatabaseDyne.getInstance(this@Home.applicationContext).getchatmethods().delete()
                        DatabaseDyne.getInstance(this@Home.applicationContext).getHomeMethods().delete()
                    }.start()
                    val intent= Intent(this@Home, Login::class.java)
                    startActivity(intent)
                    finish()
                }

        }

            return@setNavigationItemSelectedListener true
            }


        val fade = Fade()
        val decor = window.decorView
        fade.excludeTarget(decor.findViewById<ActionBarContainer>(R.id.action_bar_container), true)
        fade.excludeTarget(
            decor.findViewById<ActionBarContainer>(android.R.id.navigationBarBackground),
            true
        )
        fade.excludeTarget(
            decor.findViewById<ActionBarContainer>(android.R.id.statusBarBackground),
            true
        )
        window.enterTransition=null
        window.exitTransition=null

        var currentFragment= "fragment_home"



        val fragmentTransation:FragmentTransaction= supportFragmentManager.beginTransaction()
        fragmentTransation.add(R.id.fragment,
            fragment_home()
        )
        fragmentTransation.disallowAddToBackStack()
        fragmentTransation.commit()

        imageView5.setOnClickListener{
            val fragmentTransation:FragmentTransaction= supportFragmentManager.beginTransaction()
            when (homeFragment){
            "gyms"->{fragmentTransation.replace(R.id.fragment, fragment_home())}
            "chat"->{ fragmentTransation.replace(R.id.fragment, chatlist()) }
                "mem"->{}
                "sups"->{}
                "adv"->{}
                "abu"->{}
            }
            fragmentTransation.disallowAddToBackStack()
            fragmentTransation.commit()
            when (currentFragment){
                "fragment_home"->{}
                "profile"->{
                    imageView8.setImageDrawable(getDrawable(R.drawable.profile))}
                "stats"->{
                    imageView9.setImageDrawable(getDrawable(R.drawable.stats))}
                "schedule"->{imageView6.setImageDrawable(getDrawable(R.drawable.schedule))}
                "diet"->{
                    imageView7.setImageDrawable(getDrawable(R.drawable.diet))}
            }
            currentFragment="fragment_home"
        }
        imageView6.setOnClickListener{
            val fragmentTransation:FragmentTransaction= supportFragmentManager.beginTransaction()
            fragmentTransation.replace(R.id.fragment,
                schedule()
            )
            fragmentTransation.disallowAddToBackStack()
            fragmentTransation.commit()
            when (currentFragment){
                "fragment_home"->{imageView6.setImageDrawable(getDrawable(R.drawable.schedule_on))}
                "profile"->{imageView6.setImageDrawable(getDrawable(R.drawable.schedule_on))
                            imageView8.setImageDrawable(getDrawable(R.drawable.profile))}
                "stats"->{imageView6.setImageDrawable(getDrawable(R.drawable.schedule_on))
                            imageView9.setImageDrawable(getDrawable(R.drawable.stats))}
                "schedule"->{}
                "diet"->{imageView6.setImageDrawable(getDrawable(R.drawable.schedule_on))
                            imageView7.setImageDrawable(getDrawable(R.drawable.diet))}
            }
            currentFragment="schedule"
        }

        imageView7.setOnClickListener{
            val fragmentTransation:FragmentTransaction= supportFragmentManager.beginTransaction()
            fragmentTransation.replace(R.id.fragment,
                diet()
            )
            fragmentTransation.disallowAddToBackStack()
            fragmentTransation.commit()
            when (currentFragment){
                "fragment_home"->{imageView7.setImageDrawable(getDrawable(R.drawable.diet_on))}
                "profile"->{imageView7.setImageDrawable(getDrawable(R.drawable.diet_on))
                            imageView8.setImageDrawable(getDrawable(R.drawable.profile))}
                "stats"->{imageView7.setImageDrawable(getDrawable(R.drawable.diet_on))
                            imageView9.setImageDrawable(getDrawable(R.drawable.stats))}
                "schedule"->{imageView7.setImageDrawable(getDrawable(R.drawable.diet_on))
                    imageView6.setImageDrawable(getDrawable(R.drawable.schedule))}
                "diet"->{}
            }
            currentFragment="diet"
        }

        imageView8.setOnClickListener{
            val fragmentTransation:FragmentTransaction= supportFragmentManager.beginTransaction()
            fragmentTransation.replace(R.id.fragment,
                profile()
            )
            fragmentTransation.disallowAddToBackStack()
            fragmentTransation.commit()
            when (currentFragment){
                "fragment_home"->{imageView8.setImageDrawable(getDrawable(R.drawable.profile_on))}
                "profile"->{}
                "stats"->{imageView8.setImageDrawable(getDrawable(R.drawable.profile_on))
                            imageView9.setImageDrawable(getDrawable(R.drawable.stats))}
                "schedule"->{imageView8.setImageDrawable(getDrawable(R.drawable.profile_on))
                            imageView6.setImageDrawable(getDrawable(R.drawable.schedule))}
                "diet"->{imageView8.setImageDrawable(getDrawable(R.drawable.profile_on))
                            imageView7.setImageDrawable(getDrawable(R.drawable.diet))}
            }
            currentFragment="profile"
        }

        imageView9.setOnClickListener{
            val fragmentTransation:FragmentTransaction= supportFragmentManager.beginTransaction()
            fragmentTransation.replace(R.id.fragment,
                stats()
            )
            fragmentTransation.disallowAddToBackStack()
            fragmentTransation.commit()
            when (currentFragment){
                "fragment_home"->{imageView9.setImageDrawable(getDrawable(R.drawable.stats_on))}
                "profile"->{imageView9.setImageDrawable(getDrawable(R.drawable.stats_on))
                            imageView8.setImageDrawable(getDrawable(R.drawable.profile))}
                "stats"->{}
                "schedule"->{imageView9.setImageDrawable(getDrawable(R.drawable.stats_on))
                            imageView6.setImageDrawable(getDrawable(R.drawable.schedule))}
                "diet"->{imageView9.setImageDrawable(getDrawable(R.drawable.stats_on))
                imageView7.setImageDrawable(getDrawable(R.drawable.diet))}
            }
            currentFragment="stats"
        }
    }


}
