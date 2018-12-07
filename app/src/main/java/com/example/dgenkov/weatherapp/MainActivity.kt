package com.example.dgenkov.weatherapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var getForecastButton = findViewById<Button>(R.id.getForecastButton)
        var cityNameText = findViewById<EditText>(R.id.cityNameText)

        getForecastButton.setOnClickListener {
            var forecastIntent = Intent(this, WeatherForecastActivity::class.java)
            forecastIntent.putExtra("cityNameText", cityNameText.text.toString() )
            startActivity(forecastIntent)
        }
    }
}
