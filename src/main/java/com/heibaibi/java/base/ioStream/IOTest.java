package com.heibaibi.java.base.ioStream;

import org.junit.Test;

import java.io.File;

public class IOTest {

    @Test
    public void filterJavaFile(){
        File file = new File("E:\\资料库");
        getAllDir(file);
    }



    /**
     * 必定有个判断，用来结束循环.
     * 业务处理在判断之前就已经完成了。
     * 注意业务逻辑的完整性。
     *
     * 一项逻辑递归就只有一个固定返回值，两项递归就有固定两个返回值。
     * 固定返回值通常使用if/switch。
     * @param file
     */
    private void getAllDir(File file){
        File[] files = file.listFiles();
        for (File tempFile:files){
            if(tempFile.isDirectory()){
                getAllDir(tempFile);
            }else {
//                不想判断文件夹，因此过滤才设置在此。
                if(new JavaFilter().accept(tempFile)){
                    System.out.println(tempFile);
                }
            }
        }
    }
}
