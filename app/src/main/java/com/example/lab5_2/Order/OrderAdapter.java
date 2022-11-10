package com.example.lab5_2.Order;

import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.lab5_2.R;

import java.util.List;

public class OrderAdapter extends ArrayAdapter<Item> {
    Activity context;
    int resource;
    List<Item> items;

    public OrderAdapter(Activity context, int resource, List<Item> items){
        super(context,resource,items);
        this.context = context;
        this.resource = resource;
        this.items = items;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        View view = inflater.inflate(this.resource,null);
        TextView txtName = view.findViewById(R.id.txtOrderName);
        TextView txtPrice = view.findViewById(R.id.txtOrderPrice);
        TextView txtAmount = view.findViewById(R.id.txtAmountItem);
        TextView txtTotalPrice = view.findViewById(R.id.txtTotalPrice);

        Item item = items.get(position);
        txtName.setText("Tên hàng: "+item.getName());
        txtPrice.setText("Đơn giá: "+item.getPrice());
        txtAmount.setText("Số lượng: "+item.getAmount());
        txtTotalPrice.setText("Thành tiền: "+item.getPrice()*item.getAmount());

        return view;
    }
}
