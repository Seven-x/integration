package com.syx.springboot.inredis.test.utils;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * 解析request请求中的body
 * @author syx
 * @date 14:14  2019/10/23
 */
public class HttpHelper {
    /**
     * 从request中获取body
     * @author syx
     * @param request
     */
    public static String getBodyStringByInputStream(HttpServletRequest request) {
        InputStream inputStream = null;
        BufferedReader bufferedReader = null;
        StringBuilder sb = new StringBuilder();
        try {
            inputStream = request.getInputStream();
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bufferedReader!= null){
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return sb.toString();
    }

    public static String getBodyStringByReader(HttpServletRequest request){
        BufferedReader bufferedReader = null;
        StringBuilder sb = new StringBuilder();
        try {
            bufferedReader = request.getReader();
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (bufferedReader!= null){
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }
}
