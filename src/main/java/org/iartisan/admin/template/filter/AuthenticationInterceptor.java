package org.iartisan.admin.template.filter;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.support.RequestContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *
 * @author King
 * @since 2017/4/13
 */
public class AuthenticationInterceptor extends HandlerInterceptorAdapter {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //如果用户没有登陆则跳转到 登陆页面
       /* Object object = request.getSession().getAttribute(WebConstants.user);
        if (null == object) {
            response.sendRedirect(request.getContextPath() + "/index");
            return false;
        }*/
/*        RequestContext context = new RequestContext();
        context.getContextPath()*/
        return true;
    }

}
