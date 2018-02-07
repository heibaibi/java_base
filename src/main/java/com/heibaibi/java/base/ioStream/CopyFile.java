package com.heibaibi.java.base.ioStream;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.heibaibi.java.base.bean.Person;
import org.junit.Test;

import java.io.*;
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
    public void copyByFile() {
        long startTime = System.currentTimeMillis();
        FileReader fileReader = null;
        FileWriter fileWriter=null;
        try {
            fileReader = new FileReader("E:\\desc\\hk_ws.zip");
            fileWriter = new FileWriter("E:\\src\\hk_ws.zip");
            char[] temp = new char[2048];
            int len = -1;
            while ((len=fileReader.read(temp))!=-1){
                fileWriter.write(temp,0,len);
                fileWriter.flush();
            }
        } catch (Exception e) {
            throw new RuntimeException("字符流读文件错误");
        }finally {
            try {
                if(fileReader!=null){
                    fileReader.close();
                }
                if(fileWriter!=null){
                    fileWriter.close();
                }
                long endTimes = System.currentTimeMillis();
                System.out.println(endTimes-startTime);
            } catch (Exception e) {
                throw new RuntimeException("流关闭失败");
            }
        }
    }

    @Test
    public void copyByBufferFileStream(){
        long startTime = System.currentTimeMillis();
        BufferedInputStream bufferedInputStream =null;
        BufferedOutputStream bufferedOutputStream=null;
        try {
            bufferedInputStream= new BufferedInputStream(new FileInputStream("E:\\desc\\hk_ws.zip"));
            bufferedOutputStream= new BufferedOutputStream(new FileOutputStream("E:\\src\\hk_ws.zip"));
            int len=-1;
            byte[] temp = new byte[2048];
            while ((len=bufferedInputStream.read(temp))!=-1){
                bufferedOutputStream.write(temp,0,len);
            }
        } catch (Exception e) {
            throw  new RuntimeException("文件拷贝异常");
        }finally {
            try {
                if(bufferedInputStream!=null){
                    bufferedInputStream.close();
                }
                if(bufferedOutputStream!=null){
                    bufferedOutputStream.close();
                }
                long endTime = System.currentTimeMillis();
                System.out.println(endTime-startTime);
            } catch (IOException e) {
                throw  new RuntimeException("流关闭异常");
            }
        }
    }

    @Test
    public void copyByBufferFile() {
        long startTime = System.currentTimeMillis();
        BufferedReader bufferedReader=null;
        BufferedWriter bufferedWriter=null;
        try {
            bufferedReader = new BufferedReader(new FileReader("E:\\desc\\hk_ws.zip"));
            bufferedWriter  = new BufferedWriter(new FileWriter("E:\\src\\hk_ws.zip"));
            String lin;
            while ((lin=bufferedReader.readLine())==null){
                bufferedWriter.write(lin);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
        } catch (Exception e) {
            throw  new RuntimeException("文件拷贝异常");
        }finally {
            try {
                if(bufferedReader!=null){
                    bufferedReader.close();
                }
                if(bufferedWriter!=null){
                    bufferedWriter.close();
                }
                long endsTime = System.currentTimeMillis();
                System.out.println(endsTime-startTime);
            } catch (IOException e) {
                throw  new RuntimeException("流关闭异常");
            }
        }
    }

    @Test
    public void readKeyAndValueByProperties(){
        FileReader fileReader =null;
        try {
            Properties properties = new Properties();
            fileReader= new FileReader("D:\\KeyAndValue.Properties");
            properties.load(fileReader);
            String key = properties.getProperty("key22");
            System.out.println(key);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if(fileReader!=null){
                    fileReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void readTextToString() {
        FileInputStream fis = null;
        String result = "";
        try {
            fis = new FileInputStream("");
            byte[] bytes = new byte[1024];
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
//        return result;
    }

    /**
     * 真的很方便
     */
    @Test
    public void operationBaseJson(){
        JSONObject baseJson = new JSONObject();
        JSONObject secondJson = new JSONObject();
        baseJson.put("name","曹海生");
        baseJson.put("age",22);
        baseJson.put("sex","男");
        secondJson.put("java001",baseJson);
        Map map = secondJson.toJavaObject(Map.class);
        System.out.println();
    }

    /**
     * 这个工具处理时间格式很强大。
     * 是我到目前为止见过最强大的工具类了。
     */
    @Test
    public void operationJson(){
        Person person = new Person();
        person.setBirthDate(new Date());
        person.setHeight("175");
        JSONObject o = (JSONObject)JSON.toJSON(person);
//      有此以后就不用频繁改bean了。
        o.put("mather","moon");
        System.out.println(o);

        String s1 = new String("[{\"address\":\"上海杨浦\",\"age\":25,\"birthDate\":\"1993-08-05 10:12:11\",\"height\":\"185cm\",\"name\":\"张三\"},{\"address\":\"上海松江\",\"age\":26,\"birthDate\":\"1993-06-05 10:12:11\",\"height\":\"180cm\",\"name\":\"李四\"}]");
        List<Person> descPersonList = JSON.parseArray(s1, Person.class);
        Long birthDate = descPersonList.get(0).getBirthDate().getTime();
        System.out.println(birthDate);
    }
}
