package com.spe.dcs.utility;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.spe.dcs.app.PcsApplication;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * @ProjectName: DCS
 * @Package: com.spe.dcs.utility
 * @ClassName: CommonUtils
 * @Description:
 * @Author: cj.rhuang@gmail.com
 * @CreateDate: 2019/3/14 8:43
 * @Version: 1.0
 */
public class CommonUtils {

    public static Object getFieldValueByName(String fieldName, Object o) {
        try {
            if (fieldName.equals("PROJECT_NUMBER")) {
                fieldName = "PROJECT_NUMBER";
            }
            if (fieldName.equals("SUBPROJECT_NUMBER")) {
                fieldName = "SUBPROJECT_NUMBER";
            }
            if (fieldName.equals("SECTION")) {
                fieldName = "SECTION";
            }
            if (fieldName.equals("PROJECT_UNIT_NUMBER")) {
                fieldName = "PROJECT_UNIT_NUMBER";
            }
            if (fieldName.equals("FUNCTIONAL_AREA_CODE")) {
                fieldName = "FUNCTIONAL_AREA_CODE";
            }
            String[] names = fieldName.toLowerCase().split("_");
            String field = "";
            for (String name : names) {
                field += (name.substring(0, 1).toUpperCase() + name.substring(1));
            }
            String getter = "get" + field;

            Method method = o.getClass().getMethod(getter, new Class[]{});
            Object value = method.invoke(o, new Object[]{});
            return value;
        } catch (Exception e) {
            return null;
        }
    }

    public static Object getFragmentFieldValueByName(String fieldName, Object o) {
        try {
            String[] names = fieldName.toLowerCase().split("_");
            String field = "";
            for (String name : names) {
                field += (name.substring(0, 1).toUpperCase() + name.substring(1));
            }
            String getter = "get" + field;

            Method method = o.getClass().getMethod(getter, new Class[]{});
            Object value = method.invoke(o, new Object[]{});
            return value;
        } catch (Exception e) {
            return null;
        }
    }

    public static void logError(Throwable throwable) {
        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        throwable.printStackTrace(printWriter);
        Throwable cause = throwable.getCause();
        while (cause != null) {
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }
        printWriter.close();
        String result = writer.toString();
        Log.w("logError", result);
    }

    public static boolean isNetAvailable() {
        ConnectivityManager mConnectivityManager = (ConnectivityManager) PcsApplication.getInstance()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
        if (mNetworkInfo != null) {
            return mNetworkInfo.isAvailable();
        }
        return false;
    }

    public static boolean isJson(String content) {
        return content.startsWith("{");
    }

    public static List<MultipartBody.Part> filesToMultipartBodyParts(List<String> files) {
        List<MultipartBody.Part> parts = new ArrayList<>(files.size());
        for (String path : files) {
            File file = new File(path);
            RequestBody requestBody = RequestBody.create(MediaType.parse("image/png"), file);
            MultipartBody.Part part = MultipartBody.Part.createFormData("multipartFiles", file.getName(), requestBody);
            parts.add(part);
        }
        return parts;
    }

    public static RequestBody convertToRequestBody(String param) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("text/plain"), param);
        return requestBody;
    }
}
