package io.rotlabs.fampayassignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val apiUrl = "https://run.mocky.io/v3/fefcfbeb-5c12-4722-94ad-b8f92caad1ad"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        famContainer.load(apiUrl)

    }
}