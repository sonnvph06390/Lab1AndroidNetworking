package com.example.son.lab1androidnetworking;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Bai4 extends AppCompatActivity implements View.OnClickListener {
    private EditText edTime;
    private Button btnRun;
    private TextView tvResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai4);


        edTime = (EditText) findViewById(R.id.edTime);
        btnRun = (Button) findViewById(R.id.btnRun);
        tvResult=findViewById(R.id.tvResult);
        btnRun.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnRun:
                AsyncTaskRunner asyncTaskRunner=new AsyncTaskRunner(this,tvResult,edTime);
                String sleepTime=edTime.getText().toString();
                asyncTaskRunner.execute(sleepTime);
                break;
        }

    }
}
