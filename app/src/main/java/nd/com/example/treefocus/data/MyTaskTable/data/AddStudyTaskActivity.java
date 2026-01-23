package nd.com.example.treefocus.data.MyTaskTable.data;

import android.os.Bundle;
import android.widget.ArrayAdapter; // Import ArrayAdapter
import android.widget.Button;
import android.widget.Spinner;      // Import Spinner
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar; // Import Toolbar

import com.google.android.material.textfield.TextInputEditText;

import nd.com.example.treefocus.R;
import nd.com.example.treefocus.data.MyTaskTable.data.MyTaskTable.StudyTask;

public class AddStudyTaskActivity extends AppCompatActivity {

    private TextInputEditText edittext_task_name;
    private TextInputEditText edittext_task_description;
    private Spinner spinner_study_type;
    private Button button_save_task;
    private Button button_cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // The EdgeToEdge code can sometimes interfere with toolbars.
        // It's better to remove it for this screen to ensure the layout is clean.
        // EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_study_task);

        // --- Find all the views ---
        edittext_task_name = findViewById(R.id.editTextTaskName);
        edittext_task_description = findViewById(R.id.editTextTaskDescription);
        spinner_study_type = findViewById(R.id.spinnerStudyType);
        button_save_task = findViewById(R.id.buttonSaveTask);
        button_cancel = findViewById(R.id.buttonCancel);
        button_save_task.setOnClickListener(v -> {
            if (validateFieldsAndSave()) {


                Toast.makeText(this, "Task saved successfully", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        button_cancel.setOnClickListener(v -> finish());
        // --- Setup Toolbar ---
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Optional: Add a back button to the toolbar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        // --- Setup the Spinner ---
        // 1. Find the Spinner in your layout
        Spinner spinnerStudyType = findViewById(R.id.spinnerStudyType);

        // 2. Create an ArrayAdapter using the string array from strings.xml
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.study_types,
                android.R.layout.simple_spinner_item
        );

        // 3. Specify the layout for the dropdown list
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // 4. Apply the adapter to the spinner
        spinnerStudyType.setAdapter(adapter);

        // --- The old EdgeToEdge code is removed as it's not needed for this layout ---
        // ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
        //     Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
        //     v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
        //     return insets;
        // });
    }

    // This handles the click on the toolbar's back button
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed(); // Go back to the previous screen
        return true;
    }

    private boolean validateFieldsAndSave() {
        boolean isValid = true;
        boolean taskNameValid = !edittext_task_name.getText().toString().trim().isEmpty();
        boolean taskDescriptionValid = !edittext_task_description.getText().toString().trim().isEmpty();
        boolean studyTypeValid = spinner_study_type.getSelectedItem() != null;

        if (!taskNameValid) {
            edittext_task_name.setError("Task name cannot be empty");
            isValid = false;
        }

        if (!taskDescriptionValid) {
            edittext_task_description.setError("Task description cannot be empty");
            isValid = false;
        }

        if (!studyTypeValid) {
            Toast.makeText(this, "Please select a study type", Toast.LENGTH_SHORT).show();
            isValid = false;
        }
        if (isValid) {
            StudyTask newTask = new StudyTask();
            newTask.setTaskName(edittext_task_name.getText().toString().trim());
            newTask.setDescreption(edittext_task_description.getText().toString().trim());
            newTask.setTaskType(spinner_study_type.getSelectedItem().toString());

            AppDatabase db = AppDatabase.getDatabase(this);
            db.studyTaskQuery().insertStudyTask(newTask);

        }

        return isValid;
    }
}



