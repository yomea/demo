package com.example.demo.jackson.plan1;

import com.example.demo.jackson.plan2.ThreadLocalUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @author wuzhenhong
 * @date 2025/1/27 9:09
 */
public class ClearTlInter implements HandlerInterceptor {

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
        throws Exception {
        ThreadLocalUtil.remove();
    }
}
