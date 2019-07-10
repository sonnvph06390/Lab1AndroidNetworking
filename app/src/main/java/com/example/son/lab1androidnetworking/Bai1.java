package com.example.son.lab1androidnetworking;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.net.URL;

public class Bai1 extends AppCompatActivity implements View.OnClickListener {
    private ImageView iv1;
    private TextView tvMessage;
    private Button btnLoadImage1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai1);

        iv1 = (ImageView) findViewById(R.id.iv1);
        tvMessage = (TextView) findViewById(R.id.tvMessage);
        btnLoadImage1 = (Button) findViewById(R.id.btnLoadImage1);
        btnLoadImage1.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Thread myThread=new Thread(new Runnable() {
            @Override
            public void run() {
                final Bitmap  bitmap=loadImageFormNetwork("https://cdn.fptshop.com.vn/Uploads/Originals/2019/2/21/636863643187455627_ss-galaxy-s10-trang-1.png");
                iv1.post(new Runnable() {
                    @Override
                    public void run() {
                        tvMessage.setText("Image Downloaded");
                        iv1.setImageBitmap(bitmap);
                    }
                });
            }
        });
        myThread.start();
    }

    private Bitmap loadImageFormNetwork(String link) {
        URL url;
        Bitmap bitmap = null;
        try {
            url = new URL(link);
            bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }


}
