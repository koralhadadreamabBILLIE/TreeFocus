package nd.com.example.treefocus;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Locale;

public class CustomTimerSessionActivity extends AppCompatActivity {

    private TextView tvTimer;
    private Button btnStop;
    private CountDownTimer countDownTimer;
    private long timeInMillis; // We get this from the user!

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_timer_session);

        tvTimer = findViewById(R.id.tv_custom_timer);
        btnStop = findViewById(R.id.btn_stop_custom);

        // 1. Get the time passed from the Dashboard/Setup screen
        // Default to 10 mins (600,000 ms) if something goes wrong
        timeInMillis = getIntent().getLongExtra("EXTRA_TIME", 600000);

        startTimer(timeInMillis);

        btnStop.setOnClickListener(v -> {
            if (countDownTimer != null) countDownTimer.cancel();
            finish();
        });
    }

    private void startTimer(long duration) {
        countDownTimer = new CountDownTimer(duration, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                int minutes = (int) (millisUntilFinished / 1000) / 60;
                int seconds = (int) (millisUntilFinished / 1000) % 60;
                tvTimer.setText(String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds));
            }

            @Override
            public void onFinish() {
                tvTimer.setText("DONE");
                // You could add a "Session Complete" sound or notification here!
            }
        }.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) countDownTimer.cancel();
    }
}