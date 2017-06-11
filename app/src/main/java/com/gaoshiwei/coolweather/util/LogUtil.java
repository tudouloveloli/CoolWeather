package com.gaoshiwei.coolweather.util;

import android.util.Log;

/**
 * Created by gaoshiwei on 2017/5/22.
 */


/**
 * 自定义的日志工具类 LogUtil 。本项目并未使用。
 *
 *  LogUtil 中先是定义了 VERBOSE、DEBUG、INFO、WARN、ERROR、NOTHING 这 6 个整型常量，并且它们对应的值都是递增的。
 *  然后又定义了一个静态变量 level，可以将它的值指定为上面 6 个常量中的任意一个。
 *
 *  自定义的方法中我们都加入了一个 if 判断，只有当 level 的值小于或等于对应日志级别值的时候，才会将日志打印出来。
 *
 *  使用：
 *      LogUtil.d("TAG", "debug log");
 *      LogUtil.w("TAG", "warn log");
 *      ...
 */
public class LogUtil {

    public static final int VERBOSE = 1;
    public static final int DEBUG = 2;
    public static final int INFO = 3;
    public static final int WARN = 4;
    public static final int ERROR = 5;
    public static final int NOTHING = 6;
//   设置等级
    public static final int lever = VERBOSE;

    public static void v(String tag, String msg) {
        if (lever <= VERBOSE) {
            Log.v(tag, msg);
        }

    }

    public static void d(String tag, String msg) {
        if (lever <= DEBUG) {
            Log.d(tag, msg);
        }
    }

    public static void i(String tag, String msg) {

        if (lever <= INFO) {
            Log.i(tag, msg);
        }
    }

    public static void w(String tag, String msg) {
        if (lever <= WARN) {
            Log.w(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (lever <= ERROR) {
            Log.e(tag, msg);

        }
    }
}
