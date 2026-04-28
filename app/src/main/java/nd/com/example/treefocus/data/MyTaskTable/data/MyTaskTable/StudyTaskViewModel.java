package nd.com.example.treefocus.data.MyTaskTable.data;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import java.util.List;

import nd.com.example.treefocus.data.MyTaskTable.data.MyTaskTable.StudyTask;
import nd.com.example.treefocus.data.MyTaskTable.data.MyTaskTable.StudyTaskRepository;

public class StudyTaskViewModel extends AndroidViewModel {

    private StudyTaskRepository repository;
    private List<StudyTask> allTasks;

    public StudyTaskViewModel(@NonNull Application application) {
        super(application);
        repository = new StudyTaskRepository(application);
        allTasks = repository.getAllTasks();
    }

    public List<StudyTask> getAllTasks() {
        return allTasks;
    }

    public void insert(StudyTask newTask) {
    }
}
