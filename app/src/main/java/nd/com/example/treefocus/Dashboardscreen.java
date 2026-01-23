package nd.com.example.treefocus;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView; // Import ImageView
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import nd.com.example.treefocus.data.MyTaskTable.data.AddStudyTaskActivity;

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

        profileIcon.setOnClickListener(v -> {
            // TODO: Open Profile screen
            Toast.makeText(Dashboardscreen.this, "Profile clicked!", Toast.LENGTH_SHORT).show();
        });

        startSessionButton.setOnClickListener(v -> {
            Toast.makeText(Dashboardscreen.this, "Starting Study Session...", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Dashboardscreen.this, AddStudyTaskActivity.class);
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
