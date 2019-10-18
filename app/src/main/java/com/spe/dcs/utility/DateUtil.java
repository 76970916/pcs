package com.spe.dcs.utility;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.text.format.Time;

/**
 * 文件名：DateUtil.java
 * 作  者： cj.rhuang@gmail.com
 * 时  间： 2019/2/19 11:56
 * 描  述： 日期选择
 */
public final class DateUtil {
    private DateUtil() {
    }

    public static void createPicker(Context context, OnDateSelecterListener listener) {
        Time t = new Time();
        t.setToNow(); // 取得系统时间。
        int y = t.year;
        int month = t.month ;
        int day = t.monthDay;
        DatePickerDialog dialog = new DatePickerDialog(context, AlertDialog.THEME_HOLO_LIGHT, (view, year, monthOfYear, dayOfMonth) -> listener.onSelected(String.format("%04d-%02d-%02d", year, monthOfYear+1, dayOfMonth)), y, month, day);
        dialog.getDatePicker().setCalendarViewShown(false);
        dialog.show();
    }

    public interface OnDateSelecterListener {
        void onSelected(String date);
    }

}
