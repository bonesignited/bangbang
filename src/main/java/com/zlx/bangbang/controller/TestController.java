package com.zlx.bangbang.controller;


import com.zlx.bangbang.domain.Item;
import com.zlx.bangbang.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/test")
public class TestController {
    @GetMapping("get")
    public ResponseEntity testget() {
        return ResultUtil.success(new Item("zzz", "male"));
    }
}
