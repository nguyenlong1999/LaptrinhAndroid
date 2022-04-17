package com.example.droidcafe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class OrderActivity extends AppCompatActivity {
    RadioGroup rgDelivery;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        setTitle("Đặt hàng");

        rgDelivery = findViewById(R.id.rgDelivery);
        spinner = findViewById(R.id.spinner_phone);

        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        TextView tvGioHang = findViewById(R.id.order_textView);
        tvGioHang.setText(message);

        rgDelivery.setOnCheckedChangeListener(
                new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int id) {
                        switch (id) {
                            case R.id.sameday:
                                showMessage(R.string.sameday);
                                break;
                            case R.id.nextday:
                                showMessage(R.string.nextday);
                                break;
                            case R.id.pickup:
                                showMessage(R.string.pickup);
                                break;
                        }
                    }
                }
        );


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.labels_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long key) {
                        List<String> listPhoneTypes = Arrays.asList(
                                getResources().getStringArray(R.array.labels_array)
                        );

                        Toast.makeText(OrderActivity.this, listPhoneTypes.get(i), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                }
        );
    }


    public void showMessage(int idMessage) {
        Toast.makeText(this, getString(idMessage), Toast.LENGTH_SHORT).show();
    }
}