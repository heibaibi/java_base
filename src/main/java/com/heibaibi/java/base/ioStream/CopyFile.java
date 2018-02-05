package com.heibaibi.java.base.ioStream;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.heibaibi.java.base.bean.*;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.*;

public class CopyFile<T> {

    @Test
    public void copyByFileStream() {
        long beginTime = System.currentTimeMillis();
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {

            fis = new FileInputStream("E:\\desc\\hk_ws.zip");
            fos = new FileOutputStream("E:\\src\\hk_ws.zip");
//根据刚刚测试数据并不是数据越大，执行任务越快。
            byte[] bytes = new byte[1024];
            int len = -1;
            while ((len = fis.read(bytes)) != -1) {
                fos.write(bytes, 0, len);
            }
        } catch (Exception e) {
            throw new RuntimeException("文件复制异常");
        } finally {
            try {
                fis.close();
                fos.close();
            } catch (IOException e) {
                throw new RuntimeException("输入输出流关闭异常");
            }
        }
        long endTimes = System.currentTimeMillis();
        System.out.println(endTimes - beginTime);
    }

    @Test
    public String readTextToString(String path, Integer size) {
        FileInputStream fis = null;
        String result = "";
        try {
            fis = new FileInputStream(path);
            byte[] bytes = new byte[1024 * size];
            int len = -1;
            while ((len = fis.read(bytes)) != -1) {
                result = result + new String(bytes);
            }
        } catch (Exception e) {
            throw new RuntimeException("文件复制异常");
        } finally {
            try {
                fis.close();
            } catch (IOException e) {
                throw new RuntimeException("输入输出流关闭异常");
            }
        }
//        去除换行符号。
        JSONArray objects = JSONArray.parseArray(result);
        result = objects.toString();

        return result;
    }

}
