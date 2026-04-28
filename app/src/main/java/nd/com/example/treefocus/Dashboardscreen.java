package nd.com.example.treefocus;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView; // Import ImageView
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

// this line is the line that calls the lifecycle activities/methods (onCreate, onResume, etc)
public class Dashboardscreen extends AppCompatActivity {

    // --- Declare UI elements ---
    private ImageView settingsIcon, profileIcon; // Added icons
    private TextView streakDaysText;
    private Button startSessionButton, appBlockingButton, viewProgressButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboardscreen);
        // --- Find all the views by their ID ---
        settingsIcon = findViewById(R.id.settings_icon);
        profileIcon = findViewById(R.id.profile_icon);
        streakDaysText = findViewById(R.id.streak_days_text);
        startSessionButton = findViewById(R.id.start_session_button);
        appBlockingButton = findViewById(R.id.app_blocking_button);
        viewProgressButton = findViewById(R.id.view_progress_button);

        // --- Set click listeners for interactive elements ---
        settingsIcon.setOnClickListener(v -> {
            // TODO: Open Settings screen
            Toast.makeText(Dashboardscreen.this, "Settings clicked!", Toast.LENGTH_SHORT).show();
        });

        // --- Set click listener for the Profile Icon ---
        profileIcon.setOnClickListener(v -> {
            // 1. Create the Intent to move from the Dashboard to the Profile screen
            Intent intent = new Intent(Dashboardscreen.this, ProfileActivity.class);

            // 2. Start the activity
            startActivity(intent);
        });


        // In Dashboardscreen.java

        startSessionButton.setOnClickListener(v -> {
            // Create an Intent to open the NEW and CORRECT AddStudyTaskActivity screen
            Intent intent = new Intent(Dashboardscreen.this, nd.com.example.treefocus.data.MyTaskTable.data.AddStudyTaskActivity.class);

            // Start the new screen
            startActivity(intent);
        });

        appBlockingButton.setOnClickListener(v -> {
            Toast.makeText(Dashboardscreen.this, "Opening App Blocking Settings...", Toast.LENGTH_SHORT).show();
        });

        viewProgressButton.setOnClickListener(v -> {
            Toast.makeText(Dashboardscreen.this, "Opening Progress...", Toast.LENGTH_SHORT).show();
        });

        // You can dynamically set the streak here
        int currentStreak = 7; // Example value
        streakDaysText.setText(currentStreak + " Days");

    }
}
