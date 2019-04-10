package com.xuecheng.manage_cms;

import com.xuecheng.manage_cms.service.PageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class FreeemarkerTest {
    @Autowired
    PageService pageService;

    @Test
    public void testFreemarker(){
        String pageHtml = pageService.getPageHtml("5af942190e661827d8e2f5e3");
        System.out.println(pageHtml);
    }

}
