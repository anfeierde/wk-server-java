package com.cz1.service;

import com.cz1.domain.News;
import com.cz1.request.NewsRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by wkchen on 2017/4/13.
 */
public interface NewsService {
    News postNews(NewsRequest request, MultipartFile file);

    News findNews(Long id);

    void deleteNews(Long id);

    List<News> newsList(Integer page,Integer per_page);
}
