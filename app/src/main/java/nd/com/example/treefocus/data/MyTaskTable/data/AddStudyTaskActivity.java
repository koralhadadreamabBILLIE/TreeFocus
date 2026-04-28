//The purpose of this class is to control the screen where the user can create a new study task.
// It handles displaying عرض the input fields, listening for button clicks, and saving the new task to the database.

package nd.com.example.treefocus.data.MyTaskTable.data;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.EditText;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import nd.com.example.treefocus.CustomTimerSessionActivity;
import nd.com.example.treefocus.PomodoroSessionActivity;
import nd.com.example.treefocus.R;
import nd.com.example.treefocus.StopwatchSessionActivity;
import nd.com.example.treefocus.data.MyTaskTable.data.MyTaskTable.StudyTask;

public class AddStudyTaskActivity extends AppCompatActivity {

    private TextInputEditText edittext_task_name;
    private TextInputEditText edittext_task_description;
    private Spinner spinner_study_type;
    private Button button_save_task;
    private Button button_cancel;

    private nd.com.example.treefocus.data.MyTaskTable.data.StudyTaskViewModel studyTaskViewModel;

    //onCreate is the first method that runs when this screen is created. Its main job is to set everything up.
    //setContentView(R.layout.activity_add_study_task); is the line that links this Java file to its XML layout file.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);        setContentView(R.layout.activity_add_study_task);

        // 1. Link Java variables to the XML IDs
        edittext_task_name = findViewById(R.id.editTextTaskName);
        edittext_task_description = findViewById(R.id.editTextTaskDescription);
        spinner_study_type = findViewById(R.id.spinnerStudyType);
        button_save_task = findViewById(R.id.buttonSaveTask);
        button_cancel = findViewById(R.id.buttonCancel);

        // These are the new ones for the typing logic
        android.widget.LinearLayout layoutCustomTime = findViewById(R.id.layout_custom_time_input);
        android.widget.EditText etHours = findViewById(R.id.et_custom_hours);
        android.widget.EditText etMinutes = findViewById(R.id.et_custom_minutes);

        // 2. Setup the Spinner choices
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.study_types, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_study_type.setAdapter(adapter);

        // 3. Spinner Listener: Show/Hide input boxes based on choice
        spinner_study_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selected = parent.getItemAtPosition(position).toString();
                // If "Custom Timer" is picked, show the typing layout. Otherwise, hide it.
                if (selected.equalsIgnoreCase("Custom Timer")) {
                    layoutCustomTime.setVisibility(View.VISIBLE);
                } else {
                    layoutCustomTime.setVisibility(View.INVISIBLE);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        // 4. Save Button: Save to DB AND open the session screen
        button_save_task.setOnClickListener(v -> {
            validateFieldsAndSave(); // Saves to Room & Firebase

            String mode = spinner_study_type.getSelectedItem().toString();

            if (mode.equalsIgnoreCase("Stopwatch")) {
                startActivity(new Intent(this, StopwatchSessionActivity.class));
            }
            else if (mode.equalsIgnoreCase("Pomodoro")) {
                startActivity(new Intent(this, PomodoroSessionActivity.class));
            }
            else if (mode.equalsIgnoreCase("Custom Timer")) {
                // Get the numbers typed by the user
                String hStr = etHours.getText().toString();
                String mStr = etMinutes.getText().toString();

                long h = hStr.isEmpty() ? 0 : Long.parseLong(hStr);
                long m = mStr.isEmpty() ? 25 : Long.parseLong(mStr); // default 25m

                long totalMillis = (h * 3600000L) + (m * 60000L);

                Intent intent = new Intent(this, CustomTimerSessionActivity.class);
                intent.putExtra("EXTRA_TIME", totalMillis);
                startActivity(intent);
            }
        });

        button_cancel.setOnClickListener(v -> finish());

        // 5. Setup Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    // 5. THE METHOD IS RENAMED AND CORRECTED
    private void validateFieldsAndSave() //This is the custom method that does the real work when the user clicks "Save".
    {
        String taskName = edittext_task_name.getText().toString().trim();
        String description = edittext_task_description.getText().toString().trim();

        // Check if the name is empty. This is the only critical validation.
        if (taskName.isEmpty()) {
            edittext_task_name.setError("Task name cannot be empty");
            edittext_task_name.requestFocus();
            return; // Stop the process if the name is empty
        }

        // Create the new task object
        StudyTask newTask = new StudyTask();
        newTask.setTaskName(taskName);
        // I noticed your setter is setDescreption, let's match that. If it's a typo, you can fix it in StudyTask.java
        newTask.setDescreption(description);
        // I also noticed setTaskType in your code, let's match that too.
        newTask.setTaskType(spinner_study_type.getSelectedItem().toString());
  AppDatabase.getDatabase(this).studyTaskQuery().insertStudyTask(newTask);
savestudytask(newTask);
        // Now that the task is saved, show a message and close the screen.
        Toast.makeText(this, "Task saved successfully", Toast.LENGTH_SHORT).show();
        finish();
    }
    public void savestudytask(StudyTask studyTask) {// الحصول على مرجع إلى عقدة "users" في قاعدة البيانات

        // تهيئة Firebase Realtime Database    //مؤشر لقاعدة البيانات
        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
// ‏مؤشر لجدول المستعملين
        DatabaseReference studyTasksRef = database.child("studyTasks");
        // إنشاء مفتاح فريد للمستخدم الجديد
        DatabaseReference newstudyTaskRef = studyTasksRef.push();
        // تعيين معرف المستخدم في كائن MyUser
        studyTask.setStudyTaskId(newstudyTaskRef.getKey());
        // حفظ بيانات المستخدم في قاعدة البيانات
        //اضافة كائن "لمجموعة" المستعملين ومعالج حدث لفحص نجاح المطلوب
       // معالج حدث لفحص هل تم المطلوب من قاعدة البيانات //
        Task<Void> voidTask = newstudyTaskRef.setValue(studyTask).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(AddStudyTaskActivity.this, "FB Task added successfully", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(AddStudyTaskActivity.this, "FB Failed to add task", Toast.LENGTH_SHORT).show();
                }
            }


        });
    }
}

    

