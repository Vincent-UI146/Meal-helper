package com.example.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    // Log tag for debugging
    private val TAG = "MealDecisionHelper"

    // UI components
    private lateinit var timeInput: EditText
    private lateinit var suggestionOutput: TextView
    private lateinit var suggestButton: Button
    private lateinit var resetButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(TAG, "Activity created") // Log activity creation

        // Initialize UI components
        initializeViews()

        // Set up button click listeners
        setupButtonListeners()
    }

    /**
     * Initializes all UI components by finding their views
     */
    private fun initializeViews() {
        timeInput = findViewById(R.id.et_time_input)
        suggestionOutput = findViewById(R.id.tv_suggestion_output)
        suggestButton = findViewById(R.id.btn_suggest)
        resetButton = findViewById(R.id.btn_reset)
    }

    /**
     * Sets up click listeners for buttons
     */
    private fun setupButtonListeners() {
        // Suggest meal button
        suggestButton.setOnClickListener {
            Log.d(TAG, "Suggest button clicked")
            suggestMeal()
        }

        // Reset button
        resetButton.setOnClickListener {
            Log.d(TAG, "Reset button clicked")
            resetApp()
        }
    }

    /**
     * Determines and displays meal suggestion based on time of day
     */
    private fun suggestMeal() {
        val inputText = timeInput.text.toString().trim().lowercase()

        // Validate input
        if (inputText.isEmpty()) {
            showError("Please enter a time of day")
            return
        }

        // Determine meal suggestion
        val suggestion = when (inputText) {
            "morning" -> "Breakfast: Scrambled eggs with avocado toast and fresh orange juice"
            "mid-morning" -> "Mid-morning snack: Greek yogurt with mixed berries and granola"
            "afternoon" -> "Lunch: Grilled chicken sandwich with side salad"
            "mid-afternoon" -> "Afternoon snack: Banana with almond butter"
            "dinner" -> "Dinner: Garlic butter shrimp pasta with roasted vegetables"
            "after dinner" -> "Dessert: Dark chocolate with strawberries"
            else -> {
                showError("Invalid time. Try: Morning, Mid-morning, Afternoon, Mid-afternoon, Dinner, After dinner")
                return
            }
        }

        // Display suggestion
        displaySuggestion(suggestion)
    }

    /**
     * Displays the meal suggestion
     * @param suggestion The meal suggestion to display
     */
    private fun displaySuggestion(suggestion: String) {
        Log.d(TAG, "Displaying suggestion: $suggestion")
        suggestionOutput.text = suggestion
        suggestionOutput.visibility = View.VISIBLE
    }

    /**
     * Shows error message to user
     * @param message The error message to display
     */
    private fun showError(message: String) {
        Log.e(TAG, "Error: $message")
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        suggestionOutput.visibility = View.GONE
    }

    /**
     * Resets the app to its initial state
     */
    private fun resetApp() {
        timeInput.text.clear()
        suggestionOutput.text = ""
        suggestionOutput.visibility = View.GONE
        Log.d(TAG, "App reset completed")
        Toast.makeText(this, "App reset", Toast.LENGTH_SHORT).show()
    }
}