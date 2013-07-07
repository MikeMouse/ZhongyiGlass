package com.zhongyi.glass.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CharacterEncodingFilter implements Filter {

    protected String encoding = null;

    protected boolean ignore = true;

    public void destroy() {
        this.encoding = null;
    }
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {

        if (!ignore) {
            if (encoding != null) {
                request.setCharacterEncoding(encoding);
                response.setCharacterEncoding(encoding);
            }
        }
        chain.doFilter(request, response);
    }

    public void init(FilterConfig filterConfig) throws ServletException {

        this.encoding = filterConfig.getInitParameter("encoding");
        String value = filterConfig.getInitParameter("ignore");
        ignore = "true".equalsIgnoreCase(value) || "yes".equalsIgnoreCase(value);
    }
}