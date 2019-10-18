package com.spe.dcs.utility;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Desc:
 * Author.
 * Data:${DATA}
 */

public class ObjectUtil {

    /**
     * 对象转换字符串
     *
     * @param object
     * @return
     * @throws IOException
     */
    public static String object2String(Object object) throws IOException {
        if (object == null) throw new NullPointerException("object can not be null");
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream;
        objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(object);
        String result = byteArrayOutputStream.toString("ISO-8859-1");
        objectOutputStream.close();
        byteArrayOutputStream.close();
        return result;
    }

    /**
     * string转换对象
     *
     * @param str
     * @return
     * @throws Exception
     */
    public static Object string2Object(String str) throws Exception {
        if (str == null || str.equals("")) throw new NullPointerException("str can not be null ");
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(str.getBytes("ISO-8859-1"));
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        Object object = objectInputStream.readObject();
        objectInputStream.close();
        byteArrayInputStream.close();
        return object;
    }

}
