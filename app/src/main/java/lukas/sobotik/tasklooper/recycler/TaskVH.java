package lukas.sobotik.tasklooper.recycler;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.checkbox.MaterialCheckBox;
import lukas.sobotik.tasklooper.R;
import org.jetbrains.annotations.NotNull;

public class TaskVH extends RecyclerView.ViewHolder {
    TextView taskName;
    TextView taskDescription;
    MaterialCheckBox checkBox;
    public TaskVH(@NonNull @NotNull View itemView) {
        super(itemView);
        taskName = itemView.findViewById(R.id.task_card_name);
        taskDescription = itemView.findViewById(R.id.task_card_description);
        checkBox = itemView.findViewById(R.id.task_card_checkbox);
    }
}
