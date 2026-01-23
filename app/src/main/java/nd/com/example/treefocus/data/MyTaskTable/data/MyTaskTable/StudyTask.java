package nd.com.example.treefocus.data.MyTaskTable.data.MyTaskTable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "StudyTask")
public class StudyTask {
    @PrimaryKey(autoGenerate = true)
    private int taskId;
    private String taskName;
    private String taskType; // "Pomodoro", "Stopwatch", "Custom"
     private String descreption;

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
}
