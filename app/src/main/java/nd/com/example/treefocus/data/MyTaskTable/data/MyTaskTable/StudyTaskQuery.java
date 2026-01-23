package nd.com.example.treefocus.data.MyTaskTable.data.MyTaskTable;

import androidx.lifecycle.LiveData;
import androidx.room.Dao; // 1. Import the @Dao annotation
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao // 2. Add the @Dao annotation here
public interface StudyTaskQuery {

    @Query("SELECT * FROM StudyTask  ")
    List<StudyTask> getAllStudyTasks();

    @Query("SELECT * FROM StudyTask ORDER BY taskName DESC")
    List<StudyTask> getAllStudyTasksByName();

    @Insert
    void insertStudyTask(StudyTask studyTask);

    @Update
    void updateStudyTask(StudyTask studyTask);

    @Delete
    void deleteStudyTask(StudyTask studyTask);

    //select all tasks where the taskId column is equal to the value I provide.
    @Query("SELECT * FROM StudyTask WHERE taskId = :id")
    StudyTask getStudyTaskById(int id);

    @Query("SELECT * FROM StudyTask ORDER BY taskName ASC")
        List<StudyTask> getAllTasks();


}
