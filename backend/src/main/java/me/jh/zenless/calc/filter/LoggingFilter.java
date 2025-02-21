package me.jh.zenless.calc.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoggingFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(LoggingFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("LoggingFilter init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //요청이 들어올 때 API에 대한 요청만 로깅
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String requestURI = request.getRequestURI();

        if (requestURI.startsWith("/static/") || requestURI.startsWith("/css/") || requestURI.startsWith("/js/") || requestURI.startsWith("/images/")) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        try {
            logger.info("요청 [{}]", requestURI);

            filterChain.doFilter(servletRequest, servletResponse);

            logger.info("응답 [{}]", requestURI);
        }
        catch (Exception e) {
            logger.error("에러 발생 [{}]: {}", requestURI, e.getMessage());

            //에러 발생 시 500 에러 반환
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("서버 에러");
        }


    }

    @Override
    public void destroy() {
        logger.info("LoggingFilter destroy");
    }
}
