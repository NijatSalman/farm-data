package com.company;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
class FarmDataApplicationTests {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Test
    void contextLoads() {
    }


}
