package com.jsoft.rocket.utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

public class FileUtil {

    public static String fileBase64(String path) {
        byte[] data = null;

        try {
            InputStream in = new FileInputStream(path);
            data = new byte[1024];
            in.read(data);
            in.close();
        }catch (Exception e) {
            System.out.println(" 【读取文件异常】 ");
        }

        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);
    }


    public static File base64Tofile(String base64, String filePath, String fileName) {

        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        File file = null;
        try {
            File dir = new File(filePath);
            if(!dir.exists() && dir.isDirectory()) {
                dir.mkdirs();
            }
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] bfile = decoder.decodeBuffer(base64);
            file = new File(filePath + File.separator + fileName);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(bfile);
            return file;
        } catch (Exception e) {
            return null;
        } finally {
            try {
                if(bos != null) {
                    bos.close();
                }
                if(fos != null) {
                    fos.close();
                }
            }catch (Exception e) {
                System.out.println(" 【转化文件异常】 ");
            }
        }
    }

}
