package com.xuecheng.api.course;

import com.xuecheng.framework.domain.course.Teachplan;
import com.xuecheng.framework.domain.course.ext.TeachplanNode;
import com.xuecheng.framework.model.response.ResponseResult;
import io.swagger.annotations.ApiOperation;

public interface CourseControllerApi {
    @ApiOperation("查找课程列表")
    public TeachplanNode findTeachplanList(String id);
    @ApiOperation("添加课程")
    public ResponseResult addTeachplan(Teachplan teachplan);

}
