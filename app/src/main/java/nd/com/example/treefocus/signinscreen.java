package nd.com.example.treefocus;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView; // Make sure this is TextView
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class signinscreen extends AppCompatActivity {

    // We only need to declare the variables we will actually use in the code
    private EditText etEmail;
    private EditText etPassword;
    private Button btnSignin;
    private TextView btnsignup; // Changed to TextView to match the new XML

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // The EdgeToEdge code can be removed for simplicity
        setContentView(R.layout.activity_signinscreen);

        // Find the views from the layout
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnSignin = findViewById(R.id.btnSignin);
        btnsignup = findViewById(R.id.btnsignup); // This ID now refers to a TextView

        // --- Set Click Listeners ---

        // This is the ONLY listener for the "Sign In" button.
        // It takes the user to the Dashboard.
        btnSignin.setOnClickListener(v -> {
            // TODO: In the future, you can add code here to check if the email and password are correct.
            Toast.makeText(signinscreen.this, "Signing in...", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(signinscreen.this, Dashboardscreen.class);
            startActivity(intent);
        });

        // This listener for the "Sign Up" text takes the user to the Sign Up screen.
        btnsignup.setOnClickListener(v -> {
            // NOTE: 'testact' is likely your sign up screen. Consider renaming it to 'SignUpScreen' for clarity.
            Toast.makeText(signinscreen.this, "Going to Sign Up page...", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(signinscreen.this, signupscreen.class);
            startActivity(intent);
        });
    }
}
