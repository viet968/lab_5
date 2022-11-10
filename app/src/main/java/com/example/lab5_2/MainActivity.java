package com.example.lab5_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.lab5_2.Order.ItemCartActivity;
import com.example.lab5_2.Student.StudentListActivity;
import com.example.lab5_2.WorkList.WorkListActivity;
import com.example.lab5_2.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding mainBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());

        mainBinding.btWorkList.setOnClickListener(v ->{
            startActivity(new Intent(this, WorkListActivity.class));
        });

        mainBinding.btSale.setOnClickListener(v ->{
            startActivity(new Intent(this, ItemCartActivity.class));
        });

        mainBinding.btStudentMana.setOnClickListener( v->{
            startActivity(new Intent(this, StudentListActivity.class));
        });
    }
}