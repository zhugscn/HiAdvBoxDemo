package cn.zhuguangsheng.hiadvbox;

import android.os.Environment;

import java.io.File;

public class AdvConstants {
    public static final String SDCARD_DIR = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator;

    public static final String ADVPUB_HOME_DIR = SDCARD_DIR + File.separator
            + "advpub/";

    public static final String FILE_PATH = ADVPUB_HOME_DIR;

    //资源类型
    public static final int RES_TYPE_IMAGE = 0;
    public static final int RES_TYPE_VIDEO = 1;
}
