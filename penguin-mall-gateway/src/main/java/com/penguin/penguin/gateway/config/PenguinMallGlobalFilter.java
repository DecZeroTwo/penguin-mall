//package com.penguin.penguin.gateway.config;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.penguin.penguinmall.common.result.HttpResp;
//import lombok.SneakyThrows;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.cloud.gateway.filter.GatewayFilterChain;
//import org.springframework.cloud.gateway.filter.GlobalFilter;
//import org.springframework.core.Ordered;
//import org.springframework.core.io.buffer.DataBuffer;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.server.reactive.ServerHttpRequest;
//import org.springframework.http.server.reactive.ServerHttpResponse;
//import org.springframework.stereotype.Component;
//import org.springframework.web.server.ServerWebExchange;
//import reactor.core.publisher.Mono;
//
//import java.nio.charset.StandardCharsets;
//import java.util.Objects;
//
//@Component
//@Slf4j
//public class PenguinMallGlobalFilter implements GlobalFilter, Ordered {
//
//    @SneakyThrows
//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//        ServerHttpRequest request = exchange.getRequest();
//        ServerHttpResponse response = exchange.getResponse();
//        log.debug("我进入过滤中.....");
//        if (request.getPath().toString().contains("/user")) {
//            return chain.filter(exchange);
//        }
//        ObjectMapper objectMapper = new ObjectMapper();
//        DataBuffer dataBuffer = null;
//
//        if (Objects.isNull(request.getCookies().get("token"))) {
//            dataBuffer = response.bufferFactory().wrap(
//                    objectMapper.writeValueAsString(
//                                    HttpResp.failed("用户未登录,禁止访问"))
//                            .getBytes(StandardCharsets.UTF_8));
//            response.getHeaders().add(HttpHeaders.CONTENT_TYPE, "application/json;charset=UTF-8");
//            return response.writeWith(Mono.just(dataBuffer));
//        }
//        return chain.filter(exchange);
//    }
//
//    @Override
//    public int getOrder() {
//        return 0;
//    }
//}
