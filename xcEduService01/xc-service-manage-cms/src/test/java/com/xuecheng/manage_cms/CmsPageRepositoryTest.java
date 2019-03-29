package com.xuecheng.manage_cms;


import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.CmsPageParam;
import com.xuecheng.manage_cms.dao.CmsPageRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CmsPageRepositoryTest {
    @Autowired
    private CmsPageRepository cmsPageRepository;
    //findAll
    @Test
    public void testFindAll(){
        List<CmsPage> all = cmsPageRepository.findAll();
        System.out.println(all);
    }
    //页码查询
    @Test
    public  void testFindPage(){
        Pageable pageable = PageRequest.of(1,10);
        Page<CmsPage> pages = cmsPageRepository.findAll(pageable);
        System.out.println(pages);

    }
    //添加
    @Test
    public void testInsert(){
        CmsPage cmsPage = new CmsPage();
        cmsPage.setSiteId("kurt1");

        cmsPage.setTemplateId("t01");
        cmsPage.setPageName("测试页面");
        cmsPage.setPageCreateTime(new Date());
        List<CmsPageParam> pageParams = new ArrayList();
        CmsPageParam cmsPageParam = new CmsPageParam();
        cmsPageParam.setPageParamName("param1");
        cmsPageParam.setPageParamValue("param1_value");
        pageParams.add(cmsPageParam);
        cmsPage.setPageParams(pageParams);
        cmsPageRepository.save(cmsPage);
        System.out.println(cmsPage);

    }

    //删除
    @Test
    public void testDelete(){

        cmsPageRepository.deleteById("5c9df0fd4630870674340916");

    }
    //修改
    @Test
    public  void testUpdate() {
        Optional<CmsPage> optional = cmsPageRepository.findById("5abefd525b05aa293098fca6");
        if (optional.isPresent()){
            CmsPage cmsPage = optional.get();
            cmsPage.setPageAliase("虎虎虎");
            cmsPageRepository.save(cmsPage);
        }
    }
}


