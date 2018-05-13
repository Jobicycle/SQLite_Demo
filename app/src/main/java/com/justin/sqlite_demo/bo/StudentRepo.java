package com.justin.sqlite_demo.bo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.justin.sqlite_demo.model.Student;
import com.justin.sqlite_demo.utils.DBHelper;

import java.util.ArrayList;
import java.util.HashMap;


public class StudentRepo {
    private DBHelper dbHelper;

    public StudentRepo(Context context) {
        dbHelper = new DBHelper(context);
    }


    /**
     * Insert a new student record
     *
     * @param student
     * @return
     */
    public int insert(Student student) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Student.KEY_NAME, student.getName());
        values.put(Student.KEY_EMAIL, student.getEmail());
        values.put(Student.KEY_AGE, student.getAge());

        // Inserting Row
        long student_Id = db.insert(Student.TABLE, null, values);
        db.close();
        return (int) student_Id;
    }


    /**
     * Delete a student record
     *
     * @param student_Id
     */
    public void delete(int student_Id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(Student.TABLE, Student.KEY_ID + "= ?", new String[]{String.valueOf(student_Id)});
        db.close();
    }


    /**
     * Update a student record
     *
     * @param student
     */
    public void update(Student student) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Student.KEY_NAME, student.getName());
        values.put(Student.KEY_EMAIL, student.getEmail());
        values.put(Student.KEY_AGE, student.getAge());

        db.update(Student.TABLE, values, Student.KEY_ID + "= ?",
                new String[]{String.valueOf(student.getStudent_ID())});
        db.close();
    }


    /**
     * Get a hashmap of id/name from all students
     *
     * @return
     */
    public ArrayList<HashMap<String, String>> getStudentList() {
        SQLiteDatabase db = dbHelper.getReadableDatabase(); //Open connection to read only
        String selectQuery = "SELECT  " +
                Student.KEY_ID + "," +
                Student.KEY_NAME +
                " FROM " + Student.TABLE;

        ArrayList<HashMap<String, String>> studentList = new ArrayList<>();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> student = new HashMap<String, String>();
                student.put("id", cursor.getString(cursor.getColumnIndex(Student.KEY_ID)));
                student.put("name", cursor.getString(cursor.getColumnIndex(Student.KEY_NAME)));
                studentList.add(student);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return studentList;
    }


    /**
     * Get a specific student by ID
     *
     * @param Id
     * @return
     */
    public Student getStudentById(int Id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Student student = new Student();

        String selectQuery = "SELECT  " +
                Student.KEY_ID + "," +
                Student.KEY_NAME + "," +
                Student.KEY_EMAIL + "," +
                Student.KEY_AGE +
                " FROM " + Student.TABLE + "" +
                " WHERE " + Student.KEY_ID + "=?";

        Cursor cursor = db.rawQuery(selectQuery, new String[]{String.valueOf(Id)});

        if (cursor.moveToFirst()) {
            do {
                student = new Student(
                        cursor.getInt(cursor.getColumnIndex(Student.KEY_ID)),
                        cursor.getString(cursor.getColumnIndex(Student.KEY_NAME)),
                        cursor.getString(cursor.getColumnIndex(Student.KEY_EMAIL)),
                        cursor.getInt(cursor.getColumnIndex(Student.KEY_AGE)));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return student;
    }
}