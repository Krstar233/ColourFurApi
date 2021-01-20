package com.colourfur.security.utils;

import com.colourfur.security.entity.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/***
 * 配置Token信息的工具类
 */
public class TokenUtil {
    public static Long getUidFromToken(HttpServletRequest request){
        Map<String, Object> m = getTokenBody(request);
        if (m == null)
            return null;
        Long uid = Long.parseLong(m.get("uid").toString());
        return uid;
    }
    public static Map<String, Object> getTokenBody(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        for (Cookie c : cookies){
            if (c.getName().equals("clf_token")){
                String token = c.getValue();
                int verify = JwtUtil.verifyToken(token);
                if (verify != 0)
                    return null;
                return JwtUtil.parseToken(token);
            }
        }
        return null;
    }
    public static void setToken(User user, HttpServletResponse response){
        Long uid = user.getId();
        Map<String, Object> m = new HashMap<>();
        m.put("uid", uid);
        m.put("clf_group_id", user.getClfGroupId());
        String token = JwtUtil.createToken(m);
        Cookie cookie = new Cookie("clf_token", token);
        cookie.setMaxAge(60*60*24);         //一天
        response.addCookie(cookie);
    }
    public static void removeToken(HttpServletResponse response){
        Cookie cookie=new Cookie("clf_token",null); //假如要删除名称为username的Cookie
        cookie.setMaxAge(0); //立即删除型
        cookie.setPath("/"); //项目所有目录均有效，这句很关键，否则不敢保证删除
        response.addCookie(cookie); //重新写入，将覆盖之前的
    }
}