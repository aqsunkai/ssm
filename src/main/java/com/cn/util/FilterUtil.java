package com.cn.util;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FilterUtil implements Filter{

	@SuppressWarnings("unused")
	private FilterConfig filterConfig;
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
		System.out.println("过滤器Filter初始化");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		if (!(request instanceof HttpServletRequest) || !(response instanceof HttpServletResponse)) {
			throw new ServletException("FilterUtil just supports HTTP requests");
		}
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		httpRequest.setCharacterEncoding(this.filterConfig.getInitParameter("encoding"));
		httpResponse.setCharacterEncoding(this.filterConfig.getInitParameter("encoding"));
		httpRequest.getSession().setAttribute("todayDate", new Date());
		chain.doFilter(httpRequest, httpResponse);
	}

	@Override
	public void destroy() {
		System.out.println("过滤器Filter销毁");
	}

}
