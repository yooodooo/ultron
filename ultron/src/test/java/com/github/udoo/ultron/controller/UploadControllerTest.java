package com.github.udoo.ultron.controller;

import okhttp3.*;
import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.io.File;
import java.io.FileInputStream;
import java.util.concurrent.TimeUnit;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author dong.yang
 * @data 2019/7/18 16:46
 */
@RunWith(SpringRunner.class)
@WebMvcTest(UploadController.class)
public class UploadControllerTest {

    @Autowired
    private MockMvc mvc;

    private static final String file_less_than_1m = "D:\\role_res.json";
    private static final String file_larger_than_1m = "D:\\迅雷下载\\go1.11.windows-amd64.msi";

    @Test
    public void testUploadWithOkHttpClient() throws Exception {
        Response response = okHttpClientUpload(file_less_than_1m);
        Assert.assertTrue(response.isSuccessful());
    }

    @Test(expected = Exception.class)
    public void testUploadWithOkHttpClientWithException() throws Exception {
        okHttpClientUpload(file_larger_than_1m);
    }

    private Response okHttpClientUpload(String filePath) throws Exception {
        OkHttpClient client = new OkHttpClient.Builder()
                .writeTimeout(30, TimeUnit.SECONDS)
                .build();
        RequestBody bodyParams = RequestBody.create(MediaType.parse("multipart/form-data; charset=utf-8"),
                new File(filePath));

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", "sample_file.json", bodyParams)
                .addFormDataPart("desc", "upload sample")
                .build();

        Request request = new Request.Builder()
                .url("http://localhost:8090/ultron/upload.htm")
                .post(requestBody)
                .build();
        return client.newCall(request).execute();
    }

    @Test
    public void testWithMock() throws Exception {
        MockMultipartFile multipartFile = new MockMultipartFile("file", file_less_than_1m,
                "text/plain", IOUtils.toByteArray(new FileInputStream(file_less_than_1m)));
        this.mvc.perform(multipart("/upload.htm")
                .file(multipartFile)
                .param("desc", "upload sample"))
                .andDo(print())
                .andExpect(status().isOk());
    }

}
