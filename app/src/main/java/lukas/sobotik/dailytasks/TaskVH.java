package lukas.sobotik.dailytasks;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import org.jetbrains.annotations.NotNull;

public class TaskVH extends RecyclerView.ViewHolder {
    TextView taskName;
    TextView taskDescription;
    public TaskVH(@NonNull @NotNull View itemView) {
        super(itemView);
        taskName = itemView.findViewById(R.id.task_card_name);
        taskDescription = itemView.findViewById(R.id.task_card_description);
    }
    public void bind(final Task item, final TaskAdapter.OnItemClickListener listener) {
        itemView.setOnClickListener(v -> listener.onItemClick(item));
    }
}
