//The purpose of this class is to manage the user interface for the sign-in screen.
// It's responsible for getting the email and password the user types, checking if they are valid, and then navigating to the main part of the app (the Dashboard) or the sign-up screen.
package nd.com.example.treefocus;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import nd.com.example.treefocus.data.MyTaskTable.data.AppDatabase;
import nd.com.example.treefocus.data.MyTaskTable.data.MyUserTable.Student;

public class signinscreen extends AppCompatActivity {

    // Declare the views we need to interact with
    private EditText etEmail;
    private EditText etPassword;
    private Button btnSignin;
    private TextView btnsignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // السطر الي تحت هو بربط بين ملف البرمجة java وملف التنسيق layout XML الملائم له
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
            String password = etPassword.getText().toString().trim();// trims() is for removing the destances and the unneeded space

            // 2. Call your validation method
            if (validateInputs(email, password)) {
                // If validation is successful:
                FirebaseAuth auth = FirebaseAuth.getInstance();
                auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                // Sign in successful
                                Toast.makeText(signinscreen.this, "Signed in successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(signinscreen.this, Dashboardscreen.class);
                                startActivity(intent);
                            } else {
                                // Sign in failed
                                Toast.makeText(signinscreen.this, "Sign in failed", Toast.LENGTH_SHORT).show();

                                AppDatabase db = AppDatabase.getInstance(signinscreen.this);
                                Student student = db.getStudentQuery().checkEmailPassw(email, password);
                                if (student != null) {
                                    Toast.makeText(signinscreen.this, "Signed in successfully", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(signinscreen.this, Dashboardscreen.class);
                                    startActivity(intent);
                                }
                            }
                        });
                  }
            // If validation fails, the `validateInputs` method will show the errors.
        });

        // Listener for the "Sign Up" text
        btnsignup.setOnClickListener(v -> {
            // Take the user to the Sign Up screen
            Intent intent = new Intent(signinscreen.this, signupscreen.class);
            startActivity(intent);    // When the user clicks the "Sign Up" text, it starts a new activity which is the sign-up screen. This line of code is responsible for starting this new activity.

        });
    }

    /**
     * This is a helper method to validate the user's sign-in inputs.
     * It is correctly placed directly inside the signinscreen class.
     */
    private boolean validateInputs(String email, String password) {
        // Clear any previous errors first
        // setError() :  هاي بتظهر ملاحظة بالاحمر عادة تحت الحقل اللي تم المتابه فيه بشكل خاطيء
        //عشان نهبر المستعمل وين عبا غلط وشو لازم يكون صح منكته بهاي الملاحظة
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
