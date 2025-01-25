package com.shreya.quizapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.shreya.quizapp.R

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val usernameInput = findViewById<EditText>(R.id.username_input)
        val emailInput = findViewById<EditText>(R.id.email_input)
        val darkModeSwitch = findViewById<Switch>(R.id.dark_mode_switch)
        val saveButton = findViewById<Button>(R.id.save_button)
        val backButton = findViewById<Button>(R.id.back_button)

        val currentUser = FirebaseAuth.getInstance().currentUser
        usernameInput.setText(currentUser?.displayName)
        emailInput.setText(currentUser?.email)

        saveButton.setOnClickListener {
            val username = usernameInput.text.toString()
            val email = emailInput.text.toString()
            val darkMode = darkModeSwitch.isChecked

            saveSettings(username, email, darkMode)
        }

        backButton.setOnClickListener {
            finish() // Finish the activity and return to the previous screen
        }
    }

    private fun saveSettings(username: String, email: String, isDarkMode: Boolean) {
        val currentUser = FirebaseAuth.getInstance().currentUser
        if (currentUser != null) {
            // Update user profile with new username
            val profileUpdates = UserProfileChangeRequest.Builder()
                .setDisplayName(username)
                .build()

            currentUser.updateProfile(profileUpdates)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "Settings updated", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "Failed to update settings", Toast.LENGTH_SHORT).show()
                    }
                }

            // Save the dark mode preference (using SharedPreferences or another method)
            println("Dark Mode: $isDarkMode")
        }
    }
}
