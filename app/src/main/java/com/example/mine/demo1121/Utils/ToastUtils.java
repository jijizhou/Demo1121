package com.example.mine.demo1121.Utils;

import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.widget.Toast;

import com.example.mine.demo1121.App;

public class ToastUtils
{

    /**
     * 全局Context
     */
    private static Context mContext = App.getAppContext();
//    /**
//     * Toast
//     */
//    public static Toast mToast;
//
//    public static void show(String msg)
//    {
//        if (mToast == null) {
//
//        }
//    }

    /**
     * 单例吐司
     */
    private Toast toast;

    private static class child
    {
        final static ToastUtils INSTANCE = new ToastUtils();
    }

    public static ToastUtils getInstance()
    {
        return child.INSTANCE;
    }

    /**
     * 传入上下文对象
     *
     * @param context  当前提示消息
     * @param msg      吐司逗留时间
     * @param duration
     */
    public void showToast(Context context, String msg, int duration)
    {
        if (toast == null)
        {
            toast = Toast.makeText(context, msg, duration);
        } else
        {
            toast.cancel();// 关闭吐司显示
            toast = Toast.makeText(context, msg, duration);
        }
        toast.show();// 重新显示吐司
    }

    /**
     * 快捷toast
     */
    public static void show(String msg)
    {
        if (TextUtils.isEmpty(msg))
        {
            return;
        }
        //		ToastUtil.getInstance().showToast(ApplicationContext.getAppContext(), msg, Toast.LENGTH_SHORT);
        ToastUtils.getInstance().showPosition(mContext, msg, Gravity.CENTER);
    }

    public  void showPosition(Context context, String msg, int gravity)
    {
        if (toast == null)
        {
            toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        }else
        {
            toast.cancel();// 关闭吐司显示
            toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        }

        toast.setGravity(gravity, 0, 0);
        toast.show();
    }
}
