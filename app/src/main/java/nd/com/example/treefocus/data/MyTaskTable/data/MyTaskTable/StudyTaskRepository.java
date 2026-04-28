package nd.com.example.treefocus.data.MyTaskTable.data.MyTaskTable;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.LiveData;
import java.util.List;

import nd.com.example.treefocus.data.MyTaskTable.data.AppDatabase;

// not used in my project. then why not delete it? i might use it later.
public class StudyTaskRepository {

    private StudyTaskQuery studyTaskQuery;
    private List<StudyTask> allTasks;

    public StudyTaskRepository(Context application) {
        AppDatabase database = AppDatabase.getDatabase(application);
        studyTaskQuery = database.studyTaskQuery();
        allTasks = studyTaskQuery.getAllTasks();
    }

    public List<StudyTask> getAllTasks() {
        return allTasks;
    }
}

