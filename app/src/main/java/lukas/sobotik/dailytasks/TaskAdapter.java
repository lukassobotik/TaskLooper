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

    Context context;
    List<Task> list;

    public TaskAdapter(Context context) {
        this.context = context;
        this.list = list;
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
        holder.textView.setText(list.get(position).taskName);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
