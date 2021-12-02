package ru.octo.manyscreens

import android.app.ActivityManager
import android.app.ActivityOptions
import android.content.Context
import android.hardware.display.DisplayManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second_activoty)

        val displayManager = getSystemService(Context.DISPLAY_SERVICE) as DisplayManager
        val displays = displayManager.displays

        if (displays.size > 1) {
            val activityManager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
            val secondDisplay = displays[1]
            val activityAllowed = activityManager.isActivityStartAllowedOnDisplay(this, secondDisplay.displayId, intent)
            if (activityAllowed) {
                val options = ActivityOptions.makeBasic()
                options.launchDisplayId = secondDisplay.displayId
                startActivity(intent, options.toBundle())
            }
        }
    }
}
