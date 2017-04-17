package com.cz1.controller;

import com.cz1.domain.News;
import com.cz1.service.impl.NewsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created by wkchen on 2017/4/13.
 */
@RestController
@RequestMapping(value = "/news")
public class NewsController {

    @Autowired
    private NewsServiceImpl newsService;


    @PostMapping
    public ResponseEntity<?> postNews(@RequestPart("info") String request,
                                      @RequestPart("file") MultipartFile multipart) throws IOException {
        News news = newsService.postNews(request, multipart);
        return ResponseEntity.ok(news);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> findNews(@PathVariable("id") Long id) {
        return ResponseEntity.ok(newsService.findNews(id));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteNews(@PathVariable("id") Long id) {
        newsService.deleteNews(id);
        return ResponseEntity.ok(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<?> newsList(
            @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(value = "per_page", required = false, defaultValue = "10") Integer per_page) {
        return ResponseEntity.ok(newsService.newsList(page, per_page));
    }
}
