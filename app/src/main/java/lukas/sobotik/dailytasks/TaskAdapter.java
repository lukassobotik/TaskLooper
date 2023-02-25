package lukas.sobotik.dailytasks;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskVH> {

    public interface OnItemClickListener {
        void onItemClick(Task item);
    }
    private OnItemClickListener listener;
    Context context;
    List<Task> list;

    public TaskAdapter(Context context, OnItemClickListener listener) {
        this.context = context;
        this.listener = listener;
    }
    public TaskAdapter(Context context, List<Task> list) {
        this.context = context;
        this.list = list;
    }

    public void setList(List<Task> list) {
        this.list = list;
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

        holder.bind(list.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
