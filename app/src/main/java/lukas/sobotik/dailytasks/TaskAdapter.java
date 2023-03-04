package lukas.sobotik.dailytasks;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import org.jetbrains.annotations.NotNull;

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

    @Override
    public void onBindViewHolder(@NonNull @NotNull TaskVH holder, int position) {
        holder.taskName.setText(list.get(position).taskName);
        holder.taskDescription.setText(list.get(position).taskDescription);

        Log.d("Custom Logging", holder.taskDescription.getText().toString() + holder.taskDescription.getText().toString().isEmpty());
        if (holder.taskDescription.getText().toString().isEmpty()) {
            holder.taskDescription.setVisibility(View.GONE);
        } else {
            holder.taskDescription.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        if (list == null) return 0;
        else return list.size();
    }
}
