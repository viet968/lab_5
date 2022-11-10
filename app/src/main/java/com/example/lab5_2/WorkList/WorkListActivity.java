package com.example.lab5_2.WorkList;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.lab5_2.Interface.IWork;
import com.example.lab5_2.R;
import com.example.lab5_2.databinding.ActivityWorkListBinding;

import java.util.ArrayList;
import java.util.List;

public class WorkListActivity extends AppCompatActivity implements IWork {
    private ActivityWorkListBinding workListBinding;
    private List<Work> works;
    private ArrayAdapter adapter;
    private int requestAdd_code = 123;
    private int requestEdit_code = 456;
    private int selectedIndex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        workListBinding = ActivityWorkListBinding.inflate(getLayoutInflater());
        setContentView(workListBinding.getRoot());
        init();
        adapter = new ArrayAdapter(WorkListActivity.this, android.R.layout.simple_expandable_list_item_1,works);
        workListBinding.workList.setAdapter(adapter);

        // add work
        workListBinding.fabAddWork.setOnClickListener(v ->{
            Intent intent = new Intent(WorkListActivity.this,AddWorkActivity.class);
            startActivityForResult(intent,requestAdd_code);
        });

        // edit work
        workListBinding.workList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectedIndex = i;
                Intent intent = new Intent(WorkListActivity.this,EditWorkActivity.class);
                intent.putExtra("work",works.get(i));
                startActivityForResult(intent,requestEdit_code);
            }
        });
//        remove work
        workListBinding.workList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                AlertDialog.Builder builder =  new AlertDialog.Builder(WorkListActivity.this);
//          Thiết lập tiêu đề, nội dung thông báo, icon
                builder.setTitle("Xác nhận xóa");
                builder.setMessage("Bạn có chắc muốn xóa nội dung này");
                builder.setIcon(android.R.drawable.ic_dialog_alert);
//          Hiển thị thêm Button để điều khiển
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteWork(i);
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

                return true;
            }
        });


    }

    private void init(){
        works = new ArrayList<>();
        works.add(new Work("Work 1","09/11/2022","07:00"));
        works.add(new Work("Work 2","01/11/2022","13:00"));
        works.add(new Work("Work 3","03/11/2022","08:00"));
        works.add(new Work("Work 4","05/11/2022","10:00"));
        works.add(new Work("Work 5","08/11/2022","17:00"));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == requestAdd_code){
            if(resultCode == Activity.RESULT_OK){
                Work receiveWork = (Work) data.getSerializableExtra("wordAdded");
                addWork(receiveWork);
            }
        }else if(requestCode == requestEdit_code) {
            if (resultCode == Activity.RESULT_OK) {
                Work receiveWork = (Work) data.getSerializableExtra("wordAdded");
                editWork(receiveWork,selectedIndex);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        workListBinding = null;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void addWork(Work work) {
        works.add(work);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void editWork(Work work,int index) {
        works.set(index,work);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void deleteWork(int index) {
        works.remove(index);
        adapter.notifyDataSetChanged();
    }
}