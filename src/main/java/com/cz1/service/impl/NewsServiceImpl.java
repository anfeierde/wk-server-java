package com.cz1.service.impl;

import com.cz1.domain.Image;
import com.cz1.domain.News;
import com.cz1.error.NotFoundException;
import com.cz1.repository.NewsRepository;
import com.cz1.service.NewsService;
import com.cz1.util.MultipartFileUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by wkchen on 2017/4/13.
 */
@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsRepository newsRepository;


    @Override
    public News postNews(String info, MultipartFile multipart) throws IOException {
        // 转换成对象
        ObjectMapper objectMapper = new ObjectMapper();
        News request = objectMapper.readValue(info, News.class);

        // 保存信息
        News news = new News();
        news.setTitle(request.getTitle());
        news.setContent(request.getContent());
        news.setUrl(request.getUrl());
        news.setCreated_at(new Date());
        news.setUpdated_at(new Date());
        news.setStatus(News.STATUS_DEF);

        // 处理上传文件
        String fileExtension = MultipartFileUtils.getExtension(multipart);
        String fileName = String.valueOf(System.currentTimeMillis()) + fileExtension;

        if (!multipart.isEmpty()) {
            // 1.判断文件是否未图片格式
            BufferedImage bufferedImage = ImageIO.read(MultipartFileUtils.createInputStearm(multipart));
            File file = new File(fileName);
            if (bufferedImage == null) {
                throw new MultipartException("The file type must be an image format");
            }
            // 2.保存图片到文件系统
            ImageIO.write(bufferedImage, "png", file);
            // 3.保存图片路径到数据库
            Set<Image> images = new HashSet<Image>(){{
                 add(new Image(file.getPath(), new Date(), new Date(), news));
                 add(new Image(file.getPath(), new Date(), new Date(), news));
            }};
            news.setImages(images);
        } else {
            throw new MultipartException("The file is null");
        }
        return newsRepository.save(news);
    }


    @Override
    public News findNews(Long id) {
        if (newsRepository.findOne(id) == null) {
            throw new NotFoundException("Not found this is of the news");
        }
        return newsRepository.findOne(id);
    }


    @Override
    public void deleteNews(Long id) {
        if (newsRepository.findOne(id) == null) {
            throw new NotFoundException("Not found this is of the news");
        }
        newsRepository.delete(id);
    }


    @Override
    public List<News> newsList(Integer page, Integer per_page) {
        Sort sort = new Sort(Sort.Direction.ASC, "id");
        Pageable pageable = new PageRequest(page, per_page, sort);
        Page<News> newsPage = newsRepository.findAll(pageable);
        return newsPage.getContent();
    }
}
