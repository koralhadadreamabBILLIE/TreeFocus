package nd.com.example.treefocus;

import android.os.Bundle;
import android.os.Handler; // FIXED: Must be android.os.Handler
import android.os.Looper;  // ADDED: For modern Android standards
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class StopwatchSessionActivity extends AppCompatActivity {

    private TextView tvStopwatch;
    private Button btnStop;
    private long startTime = 0L;

    // FIXED: Use the Main Looper so the handler can talk to the UI
    /**
     * Creates a new Handler object using the Main Looper.
     * The Main Looper is used to ensure that the Handler can communicate with the UI thread.
     * This is necessary because the Handler is responsible for updating the UI.
     */
    private Handler handler = new Handler(Looper.getMainLooper());
    // This is the "Code" for our Thread/Worker
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            long millis = System.currentTimeMillis() - startTime;
            int seconds = (int) (millis / 1000);
            int minutes = seconds / 60;
            int displaySeconds = seconds % 60;

            // FIXED: Using Locale.getDefault() is safer for some Android versions
            tvStopwatch.setText(String.format(Locale.getDefault(), "%02d:%02d", minutes, displaySeconds));

            // This tells the handler to run this "Recipe" again in 1 second
            handler.postDelayed(this, 1000);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Make sure this layout name matches your XML file exactly!
        setContentView(R.layout.stopwatch_session);

        tvStopwatch = findViewById(R.id.tv_stopwatch_time);
        btnStop = findViewById(R.id.btn_stop_session);

        // Start the timer
        startTime = System.currentTimeMillis();
        handler.postDelayed(runnable, 0);

        btnStop.setOnClickListener(v -> {
            handler.removeCallbacks(runnable); // Stop the timer "worker"
            finish(); // Close this screen and go back
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // IMPORTANT: Stop the timer if the user closes the app
        // so it doesn't leak memory in the background!
        handler.removeCallbacks(runnable);
    }
}