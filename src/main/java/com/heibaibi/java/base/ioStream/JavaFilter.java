package com.heibaibi.java.base.ioStream;

import java.io.File;
import java.io.FileFilter;

public class JavaFilter implements FileFilter {
    /**
     * 过滤掉不符合规范
     * @param pathname
     * @return
     */

    @Override
    public boolean accept(File pathname) {
        return pathname.getName().endsWith(".jpg");
    }


}
