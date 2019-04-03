package com.xuecheng.manage_cms.service;

import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.request.QueryPageRequest;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.QueryResult;
import com.xuecheng.manage_cms.dao.CmsPageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Service
public class PageService {

    //注入dao
    @Autowired
    private CmsPageRepository cmsPageRepository;
/*
        if (queryPageRequest==null){
          queryPageRequest = new QueryPageRequest();
        }
        CmsPage cmsPage = new CmsPage();
        //判断有无SitedId
        if (!StringUtils.isEmpty(queryPageRequest.getSiteId())){
            cmsPage.setSiteId(queryPageRequest.getSiteId());
        }
        //判断有无模板ID
        if (!StringUtils.isEmpty(queryPageRequest.getTemplateId())){
            cmsPage.setTemplateId(queryPageRequest.getTemplateId());
        }
        //判断有无别名
        if (!StringUtils.isEmpty(queryPageRequest.getPageAliase())){
            cmsPage.setPageAliase(queryPageRequest.getPageAliase());
        }
        //上模板
        ExampleMatcher.matching().withMatcher(cmsPage,ExampleMatcher.GenericPropertyMatchers.contains());
 */
    public QueryResponseResult findList(int page, int size, QueryPageRequest queryPageRequest){
        Optional<QueryPageRequest> optional = Optional.of(queryPageRequest);
        if (!optional.isPresent()){
            QueryPageRequest queryPageRequest1 = optional.get();
            queryPageRequest1 = new QueryPageRequest();
        }
        if (page <=0){
            page = 1;
        }
        page = page -1;

        if (size <=0){
            size = 20;
        }
        //创建CmsPage对象,接受来自queryPageRequest的查询条件
        CmsPage cmsPage = new CmsPage();
        //判断有无SitedId
        if (!StringUtils.isEmpty(queryPageRequest.getSiteId())){
            cmsPage.setSiteId(queryPageRequest.getSiteId());
        }
        //判断有无模板ID
        if (!StringUtils.isEmpty(queryPageRequest.getTemplateId())){
            cmsPage.setTemplateId(queryPageRequest.getTemplateId());
        }
        //判断有无别名
        if (!StringUtils.isEmpty(queryPageRequest.getPageAliase())){
            cmsPage.setPageAliase(queryPageRequest.getPageAliase());
        }
        //上模板匹配器
        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withMatcher("pageAliase", ExampleMatcher.GenericPropertyMatchers.contains());
        //匹配
        Example<CmsPage> example = Example.of(cmsPage, exampleMatcher);

        Pageable pageable = PageRequest.of(page, size);
        Page<CmsPage> pages = cmsPageRepository.findAll(example,pageable);
        //现在需要把处理好的QueryResponseResult传给controller
        //QueryResponseResult需要什么
        QueryResult queryResult = new QueryResult();
        queryResult.setList(pages.getContent());
        queryResult.setTotal(pages.getTotalElements());
        return new QueryResponseResult(CommonCode.SUCCESS,queryResult);


    }
}
