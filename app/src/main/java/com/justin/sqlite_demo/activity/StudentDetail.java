package com.justin.sqlite_demo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.justin.sqlite_demo.bo.StudentRepo;
import com.justin.sqlite_demo.model.Student;

import SQLite_Demo.R;


public class StudentDetail extends AppCompatActivity implements View.OnClickListener {
    Button btnSave, btnDelete, btnClose;
    EditText editTextName, editTextEmail, editTextAge;
    private int _Student_Id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_detail);

        btnSave = (Button) findViewById(R.id.btnSave);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnClose = (Button) findViewById(R.id.btnClose);

        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextAge = (EditText) findViewById(R.id.editTextAge);

        btnSave.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnClose.setOnClickListener(this);

        Intent intent = getIntent();
        _Student_Id = intent.getIntExtra("studentID", 0);

        if (_Student_Id != 0) {
            StudentRepo repo = new StudentRepo(this);
            Student student = repo.getStudentById(_Student_Id);
            editTextAge.setText(String.valueOf(student.getAge()));
            editTextName.setText(student.getName());
            editTextEmail.setText(student.getEmail());
        }
    }


    public void onClick(View view) {
        if (view == findViewById(R.id.btnSave)) {
            StudentRepo repo = new StudentRepo(this);
            Student student = new Student(
                    _Student_Id,
                    editTextName.getText().toString(),
                    editTextEmail.getText().toString(),
                    Integer.parseInt(editTextAge.getText().toString()));

            if (_Student_Id == 0) {
                repo.insert(student);
                Toast.makeText(this, "Record Created", Toast.LENGTH_SHORT).show();
            } else {
                repo.update(student);
                Toast.makeText(this, "Record Updated", Toast.LENGTH_SHORT).show();
            }

            finish();

        } else if (view == findViewById(R.id.btnDelete)) {
            StudentRepo repo = new StudentRepo(this);
            repo.delete(_Student_Id);
            Toast.makeText(this, "Record Deleted", Toast.LENGTH_SHORT).show();
            finish();

        } else if (view == findViewById(R.id.btnClose)) {
            finish();
        }
    }
}
