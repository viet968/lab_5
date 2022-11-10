package com.example.lab5_2.Student;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.example.lab5_2.Interface.IStudent;
import com.example.lab5_2.R;
import com.example.lab5_2.WorkList.WorkListActivity;
import com.example.lab5_2.databinding.ActivityStudentListBinding;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StudentListActivity extends AppCompatActivity implements IStudent {
    private ActivityStudentListBinding studentListBinding;
    private List<Student> students;
    private StudentAdapter adapter;
    private int request_edit_code = 123;
    private int selectedIndex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        studentListBinding = ActivityStudentListBinding.inflate(getLayoutInflater());
        setContentView(studentListBinding.getRoot());
        generateData();
        init();

    }

    private void generateData(){
        students = new ArrayList<>();
        students.add(new Student("DH13579986","Trần Văn Tèo","D99_TH09", true, new Date(2099,12,12)));
        students.add(new Student("DH13579986","Trần Văn A","D90_TH00", false, new Date(2099,12,12)));
        students.add(new Student("DH13579986","Trần Văn B","D97_TH07", true, new Date(2099,12,12)));
        students.add(new Student("DH13579986","Trần Văn C","D96_TH06", false, new Date(2099,12,12)));
        students.add(new Student("DH13579986","Trần Văn D","D95_TH05", true, new Date(2099,12,12)));
        students.add(new Student("DH13579986","Trần Văn E","D94_TH04", true, new Date(2099,12,12)));
        students.add(new Student("DH13579986","Trần Văn F","D93_TH03", false, new Date(2099,12,12)));
        students.add(new Student("DH13579986","Trần Văn G","D92_TH02", true, new Date(2099,12,12)));
        students.add(new Student("DH13579986","Trần Văn H","D91_TH01", true, new Date(2099,12,12)));
        students.add(new Student("DH13579986","Trần Văn I","D100_TH10", false, new Date(2099,12,12)));
        students.add(new Student("DH13579986","Trần Văn K","D99_TH11", false, new Date(2099,12,12)));
    }
    private void init(){
        adapter = new StudentAdapter(this,R.layout.student_item,students);
        adapter.setListener(this);
        studentListBinding.studentList.setAdapter(adapter);
    }

    @Override
    public void editStudent(Student s, int index) {
        Intent intent = new Intent(StudentListActivity.this, EditStudentActivity.class);
        intent.putExtra("student",s);
        selectedIndex = index;
        startActivityForResult(intent,request_edit_code);
    }

    @Override
    public void deleteStudent(int index) {
        AlertDialog.Builder builder =  new AlertDialog.Builder(StudentListActivity.this);
//          Thiết lập tiêu đề, nội dung thông báo, icon
        builder.setTitle("Xác nhận xóa");
        builder.setMessage("Bạn có chắc muốn xóa nội dung này");
        builder.setIcon(android.R.drawable.ic_dialog_alert);
//          Hiển thị thêm Button để điều khiển
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                students.remove(index);
                adapter.notifyDataSetChanged();
            }
        });
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        // Khởi tạo AlertDialog
        AlertDialog dialog = builder.create();
// Không cho tắt bằng cách nhấn ở ngoài AlertDialog
        dialog.setCanceledOnTouchOutside(false);
// Hiển thị AlertDialog
        dialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == request_edit_code){
            if(resultCode == Activity.RESULT_OK){
                Student receiveStudent = (Student) data.getSerializableExtra("editedStudent");
                students.set(selectedIndex,receiveStudent);
                adapter.notifyDataSetChanged();
            }
        }
    }
}