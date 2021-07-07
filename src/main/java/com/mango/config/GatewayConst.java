package com.mango.config;

/**
 * 网关的常量配置
 *
 * @author xs.Liu
 * @version 1.0.0
 * @since 2021/6/28 16:12
 */
public class GatewayConst {

    /**
     * 路由是否要进行权限认证标识
     */
    public static final String ROUTER_INTERCEPT = "intercept";

    /**
     * 不需要进行权限认证标识
     */
    public static final Integer ROUTER_INTERCEPT_OK = 0;

    /**
     * 路由说明标识
     */
    public static final String ROUTER_DESC = "description";

}
