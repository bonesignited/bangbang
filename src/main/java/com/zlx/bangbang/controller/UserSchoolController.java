package com.zlx.bangbang.controller;


import com.zlx.bangbang.service.SchoolService;
import com.zlx.bangbang.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/school")
@Slf4j
public class UserSchoolController {

    @Autowired
    SchoolService schoolService;

    /**
     * 通过关键字模糊匹配学校列表，如果关键字为空则返回所有学校
     */
    @GetMapping
    public ResponseEntity getSchool(String key) {
        if (key == null) {
            return ResultUtil.success(schoolService.getSchoolInFuzzyMatching("%"));
        }
        return ResultUtil.success(schoolService.getSchoolInFuzzyMatching(key));
    }

    /**
     * 通过学校 id 获取学校信息
     */
    @GetMapping("/{schoolId}")
    public ResponseEntity getSchool(@PathVariable Integer schoolId) {
        return ResultUtil.success(schoolService.getById(schoolId));
    }
}
