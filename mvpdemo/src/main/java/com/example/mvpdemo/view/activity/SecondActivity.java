package com.example.mvpdemo.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.mvpdemo.R;
import com.example.mvpdemo.WheelViewUtil;
import com.lvfq.pickerview.TimePickerView;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn2third;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        btn2third = (Button) findViewById(R.id.btn2third);
        btn = (Button) findViewById(R.id.btn);
        btn2third.setOnClickListener(this);
        btn.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn:
                WheelViewUtil.alertTimerPicker(this, TimePickerView.Type.YEAR_MONTH_DAY, "yyyy-MM-dd", new WheelViewUtil.TimerPickerCallBack() {
                    @Override
                    public void onTimeSelect(String date) {
                        Toast.makeText(SecondActivity.this,date,Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case R.id.btn2third:
                startActivity(new Intent(this,ThirdActivity.class));
                break;
        }
    }
}
