package nd.com.example.treefocus;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity
{
    private ImageView imv;
    private TextView tvWelcome;
    private TextView tvEmail;

    private EditText etEmail;

  private TextView tvPassword;
  private EditText etPassword;
  private Button btnSignin;


    /// ljhkjh;lijl;kjlkjlk
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.splashscreen);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        imv = findViewById(R.id.imv);
        tvWelcome = findViewById(R.id.tvWelcome);
        tvEmail = findViewById(R.id.tvEmail);
        etEmail = findViewById(R.id.etEmail);
        tvPassword = findViewById(R.id.tvPassword);
        etPassword = findViewById(R.id.etPassword);
        btnSignin = findViewById(R.id.btnSignin);
    }
}