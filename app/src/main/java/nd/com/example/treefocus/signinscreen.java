package nd.com.example.treefocus;

import android.content.Intent;
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

public class signinscreen extends AppCompatActivity {
private ImageView imv;
private TextView tvWelcome;
private TextView tvEmail;
private TextView tvPassword;
private Button btnSignin;
private EditText etEmail;
private EditText etPassword;
    private Button btnsignup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signinscreen);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.textEmail), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });
        imv = findViewById(R.id.imv);
        tvWelcome = findViewById(R.id.tvWelcome);
        tvEmail = findViewById(R.id.tvEmail);
        etEmail = findViewById(R.id.etEmail);
        tvPassword = findViewById(R.id.tvPassword);
        EditText etPassword = findViewById(R.id.etPassword);
        btnSignin = findViewById(R.id.btnSignin);
        btnsignup = findViewById(R.id.btnsignup);
        btnSignin.setOnClickListener(v -> {
            Intent intent = new Intent(signinscreen.this, MainActivity.class);
            startActivity(intent);



        });
        btnsignup.setOnClickListener(v -> {
            Intent intent1 = new Intent(signinscreen.this, testact.class);
            startActivity(intent1);
        });
        btnSignin.setOnClickListener(v -> {
            Intent intent = new Intent(signinscreen.this, Dashboardscreen.class);
            startActivity(intent);
        });

    }
}