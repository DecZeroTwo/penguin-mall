package com.wnhz.older.authority.interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.wnhz.older.authority.annotation.BmsRole;
import com.wnhz.older.domain.dao.BlackListDao;
import com.wnhz.older.domain.entity.BlackList;
import com.wnhz.older.exception.authority.InvalidTokenException;
import com.wnhz.older.exception.authority.PermissionDeniedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class BlackListInterceptor implements HandlerInterceptor {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private BlackListDao blackListDao;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String ip = request.getHeader("x-forwarded-for");
        if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
            // 多次反向代理后会有多个ip值，第一个ip才是真实ip
            if (ip.indexOf(",") != -1) {
                ip = ip.split(",")[0];
            }
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (stringRedisTemplate.opsForSet().isMember("blackList", ip)) {
            response.sendRedirect("http://localhost:9099/blackList.html");
            return false;
        }

        if (!redisTemplate.hasKey("ipCount:" + ip)) {
            redisTemplate.opsForValue().set("ipCount:" + ip, 1L, 60, TimeUnit.SECONDS);
        } else {
            Long count = redisTemplate.opsForValue().increment("ipCount:" + ip);
            if (count >= 100L) {
                stringRedisTemplate.opsForSet().add("blackList", ip);
                blackListDao.insert(new BlackList(null, ip, 0));
                response.sendRedirect("http://localhost:9099/PerInsufficient.html");
                return false;
            }
        }
        return true;
    }
}
