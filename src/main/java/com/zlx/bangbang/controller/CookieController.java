package com.zlx.bangbang.controller;

import com.zlx.bangbang.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.ws.Response;

@RestController
@Slf4j
public class CookieController {
    @GetMapping("/test/cookie")
    public ResponseEntity cookie(@RequestParam("browser") String browser, HttpServletRequest request, HttpSession session) {
        // 取出 session 中的 browser
        Object sessionBrowser = session.getAttribute("browser");

        if (sessionBrowser == null) {
            log.debug("不存在 session, 设置browser=" + browser);
            session.setAttribute("browser", browser);
        } else {
            log.debug("存在 session, browser=" + sessionBrowser.toString());
        }

        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                log.debug(cookie.getName() + " : " + cookie.getValue());
            }
        }
        return ResultUtil.success();
    }
}
