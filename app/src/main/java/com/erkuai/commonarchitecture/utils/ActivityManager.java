package com.erkuai.commonarchitecture.utils;

import android.app.Activity;

import java.util.Stack;

/**
 * Created by Administrator on 2019/8/8.
 */

public class ActivityManager {

    public static Stack<Activity> activityStack;

    public static Stack<Activity> getActivityStack() {
        return activityStack;
    }

    public static void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<>();
        }
        activityStack.push(activity);
    }

    /**
     * 结束指定的Activity
     *
     * @param activity
     */
    public static void finishActivity(Activity activity) {
        if (activity != null && activityStack != null) {
            activityStack.remove(activity);
            activity.finish();
        }
    }


    /**
     * 结束指定类名的Activity
     *
     * @param cls
     */
    public static void finishActivity(Class<?> cls) {
        if (activityStack != null) {
            for (Activity activity : activityStack) {
                if (activity.getClass().equals(cls)) {
                    finishActivity(activity);
                    finishActivity(cls);
                }
            }
        }

    }
}
