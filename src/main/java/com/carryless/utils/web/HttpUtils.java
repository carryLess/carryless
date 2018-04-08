package com.carryless.utils.web;

import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author  carryLess
 * @version 1.0
 * @date    2018-04-08
 */
public class HttpUtils {

    private HttpUtils(){}

    /**
     * 根据传入的HttpServletRequest，获取当前请求的IP地址
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request){
        String ip = request.getHeader("X-Real-IP");
        if ((!StringUtils.isBlank(ip)) && (!"unknown".equalsIgnoreCase(ip))) {
            return ip;
        }
        ip = request.getHeader("X-Forwarded-For");
        if ((!StringUtils.isBlank(ip)) && (!"unknown".equalsIgnoreCase(ip)))
        {
            int index = ip.indexOf(',');
            if (index != -1) {
                return ip.substring(0, index);
            }
            return ip;
        }
        return request.getRemoteAddr();
    }
}
