package com.cz1.service.impl;

import com.cz1.domain.News;
import com.cz1.error.NotFoundException;
import com.cz1.repository.NewsRepository;
import com.cz1.request.NewsRequest;
import com.cz1.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

/**
 * Created by wkchen on 2017/4/13.
 */
@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsRepository newsRepository;

    @Override
    public News postNews(NewsRequest request, MultipartFile file) {

        News news = new News();
        news.setTitle(request.getTitle());
        news.setContent(request.getContent());
        news.setUrl(request.getUrl());
        news.setCreated_at(new Date());
        news.setUpdated_at(new Date());
        news.setStatus(News.STATUS_DEF);
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
