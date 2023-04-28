package com.danniyal.weatherappkotlinclassic

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://api.openweathermap.org/data/2.5/";


class MainActivity : AppCompatActivity() {

    //https://api.openweathermap.org/data/2.5/forecast?q=Karachi&appid=907d232eaa93fcce054f3599021123df&units=metric

    var tv_temp: TextView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tv_temp = findViewById(R.id.tv_temp)


        fetchWeatherData()
    }


    private fun fetchWeatherData() {

        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiInterface::class.java)


        val response = retrofit.getWeatherData("Karachi", "907d232eaa93fcce054f3599021123df" , "metric")


        response.enqueue(object : Callback<Example> {
            override fun onResponse(call: Call<Example>, response: Response<Example>) {

                val responseBody = response.body()!!

                tv_temp!!.text = responseBody.list[0].main.temp.toString()

            }

            override fun onFailure(call: Call<Example>, t: Throwable) {
                Log.d("DATA", t.toString())
            }

        })



    }
}