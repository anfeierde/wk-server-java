package com.cz1.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * Created by wkchen on 2017/4/17.
 */
public class MultipartFileUtils {


    /**
     * 获取文件名
     * @param file
     * @return
     */
    public static String getFileName(MultipartFile file) {
        return file.getOriginalFilename();
    }


    /**
     * 获取文件byte
     * @param file
     * @return
     * @throws IOException
     */
    public static byte[] getBytes(MultipartFile file) throws IOException {
        return file.getBytes();
    }


    /**
     * 获取文件扩展名
     * @param file
     * @return
     */
    public static String getExtension(MultipartFile file) {
        String filename = getFileName(file);
        return filename.substring(filename.lastIndexOf("."));
    }

    /**
     * 转换成byte输入流
     * @param file
     * @return
     * @throws IOException
     */
    public static ByteArrayInputStream createInputStearm(MultipartFile file) throws IOException {
        return new ByteArrayInputStream(file.getBytes());
    }
}
