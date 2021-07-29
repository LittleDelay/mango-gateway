package com.mango.service;

import org.springframework.cloud.gateway.route.RouteDefinition;

import java.util.Collection;

/**
 * 网关的路由处理
 *
 * @author xs.Liu
 * @version 1.0.0
 * @since 2021/7/26 14:34
 */
public interface GatewayRouteService {

    /**
     * 删除路由
     * @param routerId 路由ID
     */
    void removeRouter(String routerId);


    /**
     * 新增路由
     * @param routeDefinition 路由对象
     */
    void saveRouter(RouteDefinition routeDefinition);

    /**
     * 获取router列表
     * @return router列表
     */
    Collection<RouteDefinition> getRouteDefinitions();

    /**
     * 从数据库重载配置
     */
    void loadConfig();

}
