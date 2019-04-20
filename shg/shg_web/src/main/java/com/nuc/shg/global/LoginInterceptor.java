package com.nuc.shg.global;

import org.springframework.http.HttpRequest;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/***
 *  ClassName : LoginInterceptor
 *  Author    : lin
 *  Date      : 2019/4/17 20:28    
 *  Remark    : 登录拦截器
 */

public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        //判断url地址是否有login，如果有直接放行
        String url = httpServletRequest.getRequestURI();
        if (url.toLowerCase().indexOf("login")>=0){
            return true;
        }

        if (url.toLowerCase().indexOf("android")>=0){
            return true;
        }

        //判断全局Session对象是否有当前管理员登录，如果有直接放行
        HttpSession session = httpServletRequest.getSession();
        if (session.getAttribute("admin")!=null){
            return true;
        }

        //条件都不符合返回登录界面
        httpServletResponse.sendRedirect("/toLogin");
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
