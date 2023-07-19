package com.algaworks.ecommerce.hibernate;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;

public class TenantFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException { }

    @Override
    public void doFilter(ServletRequest httpServletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) httpServletRequest;

        String serverName = request.getServerName();
        String subdomain = serverName.substring(0, serverName.indexOf("."));

        EcmCurrentTenantIdentifierResolver.setTenantIdentifier(subdomain + "_ecommerce");

        filterChain.doFilter(httpServletRequest, servletResponse);
    }

    @Override
    public void destroy() {}
}
