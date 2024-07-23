package com.example.pds_api.interceptor;

import com.example.pds_api.utils.JWTUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class JWTInterceptor implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = request.getRequestURI();
        // 获取URL的前缀，这里假设前缀以"/"开始
        String prefix = url.substring(0, url.indexOf("/", 1));
//        判断访问的路径前缀是否为"/administrators","/user","/distributor"
//        获取role角色
        String Role = request.getHeader("Role");
        String token = request.getHeader("Authorization");
//        System.out.println("uri"+prefix);
//        System.out.println(Role+token);
//        判断token是否正确，role是否匹配
        if ((prefix.equals("/administrators") || prefix.equals("/user")) && Role.equals("administrators")) {
            Map<String,Object> map = JWTUtils.parseToken(token);
            Object acct = map.get("acct");
            if (acct != null) {
//                System.out.println("管理员访问");
                return true;
            }
        }
        if ((prefix.equals("/user") || prefix.equals("/goods")) && Role.equals("user")) {
            Map<String,Object> map = JWTUtils.parseToken(token);
            Object acct = map.get("acct");
            if (acct != null) {
//                System.out.println("用户访问");
                return true;
            }
        }
        if (prefix.equals("/distributor") && Role.equals("distributor")) {
            Map<String,Object> map = JWTUtils.parseToken(token);
            Object acct = map.get("acct");
            if (acct != null) {
//                System.out.println("分销商访问");
                return true;
            }
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
