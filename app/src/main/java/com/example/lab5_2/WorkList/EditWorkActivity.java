package com.example.lab5_2.WorkList;

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

import androidx.appcompat.app.AppCompatActivity;

import com.example.lab5_2.databinding.ActivityAddWorkBinding;

public class EditWorkActivity extends AppCompatActivity {
    private ActivityAddWorkBinding editWorkBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        editWorkBinding = ActivityAddWorkBinding.inflate(getLayoutInflater());
        setContentView(editWorkBinding.getRoot());

        int selectedYear = 2022;
        int selectedMonth = 11;
        int selectedDayOfMonth = 11;

        boolean is24HView = true;
        int selectedHour = 10;
        int selectedMinute = 20;

        Intent getIntent = getIntent();
        Work getWork = (Work) getIntent.getSerializableExtra("work");

        editWorkBinding.edtWorkName.setText(getWork.getName());
        editWorkBinding.txtDate.setText(getWork.getDate());
        editWorkBinding.txtTime.setText(getWork.getTime());
        editWorkBinding.btAddWork.setText("Ok");

        //        select date
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                editWorkBinding.txtDate.setText(i2+"/"+(i1+1)+"/"+i);
            }
        };

//        select time
        TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {

            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                editWorkBinding.txtTime.setText(hourOfDay + ":" + minute );

            }
        };

        editWorkBinding.imgSelectDate.setOnClickListener( v ->{
            View view = this.getCurrentFocus();
            if (view != null) {
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
            DatePickerDialog datePickerDialog = new DatePickerDialog(this,dateSetListener,
                    selectedYear, selectedMonth, selectedDayOfMonth);
            datePickerDialog.show();
        });

        editWorkBinding.imgSelectTime.setOnClickListener(v->{
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

        // ok button event
        editWorkBinding.btAddWork.setOnClickListener(v ->{
            Work work = new Work(editWorkBinding.edtWorkName.getText().toString().trim(),
                    editWorkBinding.txtDate.getText().toString().trim(), editWorkBinding.txtTime.getText().toString().trim());
            setResult(Activity.RESULT_OK, new Intent().putExtra("wordAdded",work));
            finish();
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        editWorkBinding = null;
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
