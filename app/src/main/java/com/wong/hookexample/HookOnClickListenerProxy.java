package com.wong.hookexample;

import android.view.View;
import android.widget.Toast;

/**
 * @author WongKyunban
 * description
 * created at 2019-04-08 下午5:04
 * @version 1.0
 */
public class HookOnClickListenerProxy implements View.OnClickListener {
    private View.OnClickListener origin;
    public HookOnClickListenerProxy(View.OnClickListener origin){
        this.origin = origin;
    }
    @Override
    public void onClick(View v) {
        Toast.makeText(v.getContext(), "Hook Click Listener", Toast.LENGTH_SHORT).show();

        if(origin != null){
            origin.onClick(v);
        }
    }
}
