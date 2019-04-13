package com.zlx.bangbang.config;

import com.zlx.bangbang.constants.Constant;
import com.zlx.bangbang.domain.User;
import com.zlx.bangbang.enums.ResultEnum;
import com.zlx.bangbang.exceptions.SubstituteException;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class AuthenticationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        HttpSession session = httpServletRequest.getSession();

        User user = (User) session.getAttribute(Constant.CURRENT_USER);
        if (user == null) {
            throw new SubstituteException(ResultEnum.NEED_LOGIN);
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
