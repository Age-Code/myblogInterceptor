package org.example.myblog.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.example.myblog.exception.NoAuthException;
import org.example.myblog.util.TokenFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@RequiredArgsConstructor
public class DefaultInterceptor implements HandlerInterceptor {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final String prefix = "Bearer ";
    final TokenFactory tokenFactory;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("preHandle");

        Long reqUserId = null;


        String accessToken = request.getHeader("Authorization");

        logger.info("Access token: " + request.getHeader("Authorization"));

        if(accessToken != null && accessToken.startsWith(prefix) && accessToken.equals(prefix + null)){
            accessToken = accessToken.substring(prefix.length());

            reqUserId = tokenFactory.validateAccessToken(accessToken);

            if(reqUserId == null || reqUserId == -100){
                throw new NoAuthException("Invalid AccessToken");
            }
        }

        request.setAttribute("reqUserId", reqUserId);

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        logger.info("postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        logger.info("afterCompletion");
    }

}
