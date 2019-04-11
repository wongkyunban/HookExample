package com.wong.hookexample;

import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author WongKyunban
 * description
 * created at 2019-04-08 下午5:06
 * @version 1.0
 */
public class HookHelper {
    public static void hookOnClickListener(View view) throws Exception{
        // 第一步：反射得到 ListenerInfo 对象
        Method getListenerInfo = View.class.getDeclaredMethod("getListenerInfo");
        getListenerInfo.setAccessible(true);
        Object listenerInfo = getListenerInfo.invoke(view);
        // 第二步：得到原始的 OnClickListener事件方法
        Class<?> listenerInfoClz = Class.forName("android.view.View$ListenerInfo");
        Field mOnClickListener = listenerInfoClz.getDeclaredField("mOnClickListener");
        mOnClickListener.setAccessible(true);
        View.OnClickListener originOnClickListener = (View.OnClickListener)mOnClickListener.get(listenerInfo);
        // 第三步：用 Hook代理类 替换原始的 OnClickListener
        View.OnClickListener hookedOnClickListener = new HookOnClickListenerProxy(originOnClickListener);
        mOnClickListener.set(listenerInfo,hookedOnClickListener);
    }
}
