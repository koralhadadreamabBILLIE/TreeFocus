package nd.com.example.treefocus;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Locale;

public class PomodoroSessionActivity extends AppCompatActivity {

    private TextView tvTimer, tvStatus;
    private Button btnStop;
    private CountDownTimer countDownTimer;

    // Time constants
    private static final long WORK_TIME = 50 * 60 * 1000;  // 50 mins
    private static final long BREAK_TIME = 10 * 60 * 1000; // 10 mins

    private boolean isWorking = true; // Our "Switch"

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pomodoro_session);

        tvTimer = findViewById(R.id.tv_pomodoro_timer);
        tvStatus = findViewById(R.id.tv_pomodoro_status);
        btnStop = findViewById(R.id.btn_stop_pomodoro);

        // Start the first session (Work)
        startNewTimer(WORK_TIME);

        btnStop.setOnClickListener(v -> {
            if (countDownTimer != null) countDownTimer.cancel();
            finish();
        });
    }

    private void startNewTimer(long duration) {
        // If a timer is already running, kill it before starting a new one
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }

        countDownTimer = new CountDownTimer(duration, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                updateCountDownText(millisUntilFinished);
            }

            @Override
            public void onFinish() {
                switchSession(); // This is the magic part!
            }
        }.start();
    }

    private void switchSession() {
        if (isWorking) {
            // Work just finished -> Start Break
            isWorking = false;
            tvStatus.setText("REST & RECHARGE");
            tvStatus.setTextColor(android.graphics.Color.parseColor("#C8E6C9")); // Forest Green
            startNewTimer(BREAK_TIME);
        } else {
            // Break just finished -> Start Work
            isWorking = true;
            tvStatus.setText("STAY FOCUSED");
            tvStatus.setTextColor(android.graphics.Color.parseColor("#E58EBB")); // Blossom Pink
            startNewTimer(WORK_TIME);
        }
        //switchSession (meaning fow wht i did in it) :  implemented a recursive timer logic using a boolean flag isWorking. When the CountDownTimer hits onFinish(), it toggles the flag and immediately triggers a new timer with the alternative duration (Work vs. Break). This creates a continuous Pomodoro cycle without requiring user intervention.
    }

    private void updateCountDownText(long millisUntilFinished) {
        int minutes = (int) (millisUntilFinished / 1000) / 60;
        int seconds = (int) (millisUntilFinished / 1000) % 60;
        tvTimer.setText(String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) countDownTimer.cancel();
    }
}