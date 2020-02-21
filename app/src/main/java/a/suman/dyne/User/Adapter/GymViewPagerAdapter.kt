package a.suman.dyne.User.Adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide

class GymViewPagerAdapter(val context: Context) :PagerAdapter(){

    var photos:List<String> = listOf("https://homepages.cae.wisc.edu/~ece533/images/airplane.png", "https://homepages.cae.wisc.edu/~ece533/images/arctichare.png")

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount(): Int {
        return photos.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val imageView= ImageView(context)
        Glide.with(context).load(photos[position]).into(imageView)
        container.addView(imageView)
        return imageView
    }


    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }


}
