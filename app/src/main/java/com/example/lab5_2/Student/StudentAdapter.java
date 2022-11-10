package com.example.lab5_2.Student;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.lab5_2.Interface.IStudent;
import com.example.lab5_2.R;

import java.util.List;

public class StudentAdapter extends ArrayAdapter<Student> {

    Activity context;
    int resource;
    List<Student> items;
    IStudent listener;

    public StudentAdapter(Activity context, int resource, List<Student> items){
        super(context,resource,items);
        this.context = context;
        this.resource = resource;
        this.items = items;
    }


    public void setListener(IStudent listener){
        this.listener = listener;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        View view = inflater.inflate(this.resource,null);
        TextView txtStudentID = view.findViewById(R.id.txtStudentId);
        TextView txtStudentName = view.findViewById(R.id.txtStudentName);
        TextView txtBirth = view.findViewById(R.id.txtBirth);
        TextView txtClass = view.findViewById(R.id.txtClass);
        TextView txtPhai = view.findViewById(R.id.txtPhai);
        CheckBox cbPhai = view.findViewById(R.id.cbPhai);
        Button btEdit = view.findViewById(R.id.btEditStudent);
        Button btRemove = view.findViewById(R.id.btRemoveStudent);

        Student item = items.get(position);
        txtStudentID.setText("Mã: "+item.getId());
        txtStudentName.setText("Tên: "+item.getName());
        txtBirth.setText("Ngày sinh: "+item.getBirth().getDate()+"/"+(item.getBirth().getMonth()+1)+"/"+item.getBirth().getYear());
        txtClass.setText("Lớp: "+item.getIdClass());
        txtPhai.setText(item.isPhai() ? "Nam" : "Nữ");

        btEdit.setOnClickListener(v ->{
            listener.editStudent(item,position);
        });

        btRemove.setOnClickListener(v->{
            listener.deleteStudent(position);
        });


        return view;
    }
}
