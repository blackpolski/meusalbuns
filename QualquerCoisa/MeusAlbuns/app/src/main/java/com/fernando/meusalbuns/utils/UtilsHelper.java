package com.fernando.meusalbuns.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by 01796085090 on 16/11/2017.
 */

public class UtilsHelper {

    public static void msg(Context context, String msg){
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }
}
