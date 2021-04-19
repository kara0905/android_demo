package com.zyl.ndk;

public class NdkJniUtils {

    static {
        System.loadLibrary("myJniApp");
    }

    public native String getCLanguageString();
}
