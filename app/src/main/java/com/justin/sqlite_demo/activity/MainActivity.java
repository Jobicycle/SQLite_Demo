package com.justin.sqlite_demo.activity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.justin.sqlite_demo.bo.StudentRepo;

import java.util.ArrayList;
import java.util.HashMap;

import SQLite_Demo.R;


public class MainActivity extends ListActivity implements View.OnClickListener {
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        if (view == findViewById(R.id.btnAdd)) {
            Intent intent = new Intent(this, StudentDetail.class);
            intent.putExtra("studentID", 0);
            startActivity(intent);
        }
    }


    private void buildStudentList() {
        StudentRepo repo = new StudentRepo(this);
        ArrayList<HashMap<String, String>> studentList = repo.getStudentList();

        if (studentList.size() > 0) {
            ListView lv = getListView();

            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    TextView tvStudentID = (TextView) view.findViewById(R.id.student_Id);
                    int studentID = Integer.parseInt(tvStudentID.getText().toString());
                    Intent intent = new Intent(getApplicationContext(), StudentDetail.class);
                    intent.putExtra("studentID", studentID);
                    startActivity(intent);
                }
            });

            ListAdapter adapter = new SimpleAdapter(
                    this,
                    studentList,
                    R.layout.view_student_entry,
                    new String[]{"id", "name"},
                    new int[]{R.id.student_Id, R.id.student_name});
            setListAdapter(adapter);

        } else {
            setListAdapter(null);
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        this.buildStudentList();
    }
}