package com.mango.service.impl;

import com.mango.service.PermissionService;
import org.springframework.http.HttpCookie;
import org.springframework.util.MultiValueMap;

/**
 * 统一鉴权service
 *
 * @author xs.Liu
 * @version 1.0.0
 * @since 2021/8/31 10:35
 */
public class PermissionServiceImpl implements PermissionService {

    /**
     * 判断url是否不需要授权
     *
     * @param url 请求url
     * @return true为不需要授权
     */
    @Override
    public boolean ignoreAuthentication(String url) {
        return false;
    }

    /**
     * 获取正确的token
     *
     * @param authentication 权限标识
     */
    @Override
    public String invalidAccessToken(String authentication, String url, MultiValueMap<String, HttpCookie> cookies) {
        return null;
    }

    /**
     * 调用签权服务，判断用户是否有权限
     *
     * @param authentication 权限标识
     * @param url 请求url
     * @param method 请求方法
     * @return true/false
     */
    @Override
    public boolean hasPermission(String authentication, String url, String method) {
        return false;
    }
}
