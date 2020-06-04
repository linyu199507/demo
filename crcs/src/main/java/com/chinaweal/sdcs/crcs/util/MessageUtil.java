package com.chinaweal.sdcs.crcs.util;
import java.nio.charset.Charset;
import org.springframework.util.StreamUtils;
//http   get
import java.net.*;
import java.util.Map;
import java.util.Map.Entry;
/**
 * @param
 * @author mo sheng xin
 * @since
 **/
public class MessageUtil {
    //http请求get方式
    public static String sendMessage(String httpURL, Map<String, String> params) throws Exception {
        //newg一个URL连接对象url
        URL url = new URL(httpURL);
        //创建连接
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        //设置请求方式(注意大写)get
        conn.setRequestMethod("GET");
        //设置需要输出，默认false不输出
        conn.setDoOutput(true);
        //判断是否有参数.在调用的地方要给出参数值，用map保存存参数
        if (params != null && params.size() > 0) {
            StringBuilder sb = new StringBuilder();
            for (Entry<String, String> entry : params.entrySet()) {
                sb.append("&").append(entry.getKey()).append("=").append(entry.getValue());
            }
            //sb.substring(1)去除最前面的&
            conn.getOutputStream().write(sb.substring(1).toString().getBytes("gbk")); //设置参数编码
        }
        //发送请求到服务器
        conn.connect();
        //获取远程响应的内容.
        String responseContent = StreamUtils.copyToString(conn.getInputStream(), Charset.forName("utf-8"));
        System.out.println("message");
        System.out.println(responseContent);
        conn.disconnect();
        return responseContent;
    }
}
