package com.commerceApp.commerceApp.filter;

import com.commerceApp.commerceApp.models.MongoInfo;
import com.commerceApp.commerceApp.repositories.CustomMongoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component

@Order(1)
public class CustomFilter implements Filter {

    @Autowired
    CustomMongoRepo customMongoRepo;


    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        java.util.Date date = new java.util.Date();
        MongoInfo mongoInfo = new MongoInfo();
//        mongoInfo.setUsername(req.getUserPrincipal().getName());
        mongoInfo.setPath(req.getRequestURI());
        mongoInfo.setAccessToken(req.getHeader("Authorization"));
        mongoInfo.setDate(date);
        customMongoRepo.save(mongoInfo);


        System.out.println(req.getMethod());
        System.out.println(req.getRequestURI());
        System.out.println(req.getUserPrincipal().getName());
        System.out.println(req.getRequestURL());
        System.out.println(req.getHeader("Authorization"));
        System.out.println(date );



        chain.doFilter(request, response);

    }

    @Override
    public void init(FilterConfig filterconfig) throws ServletException {
    }
}







