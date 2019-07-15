package com.github.udoo.ultron.controller;

import com.github.udoo.ultron.model.vo.ResourceVO;
import com.github.udoo.ultron.service.system.ResourceQueryService;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.validation.BindException;

import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * @author dong.yang
 * @data 2019/7/15 14:33
 */
@RunWith(SpringRunner.class)
@WebMvcTest(ResourceController.class)
public class ResourceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ResourceQueryService resourceQueryService;

    @Test
    public void testQueryById() throws Exception {
        ResourceVO resourceVO = new ResourceVO();
        resourceVO.setId(1L);
        resourceVO.setName("aaa");
        resourceVO.setPath("ppp");
        when(resourceQueryService.queryById(1L)).thenReturn(resourceVO);

        mockMvc.perform(get("/resource/1.json").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(resourceVO.getPath())))
                .andExpect(jsonPath("data.name", is(resourceVO.getName())));
    }

    @Test
    public void testInsertValidatedError() throws Exception {
        ResourceVO resourceVO = new ResourceVO();
        resourceVO.setId(1L);
        resourceVO.setName("aaa");
        resourceVO.setPath("ppp");

        MvcResult result = mockMvc.perform(post("/resource/save.json", resourceVO).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();

        Optional<BindException> bindException = Optional.ofNullable((BindException) result.getResolvedException());
        bindException.ifPresent((se) -> assertThat(se, is(instanceOf(BindException.class))));
    }

    @Test
    public void testInsertValidated()throws Exception {
        ResourceVO resourceVO = new ResourceVO();
        resourceVO.setId(1L);
        resourceVO.setCode("code");
        resourceVO.setName("aaa");
        resourceVO.setPath("ppp");

        mockMvc.perform(post("/resource/save.json", resourceVO).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }
}
