package com.heibaibi.java.base.reflection;

import com.heibaibi.java.base.bean.Person;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;

public class Reflection {

    /**
     * 此方法测试能不能拿到类对象
     */
    @Test
    public void methodOfGetClass() {
//        通过对象的getClass()方法获取。
        Person person = new Person();
        Class personClass = person.getClass();
        System.out.println(personClass);
// 通过类名来获取对象。
        Class classByName = Person.class;
        System.out.println(classByName);
//        通过反射获取字节码文件对象。
        Class classByforName = null;
        try {
            classByforName = Class.forName("com.heibaibi.java.base.bean.Person");
        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
            System.out.println("反射模块找不到Person类");
        }
        System.out.println(classByforName);
    }

    /**
     * 获取空参构造方法，然后生成对象。
     */
    @Test
    public void methodOfGetConstructorsNoParameter() {
//  使用无参构造方法。
        Class classByforName = null;
        try {
            classByforName = Class.forName("com.heibaibi.java.base.bean.Person");
        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
            System.out.println("反射模块找不到Person类");
        }
//        获取全部的public构造方法。
        Arrays.asList(classByforName.getConstructors()).forEach(System.out::println);
//        获取无参构造方法。
        Constructor constructor = null;
        try {
            constructor = classByforName.getConstructor();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        System.out.println(constructor);

//根据反射获取的空构造方法，获取对象。
        Object classFrom = null;
        try {
            classFrom = constructor.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(classFrom);
    }

    /**
     * 使用反射获取有参构造方法，然后使用。
     */
    @Test
    public void methodOfGetConstructorsHaveParameter() {
        //  使用无参构造方法。
        Class classByforName = null;
        try {
            classByforName = Class.forName("com.heibaibi.java.base.bean.Person");
        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
            System.out.println("反射模块找不到Person类");
        }
//      获取有参构造方法。参数写包装类类型。
        Constructor constructor = null;
        try {
            constructor = classByforName.getConstructor(String.class, Integer.class, String.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        Person classHaveParameter = null;
        try {
            classHaveParameter = (Person) constructor.newInstance("张三", 25, "中国上海");
        } catch (Exception e) {
//            e.printStackTrace();
            System.out.println("通过返回获取有参构造方法失败");
        }
        System.out.println(classHaveParameter);
    }

    /**
     * 通过反射获取私有构造方法。
     * 破坏了程序的安全性，不推荐使用。
     */
    @Test
    public void mothodOfGetPrivateConstructors() {
//        获取class
        Class classByforName = null;
        try {
            classByforName = Class.forName("com.heibaibi.java.base.bean.Person");
        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
            System.out.println("反射模块找不到Person类");
        }
//获取全部构造方法，我是没看出来这个有啥优势。
        Constructor[] declaredConstructors = classByforName.getDeclaredConstructors();
        for (Constructor constructor : declaredConstructors) {
            System.out.println(declaredConstructors);
        }
//        获取指定的私有构造方法。
        Constructor privateConstructor = null;
        try {
            privateConstructor = classByforName.getDeclaredConstructor(String.class, Integer.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
//        使用私有构造方法。
        privateConstructor.setAccessible(true);
        Person privateObject = null;
        try {
            privateObject = (Person) privateConstructor.newInstance("曹海神", 206);
        } catch (Exception e) {
//            e.printStackTrace();
            System.out.println("反射模块操作私有构造方法失败");
        }
        System.out.println(privateObject);
    }

    /**
     * 因为非静态成员属性和方法是属于对象
     * 因此使用反射操作属性和方法要先获取对象。
     */
    @Test
    public void methodOfGetFields() {
        Person basePerson = null;
        Class baseClass = null;
        try {
            baseClass = Class.forName("com.heibaibi.java.base.bean.Person");
            basePerson = (Person) baseClass.newInstance();
        } catch (Exception e) {
//            e.printStackTrace();
            System.out.println("反射模块获取对象失败");
        }
//获取所有属性。
//      Field[] fields = baseClass.getFields();
        Field[] declaredFields = baseClass.getDeclaredFields();
        for (Field field : declaredFields) {
            System.out.println(field);
        }
//        通过名称获取指定属性。
        Field nameField = null;
        Field privateHeightField = null;
        try {
            nameField = baseClass.getField("name");
            privateHeightField = baseClass.getDeclaredField("height");
        } catch (NoSuchFieldException e) {
//            e.printStackTrace();
            System.out.println("反射模块获取属性错误");
        }
        System.out.println(nameField);

//        操作属性。
        try {
            nameField.set(basePerson, "曹海神");
//操作私有变量
            privateHeightField.setAccessible(true);
            privateHeightField.set(basePerson, "172");
        } catch (IllegalAccessException e) {
//            e.printStackTrace();
            System.out.println("反射基本模块操作反射获取属性失败");
        }
        System.out.println(basePerson);
    }

    /**
     * 通过反射获取方法
     */
    @Test
    public void methodOfGetMethods() {
        Class baseClass = null ;
        Person basePerson = null;
        try {
            baseClass= Class.forName("com.heibaibi.java.base.bean.Person");
            basePerson = (Person)baseClass.newInstance();
        } catch (Exception e) {
//            e.printStackTrace();
            System.out.println("反射模块通过反射获取对象失败");
        }

//获取全部的方法，包括继承来的方法。
        Method[] methods = baseClass.getMethods();
        Method[] declaredMethods = baseClass.getDeclaredMethods();
        for(Method method:methods){
            System.out.println(method);
        }

//        获取方法
        Method methodOfHaveParameterNoResult=null;
        Method methodOfHaveParameterHaveResult=null;
        try {
            methodOfHaveParameterNoResult = baseClass.getMethod("methodOfHaveParameterNoResult", Integer.class);
            methodOfHaveParameterHaveResult = baseClass.getDeclaredMethod("methodOfHaveParameterHaveResult", String.class);
        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
        }

        try {
            methodOfHaveParameterNoResult.invoke(basePerson,206);

//          操作私有方法，若不设置accesssible会报错。
            methodOfHaveParameterHaveResult.setAccessible(true);
            methodOfHaveParameterHaveResult.invoke(basePerson,"菜还是");
        } catch (Exception e) {
//            e.printStackTrace();
            System.out.println("反射模块操作方法失败");
        }
    }

    /**
     * 反射之陈咬金功用
     */
    @Test
    public  void methodOfCYJ(){
        ArrayList<Integer> stringList = new ArrayList<Integer>();
        stringList.add(1);

        Class listClass = stringList.getClass();
        Method listAdd=null;
        try {
            listAdd = listClass.getMethod("add", Object.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        try {
            listAdd.invoke(stringList,"第二个元素");
            listAdd.invoke(stringList,"第三个元素");
            listAdd.invoke(stringList,"第四个元素");
            listAdd.invoke(stringList,"第五个元素");
        } catch (Exception e) {
//            e.printStackTrace();
            System.out.println("反射模块半路杀出陈咬金失败");
        }
        System.out.println(stringList);
//      [1, 第二个元素, 第三个元素, 第四个元素, 第五个元素]
    }

    /**
     * 通过反射控制程序执行。
     */
    @Test
    public void  methodOfControl(){
        String className=null;
        String methodName=null;
        try {
            FileReader fileReader = new FileReader("src/main/resources/reflection.properties");
            Properties properties = new Properties();
            properties.load(fileReader);
            fileReader.close();
            className=properties.getProperty("className");
            methodName=properties.getProperty("methodName");
        } catch (Exception e) {
//            e.printStackTrace();
            System.out.println("获取要执行类名和方法名失败");
        }
        Object baseObject=null;
        Method baseMethod=null;
        try {
            Class  baseClass = Class.forName(className);
            baseObject = baseClass.newInstance();
            baseMethod = baseClass.getMethod(methodName);
        } catch (Exception e) {
//            e.printStackTrace();
            System.out.println("获取对象和方法失败");
        }

        try {
            baseMethod.invoke(baseObject);
        } catch (Exception e) {
//            e.printStackTrace();
            System.out.println("运行失败");
        }
    }
}
