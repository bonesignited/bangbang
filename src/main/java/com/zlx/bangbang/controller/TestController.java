package com.zlx.bangbang.controller;

import com.zlx.bangbang.domain.Item;
import com.zlx.bangbang.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class TestController {

    @GetMapping("/testget")
    public ResponseEntity testGet(@RequestParam("name") String name, @RequestParam("sex") String sex){
        log.info("参数为 {} {}", name, sex);
        Item item = new Item(name, sex);
        log.info("返回响应");
        return ResultUtil.success(item);
    }

    @PostMapping("/testpost")
    public ResponseEntity testPost(@RequestBody Item item) {
        log.info("参数为 {} {}", item.getName(), item.getSex());
        Item item1 = new Item(item.getName(), item.getSex());
        log.info("返回响应");
        return ResultUtil.success(item1);
    }
}
