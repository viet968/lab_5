package com.example.lab5_2.Student;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.lab5_2.R;
import com.example.lab5_2.databinding.ActivityEditStudentBinding;

import java.util.Date;
import java.util.StringTokenizer;

public class EditStudentActivity extends AppCompatActivity {
    private  ActivityEditStudentBinding editStudentBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        editStudentBinding = ActivityEditStudentBinding.inflate(getLayoutInflater());
        setContentView(editStudentBinding.getRoot());
        int selectedYear = 2022;
        int selectedMonth = 11;
        int selectedDayOfMonth = 11;

        //        select date
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                editStudentBinding.txtStudentBirth.setText(i2+"/"+(i1+1)+"/"+i);
            }
        };

        Intent getIntent = getIntent();
        Student getStudent = (Student) getIntent.getSerializableExtra("student");
        if(getStudent!=null)
        editStudentBinding.edtStudentId.setText(getStudent.getId());
        editStudentBinding.edtStudentName.setText(getStudent.getName());
        editStudentBinding.edtStudentClass.setText(getStudent.getIdClass());
        editStudentBinding.txtStudentBirth.setText(getStudent.getBirth().getDate()+"/"+(getStudent.getBirth().getMonth()+1)+"/"+getStudent.getBirth().getYear());
        editStudentBinding.edtStudentPhai.setText(getStudent.isPhai() ? "Nam" : "Nữ");


        editStudentBinding.imgSelectBirthStudent.setOnClickListener( v->{
            View view = this.getCurrentFocus();
            if (view != null) {
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
            DatePickerDialog datePickerDialog = new DatePickerDialog(this,dateSetListener,
                    selectedYear, selectedMonth, selectedDayOfMonth);
            datePickerDialog.show();
        });

        editStudentBinding.btFinishEditStudent.setOnClickListener(v->{
            boolean newPhai = true;
            if(editStudentBinding.edtStudentPhai.getText().toString().trim().equalsIgnoreCase("nam")){
                newPhai = true;
            }else{
                newPhai = false;
            }
            StringTokenizer stk = new StringTokenizer(editStudentBinding.txtStudentBirth.getText().toString().trim(),"/");
            int getDate = Integer.parseInt(stk.nextToken());
            int getMonth = Integer.parseInt(stk.nextToken());
            int getYear = Integer.parseInt(editStudentBinding.txtStudentBirth.getText().toString().trim().substring(
                    editStudentBinding.txtStudentBirth.getText().toString().trim().length() - 4
            ));
            Student sendStudent = new Student(editStudentBinding.edtStudentId.getText().toString().trim(),
                    editStudentBinding.edtStudentName.getText().toString().trim(),
                    editStudentBinding.edtStudentClass.getText().toString().trim(),
                    newPhai,
                    new Date(getYear,getMonth,getDate));
//            Toast.makeText(this, stk.nextToken()+" - "+stk.nextToken()+"-"+editStudentBinding.txtStudentBirth.getText().toString().trim().substring(5), Toast.LENGTH_SHORT).show();
            setResult(Activity.RESULT_OK, new Intent().putExtra("editedStudent",sendStudent));
            finish();
        });
    }
}