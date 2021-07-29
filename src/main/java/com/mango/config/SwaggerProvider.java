package com.mango.config;

import org.springframework.web.reactive.config.WebFluxConfigurer;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.List;

/**
 * swagger聚合
 *
 * @author xs.Liu
 * @version 1.0.0
 * @since 2021/7/26 14:26
 */
public class SwaggerProvider implements SwaggerResourcesProvider, WebFluxConfigurer {

    /**
     * swagger2默认的url后缀
     */
    public static final String API_URI = "/v2/api-docs";

    @Override
    public List<SwaggerResource> get() {
        return null;
    }
}
