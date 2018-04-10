package com.zjj.baselibrary.activity;

import android.app.Activity;

import java.util.Stack;

/**
 * Created by zhijinjin on 2018/3/28.
 */

public class ActivityManager {
    private static ActivityManager activityStackManager;

    public static ActivityManager getInstance() {
        if (activityStackManager == null) {
            activityStackManager = new ActivityManager();
        }
        return activityStackManager;
    }

    private ActivityManager() {
        activityStack = new Stack<>();
    }

    private Stack<Activity> activityStack;

    /**
     * 添加到堆栈
     * @param activity
     */
    public void push(Activity activity) {
        activityStack.push(activity);
    }

    /**
     * 移除堆栈顶部的对象，并作为此函数的值返回该对象
     * @return
     */
    public Activity pop() {
        return activityStack.pop();
    }

    public void remove(Activity activity) {
        activityStack.remove(activity);
    }

    /**
     * 关闭所有activity
     */
    public void finishAll() {
        while (!activityStack.isEmpty()) {
            activityStack.pop().finish();
        }
    }

    /**
     * 关闭 这个参数activity 之上的所有activity
     * @param activity
     */
    public void finishToActivity(Activity activity){
        while (!activityStack.isEmpty()) {
            if (activityStack.peek()==activity){
                break;
            }
            activityStack.pop().finish();
        }
    }

    public void finishNActivity(int n){
        int i=n;
        while (!activityStack.isEmpty() && i>0) {
            activityStack.pop().finish();
            i--;
        }
    }

    /**
     * 关闭除了最后一个的所有activity
     */
    public void finishUntilLast() {
        if (!activityStack.isEmpty()) {
            Activity activity = activityStack.pop();
            finishAll();
            push(activity);
        }
    }

    public Stack<Activity> getActivityStack() {
        return activityStack;
    }
}
