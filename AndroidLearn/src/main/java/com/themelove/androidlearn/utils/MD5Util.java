package com.themelove.androidlearn.utils;

import android.text.TextUtils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;
import java.util.TreeMap;


public class MD5Util {
    private static final String TAG = MD5Util.class.getSimpleName();
    private static String MD5SecretKey = "2aaa08de964854800c204e400006f45b";  //默认加密key

    private static void log(String key, String value) {
        String logmsg = String.format(Locale.getDefault(), TAG + "----->%s:%s", key, value);
        System.out.println(logmsg);
    }

    /**
     * MD5加密字符串
     *
     * @param originalString
     * @return
     */
    public static String MD5EncryptString(String originalString) {
        if (originalString == null)
            return null;
        else {
            byte[] result = null;
            String resultStr = null;
            try {
                log("signBefore", originalString);
                //MD5的结果为固定128位，即16个字节
                result = MessageDigest.getInstance("MD5").digest(originalString.getBytes("UTF-8"));
                resultStr = toHexString(result);
                log("signAfter", resultStr);
            } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return resultStr;
        }
    }

    /**
     * MD5加密字符串,指定加密key
     *
     * @param originalString 待加密字符串
     * @return
     */
    public static String MD5EncryptStringWithKey(String originalString, String md5SecretKey) {

        if (TextUtils.isEmpty(originalString)) {
            return null;
        }
        if (!TextUtils.isEmpty(md5SecretKey)) {
            MD5SecretKey = md5SecretKey;
        }
        String resultStr = null;
        resultStr = MD5EncryptString(originalString + MD5SecretKey);
        return resultStr;
    }

    /**
     * 将字节数组转换为16进制字符串
     *
     * @param b
     * @return
     */
    public static String toHexString(byte[] b) {
        char HEX_DIGITS[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        StringBuilder sb = new StringBuilder(b.length * 2);
        for (int i = 0; i < b.length; i++) {
            sb.append(HEX_DIGITS[(b[i] & 0xf0) >>> 4]);
            sb.append(HEX_DIGITS[b[i] & 0x0f]);
        }
        return sb.toString();
    }

    /*****************************************MD5加密TreeMap*****************************************/
    /**
     * MD5指定加密key加密treeMap
     *
     * @param treeMap
     * @param md5SecretKey 加密key
     * @return
     */
    public static String MD5EncryptTreeMapWithKey(TreeMap<String, String> treeMap, String md5SecretKey) {
        if (treeMap == null || treeMap.size() < 1) {
            return null;
        }
        if (!TextUtils.isEmpty(md5SecretKey)) {
            MD5SecretKey = md5SecretKey;
        }
        String resultStr = null;
        String signBeforeOrderString = getOrderedStringParamFromTreeMap(treeMap);
        resultStr = MD5EncryptString(signBeforeOrderString + MD5SecretKey);
        return resultStr;
    }

    /**
     * 以字符串的形式返回拼装后的TreeMap,以url参数形式（key=value）拼装，以&连接
     *
     * @param treeMap
     * @return
     */
    private static String getOrderedStringParamFromTreeMap(TreeMap<String, String> treeMap) {
        StringBuilder builder = new StringBuilder();
        for (String key : treeMap.keySet()) {
            builder.append(key + "=");
            builder.append(treeMap.get(key));
            builder.append("&");
        }
        builder.deleteCharAt(builder.length() - 1);
        return builder.toString();
    }

    /*****************************************MD5加密String[]*****************************************/

    /**
     * MD5加密String[]数组
     *
     * @param originalStringArray
     * @param originalStringArray
     * @return
     */
    public static String MD5EncryptStringArrayWithKey(String[] originalStringArray, String md5SecretKey) {
        if (originalStringArray == null || originalStringArray.length < 1) {
            return null;
        }
        if (!TextUtils.isEmpty(md5SecretKey)) {
            MD5SecretKey = md5SecretKey;
        }
        String resultStr = null;
        String signBeforeOrderString = getOrderedStringFromArray(originalStringArray);
        resultStr = MD5EncryptString(signBeforeOrderString + MD5SecretKey);
        return resultStr;
    }

    /**
     * 以字符串的形式返回排序后的字符串数组，以&连接
     *
     * @param originalStringArray
     * @return
     */
    private static String getOrderedStringFromArray(String[] originalStringArray) {
        BubbleSort(originalStringArray, 0);
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < originalStringArray.length; i++) {
            result.append(originalStringArray[i]);
            if (i != originalStringArray.length - 1) {
                result.append("&");
            }
        }
        return result.toString();
    }

    /**
     * String[]按index位置字母进行气泡排序
     *
     * @param sortStringArray
     * @param index
     */
    private static void BubbleSort(String[] sortStringArray, int index) {
        if (sortStringArray != null && sortStringArray.length > 0) {

            for (int i = 0; i < sortStringArray.length - 1; i++) {//
                for (int j = 0; j < sortStringArray.length - i - 1; j++) {
                    int minindex = sortStringArray[j].length() - 1 > sortStringArray[j + 1].length() - 1 ? sortStringArray[j + 1].length() - 1
                            : sortStringArray[j].length() - 1;
                    if (index <= minindex) {
                        if ((int) (sortStringArray[j].charAt(index)) >= (int) (sortStringArray[j + 1].charAt(index))) {
                            if ((int) (sortStringArray[j].charAt(index)) > (int) (sortStringArray[j + 1].charAt(index))) {
                                String temp = sortStringArray[j];
                                sortStringArray[j] = sortStringArray[j + 1];
                                sortStringArray[j + 1] = temp;
                            } else {
                                String[] rebubble = {sortStringArray[j], sortStringArray[j + 1]};
                                int index2 = index + 1;

                                int maxindex = sortStringArray[j].length() - 1 > sortStringArray[j + 1].length() - 1 ? sortStringArray[j].length() - 1
                                        : sortStringArray[j + 1].length() - 1;
                                if (index2 <= maxindex) {
                                    BubbleSort(rebubble, index2++);
                                    sortStringArray[j] = rebubble[0];
                                    sortStringArray[j + 1] = rebubble[1];
                                }

                            }

                        }
                    }
                }
            }
        }
    }


}
