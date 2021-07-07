package com.mango.config;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.mango.core.bean.response.ErrorResponseData;
import com.mango.core.bean.response.ResponseKit;
import com.mango.core.config.Const;
import com.mango.core.enums.BizExceptionEnum;
import com.mango.core.exception.BizException;
import com.mango.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.Objects;

/**
 * 全局拦截
 *
 * @author xs.Liu
 * @version 1.0.0
 * @since 2021/6/28 16:15
 */
public class GatewayFilter implements GlobalFilter, Ordered {

    protected static ThreadLocal<String> ip = new ThreadLocal<>();

    @Autowired
    private PermissionService permissionService;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String method = request.getMethodValue();
        String url = request.getPath().value();
        MultiValueMap<String, HttpCookie> cookies = request.getCookies();
        request.getCookies().keySet().forEach(str -> {
            if (Objects.equals("ServerIp", str)) {
                HttpCookie first = cookies.getFirst(str);
                ip.set(first != null ? first.getValue() : "");
            }
        });
        //验证url是否需要权限
        boolean isAuth = permissionService.ignoreAuthentication(url);
        if (isAuth){
            return chain.filter(exchange);
        }

        Route route = exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR);
        if (route != null) {
            Map<String, Object> metadata = route.getMetadata();
            if (Objects.equals(GatewayConst.ROUTER_INTERCEPT_OK, metadata.get(GatewayConst.ROUTER_INTERCEPT))) {
                return chain.filter(exchange);
            }
        }

        String token = request.getHeaders().getFirst(Const.TOKEN_NAME);
        /*
         兼容微信小程序请求文件以及文件预览
         */
        if(url.startsWith("/adminFile/down/")){
            token = request.getQueryParams().getFirst("c");
            if(StrUtil.isNotEmpty(token)){
//                request = exchange.getRequest().mutate().header(Const.TOKEN_NAME, token).build();
            }
        }
        //验证token
        String authentication = permissionService.invalidAccessToken(token,url,request.getCookies());
        if (StrUtil.isEmpty(authentication)){
            throw new BizException(BizExceptionEnum.NOT_TOKEN.getCode(), BizExceptionEnum.NOT_TOKEN.getName());
        }
        //验证有无权限
        boolean permission = permissionService.hasPermission(authentication, url ,method);
        if (!permission) {
            return unauthorized(exchange);
        }
        return chain.filter(exchange);
    }

    /**
     * 网关拒绝，返回401
     *
     * @param
     */
    private Mono<Void> unauthorized(ServerWebExchange serverWebExchange) {
        serverWebExchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        ErrorResponseData result = ResponseKit.fail(BizExceptionEnum.SYSTEM_NO_AUTH.getCode(), BizExceptionEnum.SYSTEM_NO_AUTH.getName());
        DataBuffer buffer = serverWebExchange.getResponse().bufferFactory().wrap(JSON.toJSONString(result).getBytes(CharsetUtil.systemCharset()));
        return serverWebExchange.getResponse().writeWith(Flux.just(buffer));
    }

    @Override
    public int getOrder() {
        return -1;
    }
}
