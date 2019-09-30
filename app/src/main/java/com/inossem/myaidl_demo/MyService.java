package com.inossem.myaidl_demo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class MyService extends Service {

    public final static String TAG = "MyService";


    public MyService() {
    }

    private IBinder binder = new IMyAidlInterface.Stub() {

        @Override
        public String aPulsB(String a, String b) throws RemoteException {
            Log.i(TAG , "service处理中");
            String s = a + "+" + b;

            return s ;
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreat");
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }
}
