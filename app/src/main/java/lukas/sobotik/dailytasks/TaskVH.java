package lukas.sobotik.dailytasks;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import org.jetbrains.annotations.NotNull;

public class TaskVH extends RecyclerView.ViewHolder {
    TextView textView;
    public TaskVH(@NonNull @NotNull View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.task_card_txt);
    }
}
