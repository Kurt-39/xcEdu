package com.xuecheng.manage_cms.controller;

import com.xuecheng.api.config.cms.CmsPageControllerApi;
import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.request.QueryPageRequest;
import com.xuecheng.framework.domain.cms.response.CmsPageResult;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.ResponseResult;
import com.xuecheng.manage_cms.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cms/page")
public class CmsPageController implements CmsPageControllerApi {

    @Autowired
    private PageService pageService;
    //页面查询
    @Override
    @GetMapping("/list/{page}/{size}")
    public QueryResponseResult findList(@PathVariable("page") int page,@PathVariable("size") int size, QueryPageRequest queryPageRequest){
        return pageService.findList(page,size,queryPageRequest);

    };
    @PostMapping("/add")
    public CmsPageResult add(@RequestBody CmsPage cmsPage){
       return pageService.addPage(cmsPage);
    }
    //根据id查询
    @Override
    @GetMapping("/get/{id}")
    public CmsPage findById(@PathVariable("id") String siteId){
        return pageService.findById(siteId);
    };
    //保存
    @Override
    @PutMapping("/edit/{id}")
    public CmsPageResult update(@PathVariable("id") String siteId,@RequestBody CmsPage cmsPage){
        return pageService.update(siteId,cmsPage);
    }

    @Override
    @DeleteMapping("/delete/{id}")
    public ResponseResult delete(@PathVariable("id") String id) {
        return pageService.delete(id);
    }

    @Override
    @PostMapping("/postPage/{pageId}")
    public ResponseResult post(@PathVariable("pageId") String pageId) {
       return pageService.postPage(pageId);

    }

    ;

}
