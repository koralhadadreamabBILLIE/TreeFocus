package nd.com.example.treefocus;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
// We don't need the Toolbar import anymore since we removed it.
// import androidx.appcompat.widget.Toolbar;

public class Dashboardscreen extends AppCompatActivity {

    // --- Declare UI elements ---
    private TextView streakDaysText;
    private Button startSessionButton, appBlockingButton, viewProgressButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboardscreen);

        // --- Find all the views by their ID ---

        // This Toolbar code has been removed since the Toolbar is gone from the XML.
        /*
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        */

        streakDaysText = findViewById(R.id.streak_days_text);
        startSessionButton = findViewById(R.id.start_session_button);
        appBlockingButton = findViewById(R.id.app_blocking_button);
        viewProgressButton = findViewById(R.id.view_progress_button);


        // --- Set click listeners for interactive elements ---

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
