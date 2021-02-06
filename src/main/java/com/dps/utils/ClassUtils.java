package com.dps.utils;

import java.io.File;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ClassUtils {

    /**
     * 获取某个包下的所有类全路径名
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
        // jar https://blog.csdn.net/yangguosb/article/details/80761883
        if (url!=null){
            String protocol=url.getProtocol();
            if (protocol.equals("file")){
                classNames=getClassNameFromDir(url.getPath(),packageName,isRecursion);
            }else if (protocol.equals("jar")){
                // Jar本身,需要获取Jar里面的JarEntry
                JarFile jarFile=null;
                try {
                    jarFile=((JarURLConnection)url.openConnection()).getJarFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (jarFile != null) {
                    classNames = getClassNameFromJar(jarFile.entries(), packageName, isRecursion);
                }
            }
        }else{
            classNames=getClassNameFromJars(((URLClassLoader)loader).getURLs(), packageName, isRecursion);
        }
        return classNames;
    }


    /**
     * 获取某目录路径下的所有类全路径名称
     * @param filePath 目录路径
     * @param packageName 包名 （包名+'.'+类名=类的全路径名称）
     * @param isRecursion 是否递归获取
     */
    private static Set<String> getClassNameFromDir(String filePath,String packageName,boolean isRecursion){
        Set<String> classNames=new HashSet<>();
        File file=new File(filePath);
        File[] listFiles=file.listFiles();
        if (listFiles!=null && listFiles.length>0) {
            for (File childFile : listFiles) {
                if (childFile.isDirectory()){
                    if (isRecursion){
                        classNames.addAll(getClassNameFromDir(childFile.getPath(),packageName+"."+childFile.getName(),isRecursion));
                    }
                }else{
                    String fileName=childFile.getName();
                    if (fileName.endsWith(".class") && !fileName.contains("$")) {
                        classNames.add(packageName + "." + fileName.replace(".class", ""));
                    }
                }
            }
        }
        return classNames;
    }

    /**
     *
     * @param jarEntries jar文件列表
     * @param packageName 包名
     * @param isRecursion 是否递归获取
     */
    private static Set<String> getClassNameFromJar(Enumeration<JarEntry> jarEntries,String packageName,boolean isRecursion){
        Set<String> classNames=new HashSet<>();
        while (jarEntries.hasMoreElements()){
            JarEntry jarEntry=jarEntries.nextElement();
            if (!jarEntry.isDirectory()){
                String entryName=jarEntry.getName().replace("/",".");
                if (!entryName.endsWith(".class") || entryName.contains("$") || !entryName.startsWith(packageName)) {
                    continue;
                }
                entryName=entryName.replace(".class","");
                if (!isRecursion) {
                    if (entryName.replace(packageName + ".", "").contains(".")) {
                        continue;
                    }
                }
                classNames.add(entryName);
            }
        }
        return classNames;
    }

    private static Set<String> getClassNameFromJars(URL[] urls,String packageName, boolean isRecursion){
        Set<String> classNames=new HashSet<>();
        for (URL url : urls) {
            String classPath = url.getPath();
            if (!classPath.endsWith("classes/")) {
                JarFile jarFile = null;
                try {
                    jarFile = new JarFile(classPath.substring(classPath.indexOf("/")));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (jarFile != null) {
                    classNames.addAll(getClassNameFromJar(jarFile.entries(), packageName, isRecursion));
                }
            }
        }
        return classNames;
    }


}
