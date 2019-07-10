package com.example.son.lab1androidnetworking;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;

public class AsyncTaskRunner extends AsyncTask<String, String, String> {
    private String resp;
    ProgressDialog progressDialog;
    Context context;
    private EditText edTime;
    private TextView tvResult;

    public AsyncTaskRunner(Context context, TextView tvResult, EditText edTime) {
        this.context = context;
        this.edTime = edTime;
        this.tvResult = tvResult;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog=ProgressDialog.show(context,"ProgressDialog","Wait for:"+edTime.getText().toString()+"seconds");

    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (progressDialog.isShowing()){
            progressDialog.dismiss();
        }
        tvResult.setText(s);
    }

    @Override
    protected String doInBackground(String... strings) {
        publishProgress("Sleeping.....");
        try{
            int time=Integer.parseInt(strings[0])*1000;
            Thread.sleep(time);

        }catch (Exception e){
            e.printStackTrace();
            resp=e.getMessage();
        }
        return resp;
    }
}
