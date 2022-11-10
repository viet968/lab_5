package com.example.lab5_2.Order;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.lab5_2.R;
import com.example.lab5_2.databinding.ActivityItemCartBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ItemCartActivity extends AppCompatActivity {
    private ActivityItemCartBinding itemCartBinding;
    private List<Item> items;
    private ItemAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        itemCartBinding = ActivityItemCartBinding.inflate(getLayoutInflater());
        setContentView(itemCartBinding.getRoot());

        init();
        generateData();

        itemCartBinding.fabAddItem.setOnClickListener(v->{
            ArrayList<Item> orders = new ArrayList<>();
            for(Item cI : items){
                if(cI.getAmount() > 0){
                    orders.add(cI);
                }
            }

            startActivity(new Intent(this,OrderListActivity.class).putExtra("orders",orders));
        });
    }

    private void init(){
        items = new ArrayList<>();
        adapter = new ItemAdapter(this,R.layout.cart_item,items);
        itemCartBinding.itemList.setAdapter(adapter);
    }

    private void generateData(){
        Random rd = new Random();
        for(int i=1;i<20;i++){
            items.add(new Item("Item "+i, rd.nextInt(10000),0));
        }
        adapter.notifyDataSetChanged();
    }
}