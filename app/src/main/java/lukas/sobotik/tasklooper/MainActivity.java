package lukas.sobotik.tasklooper;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    TaskAdapter taskAdapter;
    RecyclerView taskRecyclerView;
    FloatingActionButton fab;
    DatabaseHelper dbHelper;
    SwipeRefreshLayout refreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();

        readDataFromDB();

        fab.setOnClickListener(b -> new TaskCreationDialog().show(
                getSupportFragmentManager(), TaskCreationDialog.TAG));

        taskRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), taskRecyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {

            }

            @Override
            public void onLongClick(View view, int position) {
                Task task = taskAdapter.getTaskFromPosition(position);
                new TaskEditDialog(task).show(
                        getSupportFragmentManager(), TaskEditDialog.TAG);
            }
        }));

        refreshLayout.setOnRefreshListener(() -> {
            readDataFromDB();
            refreshLayout.setRefreshing(false);
        });
    }

    void readDataFromDB() {
        List<Task> tasks = new ArrayList<>();

        Cursor cursor = dbHelper.readAllData();
        if (cursor.getCount() == 0) {
            return;
        }

        while (cursor.moveToNext()) {
            TaskCheckState state;
            if (Objects.equals(cursor.getString(3), TaskCheckState.checked.toString())) {
                state = TaskCheckState.checked;
            } else {
                state = TaskCheckState.unchecked;
            }

            tasks.add(new Task(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), state, cursor.getString(4)));
        }

        taskAdapter.setList(tasks);
        taskAdapter.notifyDataSetChanged();
    }

    private void initialize() {
        taskAdapter = new TaskAdapter(this);
        taskRecyclerView = findViewById(R.id.task_recycler_view);
        taskRecyclerView.setAdapter(taskAdapter);
        taskRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        fab = findViewById(R.id.create_new_task_fab);
        dbHelper = new DatabaseHelper(this);
        refreshLayout = findViewById(R.id.task_refresh_layout);
    }
}