package com.example.son.lab1androidnetworking;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Bai3 extends AppCompatActivity implements View.OnClickListener,Listener {
    private ImageView iv3;
    private TextView tvMessage3;
    private Button btnLoadImage3;
    public static final String IMAGE_URL="https://www.notebookcheck.biz/fileadmin/_processed_/a/d/csm_IMG_0670_d35a7443f8.jpg";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai3);

        iv3 = (ImageView) findViewById(R.id.iv3);
        tvMessage3 = (TextView) findViewById(R.id.tvMessage3);
        btnLoadImage3 = (Button) findViewById(R.id.btnLoadImage3);
        btnLoadImage3.setOnClickListener(this);
    }

    @Override
    public void onClick(View arg0) {
        switch (arg0.getId()){
            case R.id.btnLoadImage3:
                new LoadImageTask((Listener) this,this).execute(IMAGE_URL);
                break;
        }
    }

    @Override
    public void onImageLoaded(Bitmap bitmap) {
        iv3.setImageBitmap(bitmap);
        tvMessage3.setText("Image Downloaded");
    }

    @Override
    public void onError() {
        tvMessage3.setText("Error download image");

    }
//


}
