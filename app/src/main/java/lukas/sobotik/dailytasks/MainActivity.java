package lukas.sobotik.dailytasks;

import android.content.DialogInterface;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import static android.app.ProgressDialog.show;

public class MainActivity extends AppCompatActivity {

    TaskAdapter taskAdapter;
    RecyclerView taskRecyclerView;
    FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();

        List<String> list = new ArrayList<>();
        list.add("eee");
        list.add("eeee");

        taskAdapter.setList(list);

        fab.setOnClickListener(b -> {
            new TaskCreationDialog().show(
                    getSupportFragmentManager(), TaskCreationDialog.TAG);
        });
    }

    private void initialize() {
        taskAdapter = new TaskAdapter(this);
        taskRecyclerView = findViewById(R.id.task_recycler_view);
        taskRecyclerView.setAdapter(taskAdapter);
        taskRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        fab = findViewById(R.id.create_new_task_fab);
    }
}