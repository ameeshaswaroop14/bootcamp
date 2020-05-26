package com.commerceApp.commerceApp.filter;

import com.commerceApp.commerceApp.bootloader.Bootstrap;
import com.commerceApp.commerceApp.models.MongoInfo;
import com.commerceApp.commerceApp.repositories.CustomMongoRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger logger = LoggerFactory.getLogger(CustomFilter.class);


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

        mongoInfo.setPath(req.getRequestURI());
        mongoInfo.setAccessToken(req.getHeader("Authorization"));
        mongoInfo.setDate(date);
        customMongoRepo.save(mongoInfo);

        logger.info(req.getMethod());
        logger.info(req.getRequestURI());
        logger.info(req.getUserPrincipal().getName());
        logger.info(String.valueOf(req.getRequestURL()));
        logger.info(req.getHeader("Authorization"));
        logger.info(String.valueOf(date));


        chain.doFilter(request, response);

    }

    @Override
    public void init(FilterConfig filterconfig) throws ServletException {
    }
}









