package nd.com.example.treefocus;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class signinscreen extends AppCompatActivity {

    // Declare the views we need to interact with
    private EditText etEmail;
    private EditText etPassword;
    private Button btnSignin;
    private TextView btnsignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signinscreen);

        // Find the views from the layout
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnSignin = findViewById(R.id.btnSignin);
        btnsignup = findViewById(R.id.btnsignup);

        // --- Set Click Listeners ---

        // Listener for the "Sign In" button
        btnSignin.setOnClickListener(v -> {
            // 1. Get the text from the input fields
            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            // 2. Call your validation method
            if (validateInputs(email, password)) {
                // If validation is successful:
                Toast.makeText(signinscreen.this, "Signing in...", Toast.LENGTH_SHORT).show();

                // TODO: In the future, you will check the email and password against a database here.
                // For now, we just go to the dashboard.

                Intent intent = new Intent(signinscreen.this, Dashboardscreen.class);
                startActivity(intent);
            }
            // If validation fails, the `validateInputs` method will show the errors.
        });

        // Listener for the "Sign Up" text
        btnsignup.setOnClickListener(v -> {
            // Take the user to the Sign Up screen
            Intent intent = new Intent(signinscreen.this, signupscreen.class);
            startActivity(intent);
        });
    } // <-- *** The onCreate method ends HERE ***

    /**
     * This is a helper method to validate the user's sign-in inputs.
     * It is correctly placed directly inside the signinscreen class.
     */
    private boolean validateInputs(String email, String password) {
        // Clear any previous errors first
        etEmail.setError(null);
        etPassword.setError(null);

        boolean isValid = true;

        if (email.isEmpty()) {
            etEmail.setError("Email cannot be empty");
            isValid = false;
        }

        if (password.isEmpty()) {
            etPassword.setError("Password cannot be empty");
            isValid = false;
        }

        return isValid;
    }

} // <-- *** The signinscreen class ends HERE ***
