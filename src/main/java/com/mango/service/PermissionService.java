package com.mango.service;

import org.springframework.http.HttpCookie;
import org.springframework.util.MultiValueMap;

/**
 * 统一鉴权service
 *
 * @author xs.Liu
 * @version 1.0.0
 * @since 2021/6/28 16:17
 */
public interface PermissionService {

    /**
     * 判断url是否不需要授权
     *
     * @param url 请求url
     * @return true为不需要授权
     */
    boolean ignoreAuthentication(String url);

    /**
     * 获取正确的token
     *
     * @param authentication 权限标识
     */
    String invalidAccessToken(String authentication,String url, MultiValueMap<String, HttpCookie> cookies);

    /**
     * 调用签权服务，判断用户是否有权限
     *
     * @param authentication 权限标识
     * @param url 请求url
     * @param method 请求方法
     * @return true/false
     */
    boolean hasPermission(String authentication, String url, String method);

}
