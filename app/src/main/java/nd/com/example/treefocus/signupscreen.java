package nd.com.example.treefocus;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import nd.com.example.treefocus.data.MyTaskTable.data.MyUserTable.Student;
import nd.com.example.treefocus.data.MyTaskTable.data.AppDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class signupscreen extends AppCompatActivity {

    private EditText edittext_email;
    private EditText editTextPassword;
    private EditText edittext_confirm_password;
    private Button button_signup;
    private TextView textvwSignin;

    // These lines connect your screen to your database
    private AppDatabase db;
    private ExecutorService executorService; //todo gotta explain it
    // This is a handler that allows you to run code on the main thread
    // (the thread that displays your app's UI).
    // It's used to update the UI from a background thread.
    private Handler mainThreadHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signupscreen);

        // settin up everything needed so my app can use the database.
        db = AppDatabase.getDatabase(this);
        executorService = Executors.newSingleThreadExecutor();
        mainThreadHandler = new Handler(Looper.getMainLooper());

        // Find all the views
        edittext_email = findViewById(R.id.edittext_email);
        editTextPassword = findViewById(R.id.editTextPassword);
        edittext_confirm_password = findViewById(R.id.edittext_confirm_password);
        button_signup = findViewById(R.id.button_signup);
        textvwSignin = findViewById(R.id.textvwSignin);

        // --- Set Click Listeners ---
        button_signup.setOnClickListener(v -> {
            String email = edittext_email.getText().toString().trim();
            String password = editTextPassword.getText().toString().trim();
            String confirmPassword = edittext_confirm_password.getText().toString().trim();

            if (validateInputs(email, password, confirmPassword)) {
                // Instead of going straight to the dashboard, we now check the database
                checkUserAndSignUp(email, password);
            }
        });

        textvwSignin.setOnClickListener(v -> {
            startActivity(new Intent(signupscreen.this, signinscreen.class));
        });
    }

    // This is the new method that performs the database check
    private void checkUserAndSignUp(final String email, final String password) {
        // We call your existing 'checkEmail' method on a background thread
        executorService.execute(() -> {
            Student existingStudent = db.getStudentQuery().checkEmail(email); // CHECKS IN DATABASE

            mainThreadHandler.post(() -> {
                if (existingStudent != null) {
                    // --- USER ALREADY EXISTS ---
                    // Show a message and redirect to the sign-in screen
                    Toast.makeText(signupscreen.this, "An account with this email already exists. Please sign in.", Toast.LENGTH_LONG).show();
                } else {
                    // --- USER DOES NOT EXIST, PROCEED WITH SIGN UP ---
                    // Create a new Student object to save
                    Student newStudent = new Student();
                    newStudent.email = email;
                    newStudent.passw = password; // Assuming the password field in your Student class is named 'passw'
                    FirebaseAuth auth = FirebaseAuth.getInstance(); // line 1
                    //FirebaseAuth.getInstance(): Connecting to the Cloud Security service.
                  auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() { // line 2 , These lines are the "Cloud Security Guard." While your Room Database saves the user's info locally on the phone, Firebase Authentication saves the user's account in Google's secure cloud servers.
                  // line 2 : If this succeeds, the email and password are now officially saved in your Firebase Console under the "Authentication" tab.
                      //createUserWithEmailAndPassword(): Sending the credentials to Google's servers to create a permanent account.
                      //addOnCompleteListener: A listener that waits for the internet response so the app doesn't freeze.

                        @Override
                       public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // This code ONLY runs AFTER Google says "I saved it!"
                                Toast.makeText(signupscreen.this, "FB Task added successfully", Toast.LENGTH_SHORT).show();
                                finish();
                            } else {
                                // This code ONLY runs IF Google says "I couldn't save it!"
                                Toast.makeText(signupscreen.this, "FB Failed to add task", Toast.LENGTH_SHORT).show();
                            }
                        }





                   });
                    executorService.execute(() -> {

                        db.getStudentQuery().insert(newStudent);
                        Toast.makeText(signupscreen.this, "Account Created Successfully!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(signupscreen.this, Dashboardscreen.class));




                    });
                }
            });
        });
    }

    // Your existing validation method - no changes needed here, it's perfect
    private boolean validateInputs(String email, String password, String confirmPassword) {
        editTextPassword.setError(null);         // This method is used to clear any error messages that may have been set for the email EditText field.
        edittext_confirm_password.setError(null);         // It is typically called when the user starts typing in the field again, to remove any previous error messages.

        boolean isValid = true;
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (email.isEmpty() || !email.matches(emailPattern)) {
            edittext_email.setError("Please enter a valid email address");
            isValid = false;
        }
        if (password.isEmpty() || password.length() < 8) {
            editTextPassword.setError("Password must be at least 8 characters long");
            isValid = false;
        }
        if (confirmPassword.isEmpty() || !password.equals(confirmPassword)) {
            edittext_confirm_password.setError("Passwords do not match");
            isValid = false;
        }
        return isValid;

    }
}
