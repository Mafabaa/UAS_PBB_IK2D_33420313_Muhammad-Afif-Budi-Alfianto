package studio.afif.example.uaspraktekpbb

import android.content.ContextParams
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import studio.afif.example.uaspraktekpbb.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ImageSliderAdapter
    private val list = ArrayList<ImageData>()
    private lateinit var dots: ArrayList<TextView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        list.add(
            ImageData(
                "https://media.istockphoto.com/photos/friends-in-the-cinema-picture-id1180701083?b=1&k=20&m=1180701083&s=170667a&w=0&h=i4RjlXSocbLiBpruz5KQY4wUlHZ9WX8bAVIMGf1qclw="
            )
        )

        list.add(
            ImageData(
                "https://images.unsplash.com/photo-1485846234645-a62644f84728?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MXx8bW92aWV8ZW58MHx8MHx8&auto=format&fit=crop&w=500&q=60"
            )
        )

        list.add(
            ImageData(
                "https://images.unsplash.com/photo-1626814026160-2237a95fc5a0?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTZ8fG1vdmllfGVufDB8fDB8fA%3D%3D&auto=format&fit=crop&w=500&q=60"
            )
        )

        adapter = ImageSliderAdapter(list)
        binding.viewPager.adapter = adapter
        dots = ArrayList()
        setIndicator()

        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                selectedDot(position)
                super.onPageSelected(position)
            }
        })


        val imgAction: ImageView = findViewById(R.id.card_action)
        imgAction.setOnClickListener(this)

        val imgHorror: ImageView = findViewById(R.id.card_horror)
        imgHorror.setOnClickListener(this)

        val imgRomance: ImageView = findViewById(R.id.card_romance)
        imgRomance.setOnClickListener(this)

        val imgThriller: ImageView = findViewById(R.id.card_thriller)
        imgThriller.setOnClickListener(this)
    }

    private fun selectedDot(position: Int) {
        for (i in 0 until list.size){
            if (i == position)
                dots[i].setTextColor(ContextCompat.getColor(this, R.color.white))
            else
                dots[i].setTextColor(ContextCompat.getColor(this, R.color.black))
        }
    }

    private fun setIndicator() {
        for (i in 0 until list.size){
            dots.add(TextView(this))
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                dots[i].text = Html.fromHtml("&#9679", Html.FROM_HTML_MODE_LEGACY).toString()
            }else {
                dots[i].text = Html.fromHtml("&#9679")
            }
            dots[i].textSize = 18f
            binding.dotsIndicator.addView(dots[i])
        }
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.card_action -> {
                val moveAction = Intent(this,ActionActivity::class.java)
                startActivity(moveAction)
            }
            R.id.card_horror -> {
                val moveHorror = Intent(this,HorrorActivity::class.java)
                startActivity(moveHorror)
            }
            R.id.card_romance -> {
                val moveRomance = Intent(this,RomanceActivity::class.java)
                startActivity(moveRomance)
            }
            R.id.card_thriller -> {
                val moveThriller = Intent(this,ThrillerActivity::class.java)
                startActivity(moveThriller)
            }
        }
    }
}