package com.github.ghthou.springmvcstringtrimsamples.sample;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SpringMvcStringTrimSamplesApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    private static final String PARAM = " test ";
    private static final String RESULT = PARAM.trim();

    @Test
    public void url() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/url?name=" + PARAM))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(RESULT));
    }

    @Test
    public void form() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/form")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .content("name=" + PARAM)
        )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(RESULT));
    }

    @Test
    public void body() throws Exception {
        String json = "{\"name\":\"" + PARAM + "\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/body")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(json)
        )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(RESULT));
    }

    @Test
    public void xmlBody() throws Exception {
        String json = "<root><name>" + PARAM + "</name></root>";
        mockMvc.perform(MockMvcRequestBuilders.post("/body")
                .contentType(MediaType.APPLICATION_XML_VALUE)
                .content(json)
        )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(RESULT));
    }
}
