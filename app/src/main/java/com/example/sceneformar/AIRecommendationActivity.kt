package com.example.sceneformar

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.io.IOException

class AIRecommendationActivity : AppCompatActivity() {

    private lateinit var chestSizeEditText: EditText
    private lateinit var shoulderSizeEditText: EditText
    private lateinit var lengthSizeEditText: EditText
    private lateinit var userSizeEditText: EditText
    private lateinit var getRecommendationButton: Button
    private lateinit var recommendationTextView: TextView
    private var apparelClassifier: ApparelClassifier? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ai_recommendation)

        chestSizeEditText = findViewById(R.id.chestSizeEditText)
        shoulderSizeEditText = findViewById(R.id.shoulderSizeEditText)
        lengthSizeEditText = findViewById(R.id.lengthSizeEditText)
        userSizeEditText = findViewById(R.id.userSizeEditText)
        getRecommendationButton = findViewById(R.id.getRecommendationButton)
        recommendationTextView = findViewById(R.id.recommendationTextView)

        try {
            apparelClassifier = ApparelClassifier(this)
        } catch (e: IOException) {
            Toast.makeText(this, "Failed to load model", Toast.LENGTH_SHORT).show()
        }

        getRecommendationButton.setOnClickListener {
            val userSize = userSizeEditText.text.toString().toFloatOrNull()

            if (userSize != null) {
                val recommendation = apparelClassifier?.getRecommendation(userSize)
                recommendationTextView.text = recommendation ?: "No recommendation available"
            } else {
                Toast.makeText(this, "Please enter a valid user size", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
