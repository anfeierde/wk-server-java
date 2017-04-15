package com.cz1.repository;

import com.cz1.domain.News;
import com.cz1.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wkchen on 2017/4/13.
 */
@Repository
public interface NewsRepository extends JpaRepository<News,Long>{
    List<News> findAllByUser(User user);
}
