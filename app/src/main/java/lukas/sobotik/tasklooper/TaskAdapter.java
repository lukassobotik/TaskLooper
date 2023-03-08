package lukas.sobotik.tasklooper;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.checkbox.MaterialCheckBox;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskVH> {

    Context context;
    List<Task> list;

    public TaskAdapter(Context context) {
        this.context = context;
    }

    public void setList(List<Task> list) {
        this.list = list;
    }

    public Task getTaskFromPosition(int position) {
        return list.get(position);
    }

    @NonNull
    @NotNull
    @Override
    public TaskVH onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.task_card, parent, false);
        return new TaskVH(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull @NotNull TaskVH holder, int position) {
        TaskDatabaseHelper taskDatabaseHelper = new TaskDatabaseHelper(context);
        Task task = list.get(position);

        holder.taskName.setText(list.get(position).taskName);
        holder.taskDescription.setText(list.get(position).taskDescription);

        if (!task.checkedDate.equals(LocalDate.now().toString())) {
            task.setState(TaskCheckState.unchecked);
            task.setCheckedDate("");
            taskDatabaseHelper.updateData(task);
        }

        if (task.state.equals(TaskCheckState.checked)) {
            holder.checkBox.setChecked(true);
        } else {
            holder.checkBox.setChecked(false);
        }

        Log.d("Custom Logging", holder.taskDescription.getText().toString() + holder.taskDescription.getText().toString().isEmpty());
        if (holder.taskDescription.getText().toString().isEmpty()) {
            holder.taskDescription.setVisibility(View.GONE);
        } else {
            holder.taskDescription.setVisibility(View.VISIBLE);
        }

        // CheckBox change written to the Database
        holder.checkBox.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b) {
                task.setState(TaskCheckState.checked);
                task.setCheckedDate(LocalDate.now().toString());
                taskDatabaseHelper.updateData(task);
                holder.itemView.setAlpha(0.5f);
            } else {
                task.setState(TaskCheckState.unchecked);
                task.setCheckedDate("");
                taskDatabaseHelper.updateData(task);
                holder.itemView.setAlpha(1);
            }
        });
        holder.itemView.setOnClickListener(view -> {
            if (holder.checkBox.getCheckedState() == MaterialCheckBox.STATE_UNCHECKED) {
                holder.checkBox.setChecked(true);
                task.setState(TaskCheckState.checked);
                task.setCheckedDate(LocalDate.now().toString());
                taskDatabaseHelper.updateData(task);
                holder.itemView.setAlpha(0.5f);
            } else {
                holder.checkBox.setChecked(false);
                task.setState(TaskCheckState.unchecked);
                task.setCheckedDate("");
                taskDatabaseHelper.updateData(task);
                holder.itemView.setAlpha(1);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (list == null) return 0;
        else return list.size();
    }
}
