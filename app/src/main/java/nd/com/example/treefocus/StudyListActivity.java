package nd.com.example.treefocus;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import nd.com.example.treefocus.data.MyTaskTable.data.AddStudyTaskActivity;
import nd.com.example.treefocus.data.MyTaskTable.data.AppDatabase;
import nd.com.example.treefocus.data.MyTaskTable.data.MyTaskTable.StudyTask;
import nd.com.example.treefocus.data.MyTaskTable.data.MyTaskTable.StudyTaskAdapter;
import nd.com.example.treefocus.data.MyTaskTable.data.StudyTaskViewModel;

public class StudyListActivity extends AppCompatActivity {

    private StudyTaskViewModel studyTaskViewModel;
    private RecyclerView recyclerView;
    private StudyTaskAdapter adapter;
    private FloatingActionButton fabAddTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_list_actvity);

        // --- 1. Find the Views ---
        Toolbar toolbar = findViewById(R.id.toolbar_study_list);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recyclerView_tasks);
        fabAddTask = findViewById(R.id.fab_add_task);


        // --- 2. Setup the RecyclerView ---
        // Create an instance of our "chef" (the adapter)
        adapter = new StudyTaskAdapter();

        // Tell the RecyclerView to use our adapter
        recyclerView.setAdapter(adapter);

        // Tell the RecyclerView how to arrange the items (in a simple vertical list)
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


//        // --- 3. Setup the ViewModel to get data ---
//        // The ViewModel is a modern way to fetch and observe data safely.
//        // I noticed your teacher might want you to use it, so this is the best practice.
//        studyTaskViewModel = new ViewModelProvider(this).get(StudyTaskViewModel.class);
//
//        // Tell the ViewModel to start observing the data.
//        // When the data in the database changes, this code will automatically run!
//        studyTaskViewModel.getAllTasks().observe(this, tasks -> {
//            // This is the magic part: when the list of tasks is ready,
//            // give it to the adapter to display.
//            adapter.setTasks(tasks);
//        });
//

        // --- 4. Setup the Floating Action Button ---
        fabAddTask.setOnClickListener(v -> {
            // When the user clicks the '+' button, open the AddStudyTaskActivity
            Intent intent = new Intent(StudyListActivity.this, AddStudyTaskActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        AppDatabase db =AppDatabase.getInstance(this);
        List<StudyTask> allTasks = db.studyTaskQuery().getAllTasks();
        adapter.setTasks(allTasks);
        adapter.notifyDataSetChanged();


    }
}
