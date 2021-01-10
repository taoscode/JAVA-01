package com.geektime;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;

public class HelloClassLoader extends ClassLoader{
    public static void main(String[] args) {
        try {
            Class clazz = new HelloClassLoader().findClass("Hello");
            Method method = clazz.getMethod("hello");
            method.invoke(clazz.newInstance());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {

        File file = new File("D:\\geek\\week1-01\\HelloClassLoader1\\src\\main\\java\\Hello.xlass");
        byte [] byteCodes = null;
        try {
            byteCodes = Files.readAllBytes(file.toPath());
            for (int i=0;i<byteCodes.length;i++){
                byteCodes[i] = (byte) (255-byteCodes[i]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return defineClass(name,byteCodes,0,byteCodes.length);
    }
}
