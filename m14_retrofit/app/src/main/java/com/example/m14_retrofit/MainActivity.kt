package com.example.m14_retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.m14_retrofit.databinding.ActivityMainBinding

private const val count = 10
private var currentCount = 0

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val updateInfo = {
            lifecycleScope.launchWhenStarted {
                val randomUserList = RetrofitServices.searchRandomUserApi.getRandomUser()
                val results = randomUserList.results.first()!!
                val picture = results.picture!!
                if (currentCount >= count) {
                    val location = results.location!!
                    val street = location.street!!
                    val coordinates = location.coordinates!!
                    val timezone = location.timezone!!
                    val login = results.login!!
                    binding.textForAdmin.text =
                        "Location: ${location.postcode}, ${location.country}, " +
                                "${location.state}, ${location.city},\n${street.name} " +
                                "${street.number} (" +
                                "${coordinates.latitude}, ${coordinates.longitude}\ntimezone: " +
                                "${timezone.offset}\n${timezone.description}\nLogin:\nuuid: " +
                                "${login.uuid}" +
                                "\nusername: ${login.username}\npassword: " +
                                "${login.password}\n salt" +
                                " ${login.salt}\nmd5: ${login.md5}\nsha1: ${login.sha1}" +
                                "\nsha256: ${login.sha256}"
                    binding.textInfo.text = randomUserList.info.toString()
                    Glide.with(this@MainActivity).load(picture.medium).into(binding.imageViewMed)
                    Glide.with(this@MainActivity).load(picture.thumbnail).into(binding.imageViewSmall)
                }
                Glide.with(this@MainActivity).load(picture.large).into(binding.imageViewBig)
                val name = results.name!!
                val dob = results.dob!!
                val registered = results.registered!!
                val id = results.id!!
                binding.textForUser.text = "${results.gender}\n${name.title} ${name.first} " +
                        "${name.last}\n${results.email}\n${dob.date}, age: ${dob.age}\n" +
                        "registered: ${registered.date}, \nage: ${registered.age}\nphone: " +
                        "${results.phone}\ncell: ${results.cell}\n${id.name}, ${id.value}\n nat:" +
                        " ${results.nat}"
            }
        }
        updateInfo()
        binding.buttonUpdate.setOnClickListener {
            currentCount += 1
            updateInfo()
        }
    }
}