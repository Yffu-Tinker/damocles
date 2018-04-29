package cc.tinker.service;

import cc.tinker.conveyer.JsonResponse;
import cc.tinker.restrofitTools.RestrofitTools;
import cc.tinker.restrofitTools.RetrofitFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import retrofit2.Call;

import static org.junit.Assert.*;

/**
 * Created by Tinker on 3/26/2018.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EmailSenderTest {
    @Autowired
    private EmailSender emailSender;
    private static final String pushUrl = "http://localhost:8080/tinker/kindle/push/file/";

    @Test
    public void sendAttachmentsMail() throws Exception {
        RestrofitTools restrofitTools = RetrofitFactory.create(pushUrl, RestrofitTools.class);
        Call<JsonResponse> jsonResponseCall = restrofitTools.pushFileToKindle("E:\\资治通鉴-胡三省.mobi", "yeqinglyy_31@kindle.cn");

    }

}