package com.cz1.repository;

import com.cz1.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by wkchen on 2017/4/10.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
   User findByUsername(String name);
}
