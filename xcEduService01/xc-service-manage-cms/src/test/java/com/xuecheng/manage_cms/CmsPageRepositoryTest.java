package com.xuecheng.manage_cms;


import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.CmsPageParam;
import com.xuecheng.manage_cms.dao.CmsPageRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CmsPageRepositoryTest {
    @Autowired
    private CmsPageRepository cmsPageRepository;
   @Autowired
    RestTemplate restTemplate;
   @Test
   public void testRestTemplate(){
       Map body = restTemplate.getForEntity("http://localhost:31001/cms/config/getmodel/5a791725dd573c3574ee333f",
               Map.class).getBody();
       System.out.println(body);

   }

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
    //模糊查询
    /*
    第一步:创造查询条件
        实例匹配创建实例
     */
    @Test
    public  void testFindPageByExample(){

        CmsPage cmsPage = new CmsPage();
        cmsPage.setPageAliase("轮播");
        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withMatcher("pageAliase", ExampleMatcher.GenericPropertyMatchers.contains());
        Example<CmsPage> example = Example.of(cmsPage, exampleMatcher);
        Pageable pageable = PageRequest.of(0, 10);
        Page<CmsPage> all = cmsPageRepository.findAll(example, pageable);
        System.out.println(all);
    }

}


