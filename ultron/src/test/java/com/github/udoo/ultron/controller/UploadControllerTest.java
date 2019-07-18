package com.github.udoo.ultron.controller;

import okhttp3.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * @author dong.yang
 * @data 2019/7/18 16:46
 */
@RunWith(SpringRunner.class)
@WebMvcTest(UploadController.class)
public class UploadControllerTest {

    @Test
    public void testUploadWithOkHttpClient() throws Exception {
        OkHttpClient client = new OkHttpClient.Builder()
                .writeTimeout(30, TimeUnit.SECONDS)
                .build();
        RequestBody bodyParams = RequestBody.create(MediaType.parse("multipart/form-data; charset=utf-8"),
                new File("D:\\role_res.json"));

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", "role_res.json", bodyParams)
                .addFormDataPart("desc", "upload sample")
                .build();

        Request request = new Request.Builder()
                .url("http://localhost:8090/ultron/upload.htm")
                .post(requestBody)
                .build();
        Response response = client.newCall(request).execute();
        Assert.assertTrue(response.isSuccessful());
    }

}
