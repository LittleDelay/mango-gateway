package com.mango.mapper;

import com.mango.core.servlet.BaseMapper;
import com.mango.entity.GatewayRoute;
import org.apache.ibatis.annotations.Mapper;

/**
 * 网关路由配置Mapper
 *
 * @author xs.Liu
 * @version 1.0.0
 * @since 2021/7/29 17:47
 */
@Mapper
public interface GatewayRouterMapper extends BaseMapper<GatewayRoute> {
}
