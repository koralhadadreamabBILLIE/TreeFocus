package nd.com.example.treefocus;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class signupscreen extends AppCompatActivity {

    // Declare the views we need to interact with
    private EditText edittext_email;
    private EditText editTextPassword;
    private EditText edittext_confirm_password;
    private Button button_signup;
    private TextView textvwSignin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // We can remove the EdgeToEdge code for a simpler, more stable layout for now
        setContentView(R.layout.activity_signupscreen);

        // Find all the views from our new layout
        edittext_email = findViewById(R.id.edittext_email);
        editTextPassword = findViewById(R.id.editTextPassword);
        edittext_confirm_password = findViewById(R.id.edittext_confirm_password);
        button_signup = findViewById(R.id.button_signup);
        textvwSignin = findViewById(R.id.textvwSignin);

        // --- Set Click Listeners ---

        // Listener for the "Create Account" button
        button_signup.setOnClickListener(v -> {
            // TODO: Add logic here to validate passwords and save the new user
            Toast.makeText(signupscreen.this, "Account Created!", Toast.LENGTH_SHORT).show();

            // After signing up, take the user to the dashboard
            Intent intent = new Intent(signupscreen.this, Dashboardscreen.class);
            startActivity(intent);
        });

        // Listener for the "Sign In" text
        textvwSignin.setOnClickListener(v -> {
            // Take the user to the sign-in screen
            Intent intent = new Intent(signupscreen.this, signinscreen.class);
            startActivity(intent);
        });
    }
}
