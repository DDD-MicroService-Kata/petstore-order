package com.ThoughtWorks.DDD.Order;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import wiremock.com.github.jknack.handlebars.internal.Files;

import java.io.File;
import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles(profiles = "test")
@Transactional
@AutoConfigureMockMvc
public abstract class APIBaseTest {
    @Autowired
    private WebApplicationContext wac;

    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @Before
    public void baseBefore() {
        this.mockMvc =
                MockMvcBuilders.
                        webAppContextSetup(this.wac).
                        build();
    }

    protected String withJson(String path) {
        String filePath = "json" + File.separator + path;
        try {
            return Files.read(this.getClass().getClassLoader().getResourceAsStream(filePath));
        } catch (IOException e) {
            throw new RuntimeException(String.format("Failed to read json file in %s.", filePath));
        }
    }
}
