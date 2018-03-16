package com.mkhaleghy.cinema.tools.abstraction;

import android.content.Context;
import android.content.res.AssetManager;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;

import com.mkhaleghy.cinema.R;
import com.mkhaleghy.cinema.app.Ci;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by mk on 3/8/2018.
 */

public class Utils {
    public static int getStatusBarHeight() {
        int resourceId = Ci.context().getResources()
                .getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            return Ci.context().getResources().getDimensionPixelSize(resourceId);
        }
        return 0;
    }

    public static InputStream loadInputStreamFromAssetFile(Context context, String fileName){
        AssetManager am = context.getAssets();
        try {
            InputStream is = am.open(fileName);
            return is;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String loadFileFromAssets(String path){
        String content = null;
        try {
            InputStream is = loadInputStreamFromAssetFile(Ci.context(), path);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            content = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return content;
    }

    public static Spannable getColoredText(String txt, int colorId) {
        Spannable spannable = new SpannableString(txt);
        spannable.setSpan(new ForegroundColorSpan(colorId), 0, spannable.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannable;
    }
}
