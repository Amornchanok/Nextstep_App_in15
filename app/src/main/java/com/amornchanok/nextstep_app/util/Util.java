package com.amornchanok.nextstep_app.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;

public class Util {

    public static String objToString(Object obj){
        return new Gson().toJson(obj);
    }

    public static byte[] bitmapToByteArr(Bitmap bitmap){
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }

    public static Bitmap byteToBitmap(byte[] bytes){
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }


}
