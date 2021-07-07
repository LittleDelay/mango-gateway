package com.mango.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

/**
 * 网关路由配置表
 *
 * @author xs.Liu
 * @version 1.0.0
 * @since 2021/7/2 21:19
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "config_info_route")
public class GatewayRoute {

    /**
     * id
     */
    @Id
    private Integer id;

    /**
     * uri路径
     */
    private String uri;

    /**
     * 路由id
     */
    private String routeId;

    /**
     * 判定器
     */
    private String predicates;

    /**
     * 过滤器
     */
    private String filters;

    /**
     * 描述
     */
    private String description;

    /**
     * 是否拦截 1 是 0 否
     */
    private Integer intercept;

    /**
     * 状态：1-有效，2-无效
     */
    private Integer status;

    /**
     * 排序
     */
    public Integer orders;

    /**
     * 创建时间
     */
    private LocalDate createTime;

    /**
     * 更新时间
     */
    private LocalDate updateTime;

}
