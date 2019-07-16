package com.github.udoo.ultron.controller;

import com.alibaba.fastjson.JSON;
import com.github.udoo.ultron.model.vo.ResourceVO;
import com.github.udoo.ultron.service.system.ResourceQueryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
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
        ResourceVO resourceVO = ResourceVO.builder()
                .id(1L)
                .name("name")
                .path("path")
                .build();
        when(resourceQueryService.queryById(1L)).thenReturn(resourceVO);

        mockMvc.perform(get("/resource/1.json").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(resourceVO.getPath())))
                .andExpect(jsonPath("data.name", is(resourceVO.getName())));
    }

    @Test
    public void testInsert() throws Exception {
        ResourceVO resource = ResourceVO.builder()
                .code("")
                .name("this is a long name. exceed 32 limit of name")
                .path("path")
                .build();

        MvcResult result = mockMvc.perform(
                post("/resource/save.json")
                        .header("X-Requested-With", "XMLHttpRequest")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JSON.toJSONString(resource)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("code不能为空")))
                .andExpect(content().string(containsString("名称不能超过32个字符")))
                .andReturn();

        Optional<MethodArgumentNotValidException> bindException = Optional.ofNullable((MethodArgumentNotValidException) result.getResolvedException());
        bindException.ifPresent((se) -> assertThat(se, is(instanceOf(MethodArgumentNotValidException.class))));


        ResourceVO resourcePass = ResourceVO.builder()
                .id(1L)
                .code("cc")
                .name("aaaa")
                .path("pppp")
                .build();
        mockMvc.perform(
                post("/resource/save.json")
                        .header("X-Requested-With", "XMLHttpRequest")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JSON.toJSONString(resourcePass)))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void testUpdate() throws Exception {
        ResourceVO resource = ResourceVO.builder()
                .code("")
                .name("this is a long name. exceed 32 limit of name")
                .path("path")
                .build();

        MvcResult result = mockMvc.perform(
                post("/resource/update.json")
                        .header("X-Requested-With", "XMLHttpRequest")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JSON.toJSONString(resource)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("名称不能超过32个字符")))
                .andReturn();

        Optional<MethodArgumentNotValidException> bindException = Optional.ofNullable((MethodArgumentNotValidException) result.getResolvedException());
        bindException.ifPresent((se) -> assertThat(se, is(instanceOf(MethodArgumentNotValidException.class))));


        ResourceVO resourcePass = ResourceVO.builder()
                .id(1L)
                .name("aaaa")
                .path("pppp")
                .build();
        mockMvc.perform(
                post("/resource/update.json")
                        .header("X-Requested-With", "XMLHttpRequest")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JSON.toJSONString(resourcePass)))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }
}
