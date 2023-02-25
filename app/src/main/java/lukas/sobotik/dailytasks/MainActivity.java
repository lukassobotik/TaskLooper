package lukas.sobotik.dailytasks;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TaskAdapter taskAdapter;
    RecyclerView taskRecyclerView;
    FloatingActionButton fab;
    DatabaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();

        readDataFromDB();

        fab.setOnClickListener(b -> new TaskCreationDialog().show(
                getSupportFragmentManager(), TaskCreationDialog.TAG));
    }

    void readDataFromDB() {
        List<Task> tasks = new ArrayList<>();

        Cursor cursor = dbHelper.readAllData();
        if (cursor.getCount() == 0) {
            return;
        }

        while (cursor.moveToNext()) {
            tasks.add(new Task(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2)));
        }

        taskAdapter.setList(tasks);
        taskAdapter.notifyDataSetChanged();
    }

    private void initialize() {
        taskAdapter = new TaskAdapter(this, task -> {
            Log.d("xxx", task.taskName);
            new TaskEditDialog(task).show(
                    getSupportFragmentManager(), TaskEditDialog.TAG);
        });
        taskRecyclerView = findViewById(R.id.task_recycler_view);
        taskRecyclerView.setAdapter(taskAdapter);
        taskRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        fab = findViewById(R.id.create_new_task_fab);
        dbHelper = new DatabaseHelper(this);
    }
}