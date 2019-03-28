package com.xuecheng.api.config.cms;

import com.xuecheng.framework.domain.cms.request.QueryPageRequest;

public interface CmsPageControllerApi {

    //页面查询
    public QueryPageRequest findList(int page,int size,QueryPageRequest queryPageRequest);
}
