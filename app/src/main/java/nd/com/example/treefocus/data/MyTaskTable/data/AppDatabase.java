package nd.com.example.treefocus.data.MyTaskTable.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import nd.com.example.treefocus.data.MyTaskTable.data.MyTaskTable.StudyTask;
import nd.com.example.treefocus.data.MyTaskTable.data.MyTaskTable.StudyTaskQuery;
import nd.com.example.treefocus.data.MyTaskTable.data.MyUserTable.Student;
import nd.com.example.treefocus.data.MyTaskTable.data.MyUserTable.StudentQuery;
import nd.com.example.treefocus.signupscreen;

@Database(entities = {Student.class, StudyTask.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract StudyTaskQuery studyTaskQuery();

    private static AppDatabase dp;

    public static AppDatabase getDatabase(Context context)
    {
        if (dp == null)
        {
            dp = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "koral_database").build();
        }
        return dp;
    }

    // This method is now unused and can be removed for clarity.
    // public static AppDatabase getDatabase(signupscreen signupscreen) {
    //     if (dp == null) {
    //         dp = Room.databaseBuilder(signupscreen.getApplicationContext(), AppDatabase.class, "app_database").build();
    //     }
    //     return dp;
    // }


    public abstract StudentQuery getStudentQuery(); // Keep this one

    // By adding public abstract StudentQuery studentQuery();, you are telling AppDatabase that it is responsible for providing access to the StudentQuery DAO.
    // public abstract StudentQuery studentQuery(); // REMOVE THIS LINE

    public static AppDatabase getInstance(Context context) {
        if (dp == null) {
            // It's recommended to enable fallback to destructive migration for development
            // and to use a more robust migration strategy for production apps.
            dp = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "app_database")
                    .fallbackToDestructiveMigration().allowMainThreadQueries() // Optional: but helpful during development
                    .build();
        }
        return dp;
    }
// In AppDatabase.java



}



