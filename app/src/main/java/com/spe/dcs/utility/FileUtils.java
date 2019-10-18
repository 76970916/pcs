/*
 * Copyright (c) 2016. Huangrui All rights reserved.
 */

package com.spe.dcs.utility;

import android.os.Environment;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Comparator;

/**
 * 文件名：FileUtils.java
 * 作  者： cj.rhuang@gmail.com
 * 时  间： 2019/1/7 10:11
 * 描  述： 文件管理操作
 */
public final class FileUtils {
    public static final String PATH = Environment.getExternalStorageDirectory().getAbsolutePath(); // 保存文件的路径
    public static String APP = PATH;                      // 应用路径
    public static String LOG;
    public static String APK = APP + "app.apk";
    public static String DOWNLOAD_TEMP_PATH;
    public static String IMAGE_CACHE;      // 图片缓存
    public static String USR;
    public static String DOWNLOAD;
    public static String DB;
    public static String SAVE_NUMNER;
    public static String SAVE_IMG;
    public static String SAVE_APK;

    public static void init(String name) {
        APP = PATH + File.separator + name + File.separator;
        LOG = APP + "Log" + File.separator;
        APK = APP + name + ".apk";
        DB = APP + "db" + File.separator;
        IMAGE_CACHE = APP + "img" ;
        SAVE_NUMNER = APP + "saveNumber" + File.separator;
        SAVE_APK = APP + "saveAPK" + File.separator;
        SAVE_IMG = APP + "saveImage" + File.separator;
        USR = APP + "usr" + File.separator;
        DOWNLOAD_TEMP_PATH = APP + ".temp" + File.separator;
        DOWNLOAD = APP + "download" + File.separator;
        // 文件目录
        mkdirs(APP);
        // 日志目录
        mkdirs(LOG);
        mkdirs(USR);
        mkdirs(IMAGE_CACHE,true);
        mkdirs(DB);
        mkdirs(DOWNLOAD_TEMP_PATH,true);
        mkdirs(DOWNLOAD);
        mkdirs(SAVE_NUMNER);
        mkdirs(SAVE_IMG);
        mkdirs(SAVE_APK,true);
    }


    /**
     * 重置缓存目录
     */
    public static void resetCache() {
        deleteFile(IMAGE_CACHE);
        // deleteFile2(CACHE);
        deleteFile(DOWNLOAD_TEMP_PATH);
        mkdirs(IMAGE_CACHE);
        initDownloadDir();
    }

    /**
     * 初始化下载的目录
     */
    public static void initDownloadDir() {
        File f = new File(DOWNLOAD_TEMP_PATH);
        if (!f.exists()) {
            f.mkdir();
        }
    }


    /**
     * 获取文件名
     *
     * @param file
     * @return
     */
    public static String getFileNameWithSuffix(String file) {
        if (file != null && !"".equals(file)) {
            int index = file.lastIndexOf("/");
            if (index != -1) {
                return file.substring(index + 1);
            } else {
                return file;
            }
        }
        return null;
    }

    public static String getFileName(String file) {
        String f = getFileNameWithSuffix(file);
        if (f != null) {
            int index = f.lastIndexOf(".");
            if (index != -1)
                f = f.substring(0, index);
            return f;
        }
        return null;
    }


    /**
     * 根据当前时间生成
     *
     * @param suffix
     */
    public static String newFileName(String suffix) {
        return newFileName(Long.toString(System.currentTimeMillis()), suffix);
    }

    /**
     * @param fileName 文件名称
     * @param suffix   后缀
     * @return
     */
    public static String newFileName(String fileName, String suffix) {
        return PATH + "/" + fileName + "." + suffix;
    }

    /**
     * 删除文件或文件夹
     *
     * @return
     */
    public static void deleteFile(String fileName) {
        File f = new File(fileName);
        if (f.exists()) {
            if (f.isFile()) {
                f.delete();
            } else if (f.isDirectory()) {
                String[] filelist = f.list();
                for (int i = 0; i < filelist.length; i++) {
                    deleteFile(fileName + filelist[i]);
                }
            }
        }
    }

    /**
     * 获取文件格式
     *
     * @param fileName
     * @return
     */
    public static String getExtension(String fileName) {
        int length = fileName.indexOf(".");
        return fileName.substring(length + 1);
    }

    /**
     * 判断文件夹是否存
     *
     * @param fileName
     * @return
     */
    public static boolean isDirExists(String fileName) {
        File f = new File(PATH + fileName);
        return f.isDirectory();
    }

    /**
     * 判断文件是否存在
     *
     * @param fileName
     * @return
     */
    public static boolean isFileExists(String fileName) {
        File f = new File(fileName);
        if (f.exists()) {
            if (f.length() == 0) f.delete();
        }
        return f.exists();
    }

    /**
     * 创建文件夹
     *
     * @param fileName
     */
    public static void mkdirs(String fileName,boolean isReMk) {
        File f = new File(fileName);
        if (!f.exists()) {
            f.mkdirs();
        }else {
           if(isReMk==true) {
               f.delete();
               f.mkdirs();
           }
        }
    }
    /**
     * 创建文件夹
     *
     * @param fileName
     */
    public static void mkdirs(String fileName) {
        mkdirs(fileName,false);
    }

    /**
     * 移动文件
     *
     * @param srcFile
     * @param destPath
     * @return
     */
    public static boolean Move(String srcFile, String destPath) {
        // File (or directory) to be moved
        File file = new File(srcFile);
        // Destination directory
        File dir = new File(destPath);
        // Move file to new directory
        boolean retCode = file.renameTo(new File(dir, file.getName()));
        return retCode;
    }


    public static byte[] toByteArray(String filename) {
        File f = new File(filename);
        if (!f.exists()) {
            return null;
        }
        ByteArrayOutputStream bos = new ByteArrayOutputStream((int) f.length());
        BufferedInputStream in = null;
        try {
            in = new BufferedInputStream(new FileInputStream(f));
            int buf_size = 1024;
            byte[] buffer = new byte[buf_size];
            int len = 0;
            while (-1 != (len = in.read(buffer, 0, buf_size))) {
                bos.write(buffer, 0, len);
            }
            return bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static class CompratorByLastModified implements Comparator<File> {
        public int compare(File f1, File f2) {
            long diff = f1.lastModified() - f2.lastModified();
            if (diff > 0)
                return -1;//倒序正序控制
            else if (diff == 0)
                return 0;
            else
                return 1;//倒序正序控制
        }

        public boolean equals(Object obj) {
            return true;
        }
    }

}
