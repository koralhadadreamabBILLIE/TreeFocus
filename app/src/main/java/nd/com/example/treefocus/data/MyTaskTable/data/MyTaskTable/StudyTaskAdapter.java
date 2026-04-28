package nd.com.example.treefocus.data.MyTaskTable.data.MyTaskTable;    import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import nd.com.example.treefocus.R;

/**
 * This is our "Chef" class. It adapts a list of StudyTask data into the RecyclerView.
 */
//The StudyTaskAdapter's job is to take a list of StudyTask data and create a visual, scrollable list of items from it.
// It's the bridge between your data and your UI.
public class StudyTaskAdapter extends RecyclerView.Adapter<StudyTaskAdapter.StudyTaskViewHolder> {

    // The list of all tasks that the adapter will manage.
    private List<StudyTask> tasks = new ArrayList<>(); //هذه قائمة تحتوي على جميع عناصر StudyTask التي يجب عرضها على الشاشة. وهي مصدر البيانات للمحول.

    /**
     * This method is called by the RecyclerView when it needs a new item on the list.
     * The LayoutInflater takes my XML layout for a single row, item_study_task.xml, and turns it into a View object in Java.
     * It then creates a new StudyTaskViewHolder (which I'll explain next) and passes that newly created View to it.
     * Essentially, its job is to build a brand new, empty list item."
     */
    @NonNull
    @Override
    public StudyTaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout for a single list item (our plate).
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_study_task, parent, false);
        return new StudyTaskViewHolder(itemView);
    }

    /**
     * يتم استدعاء هذه الطريقة بواسطة RecyclerView لعرض البيانات في موضع محدد.
     * This is where the chef puts the food (data) on the plate (ViewHolder).
     */
    @Override
    public void onBindViewHolder(@NonNull StudyTaskViewHolder holder, int position) {
        // Get the specific task for this row.
        StudyTask currentTask = tasks.get(position);

        // Use the holder to set the data on the views.
        holder.taskNameTextView.setText(currentTask.getTaskName());
        holder.studyTypeTextView.setText(currentTask.getTaskType());
    }

    /**
     * This method tells the RecyclerView how many total items are in our list.
     */
    @Override
    public int getItemCount() {
        return tasks.size();
    }

    /**
     * A helper method to update the list of tasks in the adapter from our Activity.
     * My StudyListActivity calls this method when it gets an updated list of tasks from the database.
     */
    public void setTasks(List<StudyTask> tasks) {
        this.tasks.clear();
        this.tasks.addAll(tasks);
        notifyDataSetChanged(); // Tell the RecyclerView to refresh itself.
    }

    /**
     * The ViewHolder class represents one single item in the list.
     * It holds the references to the individual views inside our item_study_task.xml layout.
     */
    class StudyTaskViewHolder extends RecyclerView.ViewHolder {
        private final TextView taskNameTextView;
        private final TextView studyTypeTextView;

        public StudyTaskViewHolder(@NonNull View itemView) {
            super(itemView);
            // Find the views within the item's layout.
            taskNameTextView = itemView.findViewById(R.id.textView_taskName);
            studyTypeTextView = itemView.findViewById(R.id.textView_studyType);
        }
    }
}
    