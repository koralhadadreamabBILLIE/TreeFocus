package nd.com.example.treefocus;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

// We removed 'import android.widget.ImageView;' because it's no longer used.
// This is good practice for keeping code clean.

public class Dashboardscreen extends AppCompatActivity {

    // --- Declare UI elements ---
    // We removed 'private ImageView settingsIcon, profileIcon;'
    private TextView streakDaysText;
    private Button startSessionButton, appBlockingButton, viewProgressButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboardscreen);

        // --- Find all the views by their ID ---
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false); // Hide the default title
        }

        // We removed the lines for finding the icons:
        // settingsIcon = findViewById(R.id.settings_icon);
        // profileIcon = findViewById(R.id.profile_icon);

        streakDaysText = findViewById(R.id.streak_days_text);
        startSessionButton = findViewById(R.id.start_session_button);
        appBlockingButton = findViewById(R.id.app_blocking_button);
        viewProgressButton = findViewById(R.id.view_progress_button);


        // --- Set click listeners for interactive elements ---

        // We removed the click listeners for the icons.
        /*
        settingsIcon.setOnClickListener(v -> {
            Toast.makeText(Dashboardscreen.this, "Settings clicked!", Toast.LENGTH_SHORT).show();
        });

        profileIcon.setOnClickListener(v -> {
            Toast.makeText(Dashboardscreen.this, "Profile clicked!", Toast.LENGTH_SHORT).show();
        });
        */

        startSessionButton.setOnClickListener(v -> {
            // TODO: Open Study Mode Selection screen
            Toast.makeText(Dashboardscreen.this, "Starting Study Session...", Toast.LENGTH_SHORT).show();
            // Example: Intent intent = new Intent(Dashboardscreen.this, StudyModeSelectionActivity.class);
            // startActivity(intent);
        });

        appBlockingButton.setOnClickListener(v -> {
            // TODO: Open App Blocking Settings screen
            Toast.makeText(Dashboardscreen.this, "Opening App Blocking Settings...", Toast.LENGTH_SHORT).show();
            // Example: Intent intent = new Intent(Dashboardscreen.this, AppBlockingActivity.class);
            // startActivity(intent);
        });

        viewProgressButton.setOnClickListener(v -> {
            // TODO: Open Progress/Streaks screen
            Toast.makeText(Dashboardscreen.this, "Opening Progress...", Toast.LENGTH_SHORT).show();
            // Example: Intent intent = new Intent(Dashboardscreen.this, ProgressActivity.class);
            // startActivity(intent);
        });

        // You can dynamically set the streak here
        // For example, if you retrieve the streak count from a database
        int currentStreak = 7; // Example value
        streakDaysText.setText(currentStreak + " Days");
    }
}
