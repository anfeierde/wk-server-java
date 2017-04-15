package com.cz1.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by wkchen on 2017/4/14.
 */
@RestController
@RequestMapping(value = "/upload")
public class FileController {

//    private static final String PNGT_YPE = "image/png";

    @Value("${file.upload.base}")
    private String imageBaseName;

    @Value("${file.upload.directory}")
    private String imageDirectory;

    @PostMapping
    public String upLoadFile(@RequestPart("file") MultipartFile multipart) {
        String fileNameBase = imageBaseName + String.valueOf(System.currentTimeMillis());
        String fileExtension = multipart.getOriginalFilename().substring(multipart.getOriginalFilename().lastIndexOf("."));
        String fileName = fileNameBase +fileExtension;
        File file = new File(imageDirectory+fileName);
        try {
            // MultipartFile to File
            multipart.transferTo(file);
            // check file type is image
            BufferedImage image = ImageIO.read(file);
            if (image == null) {
                // delete file(not image type)
                if (file.exists()) {
                    file.delete();
                }
                throw new MultipartException("The file type must be an image format");
            }
            ImageIO.write(image, "png", file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "上传成功";
    }
}
