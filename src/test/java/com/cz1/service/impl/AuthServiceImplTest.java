package com.cz1.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Created by wkchen on 2017/4/11.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AuthServiceImplTest {

    @Autowired
    private MockMvc mvc;


    @Autowired
    private AuthServiceImpl authService;

    @Test
    public void createToken() throws Exception {
//        mvc.perform(MockMvcRequestBuilders.get("/auth")
//                .pathInfo("wkchen")
//                .param("password", "asddd"))
//                .andExpect(MockMvcResultMatchers.status().isOk());
        authService.createToken("wkchen", "asddd");
    }

}