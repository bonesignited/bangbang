package com.zlx.bangbang.utils;

import com.zlx.bangbang.enums.BaseEnum;
import com.zlx.bangbang.enums.CodeEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * 公用工具类
 * Created By Cx On 2018/11/6 11:30
 */
public class CommonUtil {

    /**
     * 获取客户端IP
     * <p>
     * 如果用反向代理软件，将http://ip:port/ 的URL反向代理为http://www.xxx.com/ 的URL时，
     * 当我们访问http://www.xxx.com/index.jsp/ 时，并不是我们访问服务器,
     * 而是先由代理服务器去访问http://ip:port/index.jsp ，代理服务器再将访问到的结果返回给我们的浏览器，
     * 所以通过request.getRemoteAddr()的方法获取的IP实际上是代理服务器的地址
     * 即：127.0.0.1　或　192.168.1.110，不是客户端的真实ＩＰ。
     * <p>
     * 但在转发请求的HTTP头信息中，增加了X－FORWARDED－FOR信息。用以跟踪原有的客户端IP地址和原来客户端请求的服务器地址。
     * 有时访问http://www.xxx.com/index.jsp/ 时，返回的IP地址始终是unknown，不是如上所说的127.0.0.1 或 92.168.1.110，
     */
    public static String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
            //多次反向代理后会有多个ip值，第一个ip才是真实ip
            int index = ip.indexOf(",");
            if (index != -1) {
                return ip.substring(0, index);
            } else {
                return ip;
            }
        }
        ip = request.getHeader("X-Real-IP");
        if (StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
            return ip;
        }
        return request.getRemoteAddr();
    }

    /**
     * 对字符串md5加密
     */
    public static String getMD5(String str) {
        return DigestUtils.md5DigestAsHex(str.getBytes());
    }

    /**
     * 遍历enumClass获取和EnumString匹配的Enum并返回
     */
    public static <T extends BaseEnum> T getEnum(String enumString, Class<T> enumClass) {
        for (T baseEnum : enumClass.getEnumConstants()) {
            if (baseEnum.toString().equals(enumString)) return baseEnum;
        }
        return null;
    }

    //遍历枚举类，查询符合code值的枚举,并返回该枚举
    public static <T extends CodeEnum> T getEnum(Integer code, Class<T> enumClass) {
        //getEnumConstants()作用：
        //以声明顺序返回一个数组，该数组包含构成此 Class 对象所表示的枚举类的值，或者在此 Class 对象不表示枚举类型时返回 null
        for (T each : enumClass.getEnumConstants()) {
            if (each.getCode().equals(code)) return each;
        }
        return null;
    }

}

