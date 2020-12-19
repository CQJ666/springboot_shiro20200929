package com.example.springboot_shiro20200929.exceptionHandler;

import com.alibaba.fastjson.JSONObject;
import com.example.springboot_shiro20200929.bean.constant.ReturnMessage;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 王飞
 * @Date: 2020/12/19/16:32
 * @Description:用于自定义过滤器层，
 */
public class SendFailMessage {

    public static void sendMessage(int code, String msg, ServletResponse response){
        HttpServletResponse httpServletResponse = (HttpServletResponse)response;
        httpServletResponse.setContentType("application/json; charset=utf-8");
        httpServletResponse.setCharacterEncoding("UTF-8");
        try {
            httpServletResponse.getWriter().print(JSONObject.toJSONString(ReturnMessage.failWithMsg(code,msg)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
