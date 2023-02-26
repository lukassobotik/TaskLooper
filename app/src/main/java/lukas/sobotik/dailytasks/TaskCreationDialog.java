package lukas.sobotik.dailytasks;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import androidx.fragment.app.DialogFragment;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class TaskCreationDialog extends DialogFragment {

    public static String TAG = "TaskCreationDialog";

    TextInputEditText taskName;
    TextInputEditText taskDescription;
    MaterialButton saveButton;
    /** The system calls this to get the DialogFragment's layout, regardless
     of whether it's being displayed as a dialog or an embedded fragment. */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout to use as dialog or embedded fragment
        View view = inflater.inflate(R.layout.fragment_task_creation, container, false);
        Objects.requireNonNull(getDialog()).getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        saveButton = view.findViewById(R.id.task_save_button);
        taskName = view.findViewById(R.id.task_name_edit_text);
        taskDescription = view.findViewById(R.id.task_description_edit_text);

        saveButton.setOnClickListener(clickedView -> {
            if (!Objects.requireNonNull(taskName.getText()).toString().equals("")) {
                DatabaseHelper dbHelper = new DatabaseHelper(getContext());
                dbHelper.addTask(taskName.getText().toString(), Objects.requireNonNull(taskDescription.getText()).toString());
                dismiss();
                ((MainActivity) requireActivity()).readDataFromDB();
            } else {
                taskName.setError("Task Name is Required");
                taskName.requestFocus();
            }
        });

        return view;
    }

    /** The system calls this only when creating the layout in a dialog. */
    @NotNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // The only reason you might override this method when using onCreateView() is
        // to modify any dialog characteristics. For example, the dialog includes a
        // title by default, but your custom layout might not need it. So here you can
        // remove the dialog title, but you must call the superclass to get the Dialog.
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        return dialog;
    }
}
