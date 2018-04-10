package com.zjj.baselibrary.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;


/**
 * 普通父类，无网络请求，留作使用 RxJava进行网络请求，网络请求使用MyOkHttp
 * Created by zhijinjin on 2018/3/28.
 */
public class BaseActivity extends AppCompatActivity {

    /*
     * 解决Vector兼容性问题
     * http://www.jianshu.com/p/e3614e7abc03
     */
    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }
    private boolean hasInit = false;

    //跳转封装
    private static void start(Context context, Class<?> clazz, Bundle bundle){
        if (context != null && BaseActivity.class.isAssignableFrom(clazz)) {
            context.startActivity(newIntent(clazz, context, bundle));
        }else{
            // context.startActivity(newIntent(clazz, context, bundle));
        }
    }

    public static void start(Activity activity, Class<?> clazz, Bundle bundle){
        start((Context) activity, clazz, bundle);
    }

    public static void start(Fragment fragment, Class<?> clazz,  Bundle bundle){
        if (fragment != null) {
            start((Context) fragment.getActivity(), clazz, bundle);
        }
    }

    private static Intent newIntent(Class<?> clazz, Context context, Bundle bundle) {
        Intent intent = new Intent(context, clazz);
        if (bundle!=null)
            intent.putExtras(bundle);
        return intent;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        if (!hasInit) {
            ActivityManager.getInstance().push(this);
            hasInit = true;
        }
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!hasInit) {
            ActivityManager.getInstance().push(this);
            hasInit = true;
        }
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    public Bundle getBundle(){
        return getIntent().getExtras();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityManager.getInstance().remove(this);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
    }

}
