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

public class ItemAdapter extends ArrayAdapter<Item> {

    Activity context;
    int resource;
    List<Item> items;

    public ItemAdapter(Activity context, int resource, List<Item> items){
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
        TextView txtName = view.findViewById(R.id.txtName);
        TextView txtPrice = view.findViewById(R.id.txtDonGia);
        EditText edtAmount = view.findViewById(R.id.edtAmount);

        Item item = items.get(position);
        txtName.setText(item.getName());
        txtPrice.setText(item.getPrice()+"");
        edtAmount.setText(item.getAmount()+"");
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try{
                    item.setAmount(Integer.parseInt(charSequence.toString()));
                }catch (Exception exception){
                    item.setAmount(0);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        };
        edtAmount.addTextChangedListener(textWatcher);

        return view;
    }
}
