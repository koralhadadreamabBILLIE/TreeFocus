package nd.com.example.treefocus.data.MyTaskTable.data.MyTaskTable;

public class StudyTask {
    private int taskId;
    private String taskName;
    private String taskType; // "Pomodoro", "Stopwatch", "Custom"
    private int durationMinutes;
    private String startTime;
    private String endTime;
    private boolean isCompleted;
    private boolean streakEarned;
    private String notes;
    private String date;

    // Constructor
    public StudyTask(int taskId, String taskName, String taskType, int durationMinutes, String date) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.taskType = taskType;
        this.durationMinutes = durationMinutes;
        this.date = date;
        this.isCompleted = false;
        this.streakEarned = false;
    }

    // Getters and Setters
    public int getTaskId() { return taskId; }
    public void setTaskId(int taskId) { this.taskId = taskId; }

    public String getTaskName() { return taskName; }
    public void setTaskName(String taskName) { this.taskName = taskName; }

    public String getTaskType() { return taskType; }
    public void setTaskType(String taskType) { this.taskType = taskType; }

    public int getDurationMinutes() { return durationMinutes; }
    public void setDurationMinutes(int durationMinutes) { this.durationMinutes = durationMinutes; }

    public String getStartTime() { return startTime; }
    public void setStartTime(String startTime) { this.startTime = startTime; }

    public String getEndTime() { return endTime; }
    public void setEndTime(String endTime) { this.endTime = endTime; }

    public boolean isCompleted() { return isCompleted; }
    public void setCompleted(boolean completed) { isCompleted = completed; }

    public boolean isStreakEarned() { return streakEarned; }
    public void setStreakEarned(boolean streakEarned) { this.streakEarned = streakEarned; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    // toString() method
    @Override
    public String toString() {
        return "Task{" +
                "taskId=" + taskId +
                ", taskName='" + taskName + '\'' +
                ", taskType='" + taskType + '\'' +
                ", durationMinutes=" + durationMinutes +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", isCompleted=" + isCompleted +
                ", streakEarned=" + streakEarned +
                ", notes='" + notes + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
