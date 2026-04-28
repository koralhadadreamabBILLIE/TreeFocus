package nd.com.example.treefocus;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import nd.com.example.treefocus.data.MyTaskTable.data.AddStudyTaskActivity;
import nd.com.example.treefocus.data.MyTaskTable.data.AppDatabase;
import nd.com.example.treefocus.data.MyTaskTable.data.MyTaskTable.StudyTask;
import nd.com.example.treefocus.data.MyTaskTable.data.MyTaskTable.StudyTaskAdapter;
import nd.com.example.treefocus.data.MyTaskTable.data.StudyTaskViewModel;

public class StudyListActivity extends AppCompatActivity {

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
        adapter = new StudyTaskAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // --- 3. Start Listening to Firebase ---
        // We call the method here so it starts working immediately
        getAllFromFirebase(adapter);

        // --- 4. Setup the Floating Action Button ---
        fabAddTask.setOnClickListener(v -> {
            Intent intent = new Intent(StudyListActivity.this, AddStudyTaskActivity.class);
            startActivity(intent);
        });
    }


    @Override
    protected void onResume() { //Runs every time the screen becomes visible to the user.
        super.onResume();
        AppDatabase db =AppDatabase.getInstance(this);
        List<StudyTask> allTasks = db.studyTaskQuery().getAllTasks();
        adapter.setTasks(allTasks);
        adapter.notifyDataSetChanged();



    }
    // The purpose of this method is to set up a ValueEventListener on the Firebase 'tasks' node. It makes the app 'listen' for any changes in the database. If a task is added, deleted, or changed in the cloud, this method triggers automatically to refresh the UI.
    public void getAllFromFirebase(StudyTaskAdapter adapter) {
        //عنوان قاعدة البيانات
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        // عنوان مجموعة المعطيات داخل قاعدة البيانات
        DatabaseReference myRef = database.getReference("tasks");
//إضافة listener مما يسبب الإصغاء لكل تغيير حتلنة عرض المعطيات//
        myRef.addValueEventListener(new ValueEventListener() {
            @Override //دالة معالج حدث تقوم بتلقي نسخة عن كل المعطيات عند أي تغيير


            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //   تجهيز مبنى معطيات فارغ لحفظ المعطيات التي تلقيناها //
                ArrayList<StudyTask> tasks = new ArrayList<>();
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                        StudyTask task = postSnapshot.getValue(StudyTask.class);
                    tasks.add(task);
                }
                adapter.setTasks(tasks);//اضافة كل المعطيات للمنسق
                adapter.notifyDataSetChanged();//اعلام المنسق بالتغيير
            }


            @Override//بحالة فشل استخراج المعطيات
            public void onCancelled(@NonNull DatabaseError error) {
                // Failed to read value
                Log.w("TAG", "Failed to read value.", error.toException());
            }
        });
    }

}
