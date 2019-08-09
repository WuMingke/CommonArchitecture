package com.erkuai.commonarchitecture.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.TextView;
import android.widget.Toast;

import com.erkuai.commonarchitecture.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2019/8/9.
 */

public class Utils {

    public static <T> ObservableTransformer<T, T> rx2SchedulerHelperO() {    //compose简化线程
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (info != null && info.isConnected()) {
                // 当前网络是连接的
                if (info.getState() == NetworkInfo.State.CONNECTED) {
                    // 当前所连接的网络可用
                    return true;
                }
            }
        }
        return false;
    }

    public static void showToast(Context context, String msg) {
        Toast toast = new Toast(context);
        toast.setGravity(Gravity.CENTER, 0, 0);
        TextView textView = (TextView) LayoutInflater.from(context).inflate(R.layout.diy_toast, null);
        textView.setText(msg);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(textView);
        toast.show();
    }

    public static void saveData(Context context, String data, String folder, String name) {
        if (data == null) return;
        //sd卡检测
        String sdStatus = Environment.getExternalStorageState();
        if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) {
            showToast(context, "SD 卡不可用");
            return;
        }
        //检测文件夹是否存在
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + folder);
        if (!file.exists()) {
            file.mkdirs();
        }
        FileOutputStream outputStream = null;
        try {
            //创建文件，并写入内容
            outputStream = new FileOutputStream(new File(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + folder + File.separator + name));
            outputStream.write(data.getBytes("UTF-8"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static String readData(String folder, String name) {
        byte Buffer[] = new byte[1024];
        //得到文件输入流
        FileInputStream in = null;
        ByteArrayOutputStream outputStream = null;
        try {
            in = new FileInputStream(new File(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + folder + File.separator + name));
            //读出来的数据首先放入缓冲区，满了之后再写到字符输出流中
            int len = in.read(Buffer);
            if (len != -1) {
                //创建一个字节数组输出流
                outputStream = new ByteArrayOutputStream();
                outputStream.write(Buffer, 0, len);
                //把字节输出流转String
                return new String(outputStream.toByteArray());
            } else {
                return null;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.flush();
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
