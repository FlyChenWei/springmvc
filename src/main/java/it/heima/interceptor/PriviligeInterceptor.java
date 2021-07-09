package it.heima.interceptor;

import it.heima.domain.User;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//最后进行拦截器配置
public class PriviligeInterceptor implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //查看session中有无用户数据
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");

        if(user==null){
            response.sendRedirect(request.getContextPath()+"/login.jsp");
            return false;//拦截
        }
        return true;
    }
}
