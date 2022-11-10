package com.example.lab5_2.WorkList;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.example.lab5_2.Interface.IWork;
import com.example.lab5_2.R;
import com.example.lab5_2.databinding.ActivityAddWorkBinding;

import java.util.Calendar;

public class AddWorkActivity extends AppCompatActivity {
    private ActivityAddWorkBinding addWorkBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addWorkBinding = ActivityAddWorkBinding.inflate(getLayoutInflater());
        setContentView(addWorkBinding.getRoot());

        int selectedYear = 2022;
        int selectedMonth = 11;
        int selectedDayOfMonth = 11;

        boolean is24HView = true;
        int selectedHour = 10;
        int selectedMinute = 20;

//        select date
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                    addWorkBinding.txtDate.setText(i2+"/"+(i1+1)+"/"+i);
            }
        };

//        select time
        TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {

            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                addWorkBinding.txtTime.setText(hourOfDay + ":" + minute );

            }
        };

        addWorkBinding.btAddWork.setText("Add");
        addWorkBinding.btAddWork.setOnClickListener(v ->{
            Work work = new Work(addWorkBinding.edtWorkName.getText().toString().trim(),
                    addWorkBinding.txtDate.getText().toString().trim(), addWorkBinding.txtTime.getText().toString().trim());
            setResult(Activity.RESULT_OK, new Intent().putExtra("wordAdded",work));
            finish();
        });

        addWorkBinding.imgSelectDate.setOnClickListener( v ->{
            View view = this.getCurrentFocus();
            if (view != null) {
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
            DatePickerDialog datePickerDialog = new DatePickerDialog(this,dateSetListener,
                    selectedYear, selectedMonth, selectedDayOfMonth);
            datePickerDialog.show();
        });

        addWorkBinding.imgSelectTime.setOnClickListener(v->{
            View view = this.getCurrentFocus();
            if (view != null) {
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
            // Create TimePickerDialog:
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    android.R.style.Theme_Holo_Light_Dialog_NoActionBar,
                    timeSetListener, selectedHour, selectedMinute, is24HView);

//           Show
            timePickerDialog.show();
        });



    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        addWorkBinding = null;
    }

    @Override
    public void onBackPressed() {
        setResult(Activity.RESULT_CANCELED);
        finish();
    }
}