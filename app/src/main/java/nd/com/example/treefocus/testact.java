package nd.com.example.treefocus;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class testact extends AppCompatActivity {

private TextView text_title;
private TextView text_subtitle;
private EditText edittext_email;
private EditText editTextPassword;
private EditText edittext_confirm_password;
private Button buttonsignup;
private TextView text_login_prompt;
private TextView textvwSignin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_testact);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        text_title = findViewById(R.id.text_title);
        text_subtitle = findViewById(R.id.text_subtitle);
        edittext_email = findViewById(R.id.edittext_email);
        editTextPassword = findViewById(R.id.editTextPassword);
        edittext_confirm_password = findViewById(R.id.edittext_confirm_password);
        buttonsignup = findViewById(R.id.button_signup);
        text_login_prompt = findViewById(R.id.text_login_prompt);
        textvwSignin = findViewById(R.id.textvwSignin);
        textvwSignin.setOnClickListener(v -> {
            Intent intent = new Intent(testact.this, signinscreen.class);
            startActivity(intent);
        });
        buttonsignup.setOnClickListener(v -> {
            Intent intent = new Intent(testact.this, Dashboardscreen.class);
            startActivity(intent);
        });
    }
}
