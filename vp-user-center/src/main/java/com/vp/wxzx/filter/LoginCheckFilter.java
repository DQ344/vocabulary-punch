package com.vp.wxzx.filter;

import com.alibaba.fastjson.JSONObject;
import com.vp.wxzx.entity.ResultBody;
import com.vp.wxzx.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * @author qlh
 * @date 2023/10/14 17:14
 * @description 登录拦截器
 */

@Slf4j
@WebFilter(urlPatterns = "/*")
public class LoginCheckFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        // 获取 url
        String url = req.getRequestURL().toString();
        log.info("请求的 url:{}", url);

        // 判断请求 url 中是否包含login，如果包含说明是登录操作，放行
        if(url.contains("login")){
            log.info("登录操作，放行");
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        // 获取请求头中的令牌（token）
        String jwt = req.getHeader("token");

        // 判断令牌是否存在，如果不存在，返回错误结果（未登录）
        if(!StringUtils.hasLength(jwt)){
            log.info("请求头token为空，返回未登录信息");
            ResultBody error = ResultBody.error().message("NOT_LOGIN");
            // 手动转换 对象 -- json --> 阿里巴巴 fastJSON
            String notLogin = JSONObject.toJSONString(error);
            resp.getWriter().write(notLogin);
            return;
        }

        // 解析 token，如果解析失败，返回错误结果（未登录）
        try {
            JwtUtils.parseJWT(jwt);

        }catch (Exception e){ // jwt 解析失败
            e.printStackTrace();
            log.info("解析令牌失败，返回未登录");
            ResultBody error = ResultBody.error().message("NOT_LOGIN");
            // 手动转换 对象 -- json --> 阿里巴巴 fastJSON
            String notLogin = JSONObject.toJSONString(error);
            resp.getWriter().write(notLogin);
            return;
        }
        // 放行
        log.info("令牌合法，放行");
        filterChain.doFilter(servletRequest, servletResponse);

    }
}
