package com.dps.utils;

import sun.java2d.pipe.SpanShapeRenderer;

import java.net.URL;
import java.util.Set;

public class ClassUtils {

    /**
     * 获取某个包下的所有类名
     * @param packageName
     * @param isRecursion
     */
    public static Set<String> getClassName(String packageName, boolean isRecursion) {
        // 1.获取类加载器,因为这是本程序中,使用当前线程类加载器ClassLoader
        Set<String> classNames=null;
        ClassLoader loader=Thread.currentThread().getContextClassLoader();

        String packagePath=packageName.replace(".","/");
        URL url=loader.getResource(packagePath);
        // 2. URL的protocol
        // protocol(协议)可以是 HTTP、HTTPS、FTP 和 File
        if (url!=null){
            String protocol=url.getProtocol();
        }


        return null;
    }

}
