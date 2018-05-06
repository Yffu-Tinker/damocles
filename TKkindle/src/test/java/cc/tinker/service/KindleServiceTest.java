package cc.tinker.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KindleServiceTest {

    @Autowired private KindleService kindleService;
    @Test
    public void pushAllFile() {
        kindleService.pushAllFile("yeqinglyy_31@kindle.cn","E:\\EBOOKS");
    }
}