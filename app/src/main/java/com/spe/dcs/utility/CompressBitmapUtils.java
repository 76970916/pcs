package com.spe.dcs.utility;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Desc:图片压缩 ---1M以内
 * Author.
 * Data:${DATA}
 */

public class CompressBitmapUtils {

    public static String savePhoto(String path) {
        int maxw = 2000;
        Bitmap bitmap = createBitmap(path, maxw);
        FileOutputStream fos = null;
        String str = null;
        Date date = null;
        if (bitmap.getWidth() > maxw) bitmap = zoomBitmap(bitmap, maxw, maxw
                * bitmap.getHeight() / bitmap.getWidth());
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");// 获取当前时间，进一步转化为字符串
        date = new Date(System.currentTimeMillis());
        str = format.format(date);
        path = FileUtils.IMAGE_CACHE + File.separator + FileUtils.getFileName(path) + "_" + str + ".jpg";
        File photo = new File(path);
        photo.getParentFile().mkdirs();
        if (!photo.exists()) {
            try {
                photo.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            fos = new FileOutputStream(path);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, fos);// 把数据写入文件
        } catch (FileNotFoundException e) {
            Log.w("test", e);
        } finally {
            try {
                if (bitmap != null && !bitmap.isRecycled())
                    bitmap.recycle();
                fos.flush();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return path;
        }
    }

   private static Bitmap zoomBitmap(Bitmap bitmap, int width, int height) {
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        Matrix matrix = new Matrix();
        float scaleWidth = ((float) width / w);
        float scaleHeight = ((float) height / h);
        matrix.postScale(scaleWidth, scaleHeight);// 利用矩阵进行缩放不会造成内存溢出
        Bitmap newbmp = Bitmap.createBitmap(bitmap, 0, 0, w, h, matrix, true);
        return newbmp;
    }

    private   static Bitmap createBitmap(String name, int maxw) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        options.inSampleSize = 1;
        BitmapFactory.decodeFile(name, options);
        int width, height;
        if (options.outWidth > maxw) {
            width = 2000;
            height = width * options.outHeight / options.outWidth + width
                    * options.outHeight % options.outWidth;
        } else {
            width = options.outWidth;
            height = options.outHeight;
        }
        options.inSampleSize = computeSampleSize(options, width, width * height);
        options.inJustDecodeBounds = false;
        options.inPurgeable = true;
        options.inInputShareable = true;
        options.inPreferredConfig = Bitmap.Config.ARGB_4444;
        Bitmap bitmap = null;
        try {
            bitmap = BitmapFactory
                    .decodeStream(new FileInputStream(name), null, options);
        } catch (FileNotFoundException e) {
        }
        return bitmap;
    }

    private   static int computeSampleSize(BitmapFactory.Options options,
                                 int minSideLength, int maxNumOfPixels) {
        int initialSize = computeInitialSampleSize(options, minSideLength, maxNumOfPixels);
        int roundedSize;
        if (initialSize <= 8) {
            roundedSize = 1;
            while (roundedSize < initialSize) {
                roundedSize <<= 1;
            }
        } else {
            roundedSize = (initialSize + 7) / 8 * 8;
        }
        return roundedSize;
    }

    private   static int computeInitialSampleSize(BitmapFactory.Options options,
                                        int minSideLength, int maxNumOfPixels) {
        double w = options.outWidth;
        double h = options.outHeight;
        int lowerBound = (maxNumOfPixels == -1) ? 1 : (int) Math.ceil(Math
                .sqrt(w * h / maxNumOfPixels));
        int upperBound = (minSideLength == -1) ? 128 : (int) Math.min(Math
                .floor(w / minSideLength), Math.floor(h / minSideLength));
        if (upperBound < lowerBound) {
            // return the larger one when there is no overlapping zone.
            return lowerBound;
        }
        if ((maxNumOfPixels == -1) && (minSideLength == -1)) {
            return 1;
        } else if (minSideLength == -1) {
            return lowerBound;
        } else {
            return upperBound;
        }
    }

}
