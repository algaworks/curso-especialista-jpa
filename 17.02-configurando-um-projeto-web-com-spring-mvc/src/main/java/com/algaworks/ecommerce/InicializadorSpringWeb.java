package com.algaworks.ecommerce;

import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.*;
import java.util.EnumSet;

public class InicializadorSpringWeb implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();

        applicationContext.scan(InicializadorSpringWeb.class.getPackage().getName());

        servletContext.addListener(new ContextLoaderListener(applicationContext));
        servletContext.addListener(new RequestContextListener());

        FilterRegistration.Dynamic characterEncodingFilter = servletContext
                .addFilter("characterEncodingFilter", characterEncodingFilter());
        characterEncodingFilter.setAsyncSupported(true);
        characterEncodingFilter.addMappingForServletNames(dispatcherTypes(), false, "dispatcher");

        FilterRegistration.Dynamic openEntityManagerInViewFilter = servletContext
                .addFilter("openEntityManagerInViewFilter", openEntityManagerInViewFilter());
        openEntityManagerInViewFilter.setAsyncSupported(true);
        openEntityManagerInViewFilter.addMappingForServletNames(dispatcherTypes(), false, "dispatcher");

        ServletRegistration.Dynamic dispatcher = servletContext
                .addServlet("dispatcher", dispatcherServlet(applicationContext));
        dispatcher.setAsyncSupported(true);
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");
    }

    private DispatcherServlet dispatcherServlet(WebApplicationContext applicationContext) {
        return new DispatcherServlet(applicationContext);
    }

    private CharacterEncodingFilter characterEncodingFilter() {
        CharacterEncodingFilter filter = new CharacterEncodingFilter();

        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);

        return filter;
    }

    private OpenEntityManagerInViewFilter openEntityManagerInViewFilter() {
        OpenEntityManagerInViewFilter filter = new OpenEntityManagerInViewFilter();

        return filter;
    }

    private EnumSet<DispatcherType> dispatcherTypes() {
        return EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD,
                DispatcherType.INCLUDE, DispatcherType.ASYNC);
    }
}