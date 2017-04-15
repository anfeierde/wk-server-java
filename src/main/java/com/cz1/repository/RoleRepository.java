package com.cz1.repository;

import com.cz1.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by wkchen on 2017/4/11.
 */
@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByName(String name);
}