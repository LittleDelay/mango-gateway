package com.mango.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 默认配置
 *
 * @author xs.Liu
 * @version 1.0.0
 * @since 2021/6/28 16:11
 */
@Data
@Component
@RefreshScope
@ConfigurationProperties(prefix = "mango.gateway")
public class GatewayConfiguration {

    /**
     * 不验证权限的url
     */
    public List<String> ignoreUrl;

}
