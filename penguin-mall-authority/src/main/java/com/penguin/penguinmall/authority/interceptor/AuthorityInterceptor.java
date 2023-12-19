package com.penguin.penguinmall.authority.interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;

import com.penguin.penguinmall.authority.annotation.BmsRole;
import com.penguin.penguinmall.exception.authority.InvalidTokenException;
import com.penguin.penguinmall.exception.authority.PermissionDeniedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class AuthorityInterceptor implements HandlerInterceptor {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.debug("我已进入拦截器:{}", new Date());
        Cookie[] cookies = request.getCookies();
        String token = null;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("token")) {
                token = cookie.getValue();
            }
        }
        Boolean exist = stringRedisTemplate.hasKey(token);
        if (exist == false) {
            throw new InvalidTokenException("无效的token");
        }
        String salt = stringRedisTemplate.opsForValue().get(token);

        DecodedJWT decodedJWT = null;
        try {
            decodedJWT = JWT.require(Algorithm.HMAC256(salt)).build().verify(token);
        } catch (TokenExpiredException e) {
            log.debug("当前的token已经过期....,准备续期.....");
            Long expire = stringRedisTemplate.getExpire(token, TimeUnit.MILLISECONDS);
            if (expire > 0) {
                decodedJWT = JWT.decode(token);
                String newToken = JWT.create()
                        .withClaim("userId", decodedJWT.getClaim("userId").asString())
                        .withClaim("username", decodedJWT.getClaim("username").asString())
                        .withClaim("roleId", decodedJWT.getClaim("roleId").asString())
                        .withExpiresAt(new Date(System.currentTimeMillis() + expire))
                        .sign(Algorithm.HMAC256(salt));
                stringRedisTemplate.delete(token);
                stringRedisTemplate.opsForValue().set(newToken, salt);
                stringRedisTemplate.expire(newToken, expire / 1000, TimeUnit.SECONDS);

                Cookie tokenCookie = new Cookie("token", newToken);
                tokenCookie.setPath("/");
                response.addCookie(tokenCookie);
            }

        } catch (JWTVerificationException e) {
            throw new JWTVerificationException("令牌验证出错，拒绝访问");
        }

        String roleId = decodedJWT.getClaim("roleId").asString();

        HandlerMethod method = (HandlerMethod) handler;
        BmsRole bmsRole = method.getMethodAnnotation(BmsRole.class);
        String requiredRolId = bmsRole.value();
        if (!requiredRolId.equals("0") && !requiredRolId.contains(roleId)) {
            throw new PermissionDeniedException("您没有足够的权限");
        }
        return true;
    }
}
