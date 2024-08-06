package com.tianji.learning.controller;

import com.tianji.common.domain.dto.PageDTO;
import com.tianji.common.domain.query.PageQuery;
import com.tianji.learning.domain.vo.LearningLessonVO;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lessons")
@RequiredArgsConstructor
public class LearningLessonController {

    final ILearningLessonService lessonService;

    @ApiOperation("query page")
    @GetMapping("page")
    public PageDTO<LearningLessonVO> queryMeLessons(PageQuery query){
        return lessonService.queryMyLessons(query);
    }
}
