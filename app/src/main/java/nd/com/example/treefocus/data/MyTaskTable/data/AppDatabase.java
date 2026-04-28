// الغرض من هذه class هو إنشاء وإدارة قاعدة البيانات الفعلية على الهاتف.
// it connects all my tables (entities) and all my database rules (DAOs)
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
public abstract class AppDatabase extends RoomDatabase {  //يخبر هذا Room بأن هذه الفئة هي التكوين الرئيسي لقاعدة البيانات.
    // entities = {Student.class, StudyTask.class}: This part tells Room all the tables that should be included in this database. I have a Student table and a StudyTask table.
    //extends RoomDatabase: This is a requirement for any Room database class. It must inherit from RoomDatabase.
    public abstract StudyTaskQuery studyTaskQuery();
    // These abstract methods are how the rest of my app gets access to the DAOs

    private static AppDatabase dp;

    public static AppDatabase getDatabase(Context context) //its purpose is to make sure that only one instance of the database is ever created in the entire app.
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


    public abstract StudentQuery getStudentQuery(); //gives the DAO for student table

    // By adding public abstract StudentQuery studentQuery();, you are telling AppDatabase that it is responsible for providing access to the StudentQuery DAO.

    public static AppDatabase getInstance(Context context) //: Your getInstance() method is doing the exact same job as your getDatabase() method.
    {
        if (dp == null) {
            dp = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "app_database")
                    .fallbackToDestructiveMigration().allowMainThreadQueries() // Optional: but helpful during development
                    .build();
        }
        return dp; //Purpose: Creates a database named "koral_database" if one doesn't exist, then returns it.
    }
// In AppDatabase.java



}



