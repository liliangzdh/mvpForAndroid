package com.ydniu.mvpbase.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;

import java.io.File;

public class IntentUtils {

    // 打开文件，并进行安装
    public static void openFile(Context context, File f) {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(android.content.Intent.ACTION_VIEW);
        String type = getMimeType_Atlantis(f);
        Uri data;
        // 判断版本大于等于7.0
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            String packageName = context.getApplicationInfo().packageName;
            String authority = packageName + ".FileProvider";
            data = FileProvider.getUriForFile(context, authority, f);
            // 给目标应用一个临时授权
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        } else {
            data = Uri.fromFile(f);
        }
        intent.setDataAndType(data, type);
        context.startActivity(intent);
    }


    public static boolean isHasInstallPermissionWithO(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return context.getPackageManager().canRequestPackageInstalls();
        }
        return true;
    }



    // 检查文件类型
    private static String getMimeType_Atlantis(File f) {
        String type;
        String fName = f.getName();
        String end = fName
                .substring(fName.lastIndexOf(".") + 1, fName.length())
                .toLowerCase();
        switch (end) {
            case "m4a":
            case "mp3":
            case "mid":
            case "xmf":
            case "ogg":
            case "wav":
                type = "audio";
                break;
            case "3gp":
            case "mp4":
                type = "video";
                break;
            case "apk":
                type = "application/vnd.android.package-archive";
                break;
            default:
                type = "*";
                break;
        }
        if (!end.equals("apk")) {
            type += "/*";
        }
        return type;
    }
}
