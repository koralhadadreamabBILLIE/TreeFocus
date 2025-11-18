package nd.com.example.treefocus.data.MyTaskTable.data.MyUserTable;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import nd.com.example.treefocus.data.MyTaskTable.data.MyUserTable.Student;

/**
 * واجهة تحوي عمليات\دوال\استعلامات على قاعدة البيانات
 */
@Dao//لتحديد ان الواجهخة تحوي استعلامات على قاعدة بايانات
public interface StudentQuery
{   //استخراج جميع المستعملين
    @Query("SELECT * FROM Student")
    List<Student> getAll();
    // استخراج مستعمل حسب رقم المميز لهid
    @Query("SELECT * FROM Student WHERE keyid IN (:userIds)")
    List<Student> loadAllByIds(int[] userIds);
    //هل المستعمل موجود حسب الايميل وكلمة السر
    @Query("SELECT * FROM Student WHERE email = :myEmail AND passw = :myPassw LIMIT 1")
    Student checkEmailPassw(String myEmail, String myPassw);
    //فحص هل الايميل موجود من قبل
    @Query("SELECT * FROM Student WHERE email = :myEmail LIMIT 1")
    Student checkEmail(String myEmail);
    @Insert
// اضافة مستعمل او مجموعة مستعملين
    void insertAll(Student... users);
    @Delete
// حذف
    void delete(Student user);
    //حذف حسب الرقم المميز id
    @Query("Delete From Student WHERE keyid=:id ")
    void delete(int id);
    @Insert//اضافة مستعمل واحد
    void insert(Student myUser);
    @Update
//تعديل مستعمل او قائمة مستعملين
    void update(Student...values);
}

