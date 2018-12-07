package com.example.dgenkov.weatherapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Adapter
import android.widget.ArrayAdapter
import android.widget.ListAdapter
import android.widget.ListView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class WeatherForecastActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_forecast)

        val cityNameText = intent.extras.getString("cityNameText")


        var retriever = WeatherRetriever()

        val callback = object : Callback<Weather> {
            override fun onResponse(call: Call<Weather>, response: Response<Weather>) {
                println("success")
                title = response?.body()?.query?.results?.channel?.title
                println(response?.body()?.query?.results?.channel?.item?.forecast)
                var forecasts = response?.body()?.query?.results?.channel?.item?.forecast

                var forecastStrings = mutableListOf<String>()
                if (forecasts != null) {
                    for (forecastDay in forecasts) {
                        println("-------------------------")
                        println(forecastDay.date)
                        println(forecastDay.day)
                        println(forecastDay.high)
                        println(forecastDay.low)

                        forecastStrings.add("${forecastDay.date} - High:${forecastDay.high}, Low:${forecastDay.low} - ${forecastDay.text}")
                    }
                }

                var listView = findViewById<ListView>(R.id.forecastListView)

                var adapter = ArrayAdapter(this@WeatherForecastActivity,android.R.layout.simple_list_item_1,forecastStrings)

                listView.adapter = adapter
            }
            override fun onFailure(call: Call<Weather>, t: Throwable) {
                println("failure")
            }



        }

        retriever.getForecast(callback,cityNameText)
    }
}
