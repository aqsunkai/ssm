package com.cn.util;


import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


public class InterceptorUtil implements HandlerInterceptor{

	//进入Handler方法之前执行
    //可以用于身份认证、身份授权。如果认证没有通过表示用户没有登陆，需要此方法拦截不再往下执行，否则就放行
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("InterceptorUtil...........preHandle");
		Date date = (Date) request.getSession().getAttribute("todayDate");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(sdf.format(date));
		/*String user= (String) request.getSession().getAttribute("user");
		if(user != null){
			return true;
		}
		request.getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(request, response);
		//true表示放行，false表示不放行
		return false;*/
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("InterceptorUtil...........postHandle");
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("InterceptorUtil...........afterCompletion");
	}

}
