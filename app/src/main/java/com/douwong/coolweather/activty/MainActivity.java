package com.douwong.coolweather.activty;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.douwong.coolweather.R;
import com.douwong.coolweather.db.MyDataBaseHelper;
import com.douwong.coolweather.service.MyService;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.tv)
    TextView mTv;
    @BindView(R.id.activity_main)
    RelativeLayout mActivityMain;
    MyDataBaseHelper myDataBaseHelper;



    private MyService.DownloadBinder mdownloadBinder;



    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceDisconnected(ComponentName name) {
        }
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mdownloadBinder = (MyService.DownloadBinder) service;
            mdownloadBinder.startDownload();
            mdownloadBinder.getProgress();
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        myDataBaseHelper = new MyDataBaseHelper(this, "BookStore.db", null, 1);

        mTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = myDataBaseHelper.getWritableDatabase();
            }
        });

    }


    public void btn1(View v){

        Intent intent = new Intent(this, MyService.class);

        bindService(intent,connection,BIND_AUTO_CREATE);


    }

    public void btn2(View v){
        unbindService(connection); //

    }





}
