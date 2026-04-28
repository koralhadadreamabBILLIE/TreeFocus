package nd.com.example.treefocus.data.MyTaskTable.data.MyTaskTable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "StudyTask") // Tells Room: "This is a blueprint for a database table."

//•@Entity: "This annotation الاستدعاء tells the Room Database that this class represents a table.
// The table will be named StudyTask and it will store all of my tasks."
public class StudyTask {

    @PrimaryKey(autoGenerate = true)  // tells room: "this is the unique ID for each task"
    private int taskId;
    //This marks taskId as the primary key for the table. It's a unique identifier for every single task.
    // "autoGenerate = true" means that Room will automatically handle creating a new, unique number for each task so I don't have to.
    private String taskName;
    private String taskType; // "Pomodoro", "Stopwatch", "Custom"
     private String descreption;
    private String studyTaskId;

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }
    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public String getDescreption() {
        return descreption;
    }

    public void setDescreption(String descreption) {
        this.descreption = descreption;
    }

    @Override
    public String toString() {
        return "StudyTask{" +
                "taskId=" + taskId +
                ", taskName='" + taskName + '\'' +
                ", taskType='" + taskType + '\'' +
                ", descreption='" + descreption + '\'' +
                '}';
    }

    public void setStudyTaskId(String studyTaskId) {
        this.studyTaskId = studyTaskId;
    }

    public String getStudyTaskId() {
        return studyTaskId;
    }
}
