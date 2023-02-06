package com.release.utils;

import javax.servlet.http.HttpServletResponse;

/**
 * @author yancheng
 * @since 2023/1/31
 */
public class WebUtils {

    /**
     * 将字符串渲染到客户端
     * @param response
     * @param string
     * @return
     */
    public static String renderString(HttpServletResponse response,String string){
        try {
            response.setStatus(200);
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(string);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
