package com.zlx.bangbang.controller;


import com.zlx.bangbang.domain.Indent;
import com.zlx.bangbang.service.impl.IndentServiceImpl;
import com.zlx.bangbang.utils.ResultUtil;
import com.zlx.bangbang.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

@RestController
public class IndentController {
    @Autowired
    private TokenUtil tokenUtil;

    @Autowired
    private IndentServiceImpl indentService;

    @GetMapping("/performer/{userId}")
    public Indent getUserPersonalPerformedIndentList(@PathVariable @NotNull String userId) {
        return ResultUtil.success(indentService.getUserPerformedIndent(userId));
    }
}
