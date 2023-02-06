package com.release.utils;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

/**
 * @author yancheng
 * @since 2023/1/31
 */
@Slf4j
public class JwtTokenUtil {
    // Token请求头
    public static final String TOKEN_HEADER = "Authorization";
    // Token前缀
    public static final String TOKEN_PREFIX = "Bearer ";
    //token过期时间
    public static final Long TOKEN_EXPIRE= 604800000L;
    public static Long studentId;

    /**
     * 生成token
     * @param studentId
     * @return
     */
    public static String getToken(Long studentId) {
        Date start = new Date();
        long currentTime = System.currentTimeMillis() + TOKEN_EXPIRE;
        Date end = new Date(currentTime);
        String token = "";
        Algorithm algorithm = Algorithm.HMAC256("!Secret");
        token = JWT.create()
                .withClaim("studentId", studentId)
                .withIssuedAt(start)
                .withExpiresAt(end)
                .sign(algorithm);
        return token;
    }

    /**
     * 生成B端token
     * @param businessUserVO
     * @return
     */
//    public static String getToBToken(BusinessUserVO businessUserVO) {
//        Date start = new Date();
//        long currentTime = System.currentTimeMillis() + TOKEN_EXPIRE;
//        Date end = new Date(currentTime);
//        String token = "";
//        Algorithm algorithm = Algorithm.HMAC256("!Secret");
//        token = JWT.create()
//                .withClaim("businessUserVO", JSON.toJSONString(businessUserVO))
//                .withIssuedAt(start)
//                .withExpiresAt(end)
//                .sign(algorithm);
//        return token;
//    }

//    public static void main(String[] args) {
//        BusinessUserVO businessUserVO =new BusinessUserVO();
//        businessUserVO.setYearName(2021);
//        businessUserVO.setSchoolId(370526384659062784L);
//        System.out.println("Bearer "+getToBToken(businessUserVO));
//
//    }

    /**
     * 从Token中获取studentId
     */
//    public static Long getStudentId() {
//        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
//                .getRequest();
//        String header = request.getHeader(TOKEN_HEADER);
//        if (header == null || "".equals(header)){
//            return JwtTokenUtil.studentId;
//        }
//        String token = header.split(" ")[1];
//        DecodedJWT jwt = JWT.decode(token);
//        Map<String, Claim> map = jwt.getClaims();
//        return map.get("studentId").as(Long.class);
//    }

    public static Long parseJWT(String token) {
        DecodedJWT jwt = JWT.decode(token);
        Map<String, Claim> map = jwt.getClaims();
        return map.get("studentId").as(Long.class);
    }

    /**
     * 从Token中获取BusinessUserVO
     */
//    public static BusinessUserVO getBusinessUser() {
//        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
//                .getRequest();
//        String header = request.getHeader(TOKEN_HEADER);
//        if (header == null || "".equals(header)){
//            return null;
//        }
//        String token = header.split(" ")[1];
//        DecodedJWT jwt = JWT.decode(token);
//        Map<String, Claim> map = jwt.getClaims();
//        String str=map.get("businessUserVO").toString();
//        BusinessUserVO businessUserVO=JSON.parseObject(JSON.parse(str).toString(),BusinessUserVO.class);
//        return businessUserVO;
//    }

    /**
     * 单元测试使用
     * @param studentId
     */
    public static void setStudentId(Long studentId){
        JwtTokenUtil.studentId = studentId;
    }

}
