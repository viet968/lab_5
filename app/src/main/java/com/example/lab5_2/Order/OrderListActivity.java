package com.example.lab5_2.Order;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.example.lab5_2.R;
import com.example.lab5_2.databinding.ActivityOrderListBinding;

import java.util.ArrayList;

public class OrderListActivity extends AppCompatActivity {
    private ActivityOrderListBinding orderListBinding;
    OrderAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        orderListBinding = ActivityOrderListBinding.inflate(getLayoutInflater());
        setContentView(orderListBinding.getRoot());
        Intent getOrdersIntent = getIntent();
        ArrayList<Item> getOrders = (ArrayList<Item>) getOrdersIntent.getSerializableExtra("orders");
        int sumPrice = 0;
        if(getOrders!=null){
            adapter = new OrderAdapter(this,R.layout.order_item,getOrders);
            orderListBinding.orderList.setAdapter(adapter);
            for(Item cO : getOrders){
                sumPrice+=(cO.getPrice()*cO.getAmount());
            }
            orderListBinding.txtSumPrice.setText("Tổng tiền: "+sumPrice+"");
        }


    }

}