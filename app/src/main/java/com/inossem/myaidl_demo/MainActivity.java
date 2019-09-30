package com.inossem.myaidl_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    public final static String TAG = "MainActivity";
    private IMyAidlInterface myInterface;
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myInterface = IMyAidlInterface.Stub.asInterface(service);
            Log.i(TAG, "连接Service 成功");  //  和 service 是两个进程  log日志也在不同进程
            try {
                String s = myInterface.aPulsB("1", "2");
                Log.i(TAG, "activity获得的service处理完的结果：" + s);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.e(TAG, "连接Service失败");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startAndBindService();
    }

    private void startAndBindService() {
        Intent service = new Intent(MainActivity.this, MyService.class);
        //startService(service);
        bindService(service, serviceConnection, Context.BIND_AUTO_CREATE);
        //Context.BIND_AUTO_CREATE的意思就是说：如果绑定Service的时候，Service还没有被创建，就创建它。
    }
}
