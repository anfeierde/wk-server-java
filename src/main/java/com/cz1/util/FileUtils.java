package com.cz1.util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public final class FileUtils {

    /**
     * 判断文件是否是图片*
     * @param imgFile 文件对象
     * @return
     */
    public static boolean isImage(File imgFile) {
        try {
            BufferedImage image = ImageIO.read(imgFile);
            return image != null;   // 是图片
        } catch (IOException e) {
            e.printStackTrace();
            return false;    //  不是图片
        }
    }
}