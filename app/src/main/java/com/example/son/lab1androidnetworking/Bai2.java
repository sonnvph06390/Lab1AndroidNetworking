package com.example.son.lab1androidnetworking;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Bai2 extends AppCompatActivity implements View.OnClickListener {
    private ImageView iv2;
    private TextView tvMessage2;
    private Button btnLoadImage2;
    ProgressDialog progressDialog;
    String url = "https://ss7.vzw.com/is/image/VerizonWireless/SamsungGalaxyS10_Pink?$device-lg$";
    Bitmap bitmap = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai2);


        iv2 = (ImageView) findViewById(R.id.iv2);
        tvMessage2 = (TextView) findViewById(R.id.tvMessage2);
        btnLoadImage2 = (Button) findViewById(R.id.btnLoadImage2);
        btnLoadImage2.setOnClickListener(this);
    }

    private Handler messageHandle = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle bundle = msg.getData();
            String message = bundle.getString("message");
            tvMessage2.setText(message);
            iv2.setImageBitmap(bitmap);
            progressDialog.dismiss();
        }
    };

    @Override
    public void onClick(View view) {
        progressDialog = ProgressDialog.show(this, "", "Downloading...");
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                bitmap =downloadBitmap(url);
                Message message=messageHandle.obtainMessage();
                Bundle bundle=new Bundle();
                String threadMessage="Image downloaded";
                bundle.putString("message", threadMessage);
                message.setData(bundle);
                messageHandle.sendMessage(message);
            }

        };
        Thread thread=new Thread(runnable);
        thread.start();

    }

    private Bitmap downloadBitmap(String link) {
        try {
            URL url=new URL(link);
            HttpURLConnection connection= (HttpURLConnection) url.openConnection();
            connection.connect();
            InputStream inputStream=connection.getInputStream();
            Bitmap bitmap= BitmapFactory.decodeStream(inputStream);
            return bitmap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
