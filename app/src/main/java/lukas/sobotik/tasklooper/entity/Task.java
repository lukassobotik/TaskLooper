package lukas.sobotik.tasklooper.entity;

import androidx.annotation.NonNull;

public class Task {
    int id;
    String taskName;
    String taskDescription;
    TaskCheckState state;
    String checkedDate;

    public Task(int id, @NonNull String taskName, String taskDescription, @NonNull TaskCheckState state, @NonNull String checkedDate) {
        this.id = id;
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.state = state;
        this.checkedDate = checkedDate;
    }
    public TaskCheckState getState() {
        return state;
    }

    public void setState(TaskCheckState state) {
        this.state = state;
    }

    public String getCheckedDate() {
        return checkedDate;
    }

    public void setCheckedDate(String checkedDate) {
        this.checkedDate = checkedDate;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
