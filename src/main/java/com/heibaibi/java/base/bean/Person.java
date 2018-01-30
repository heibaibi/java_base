package com.heibaibi.java.base.bean;

public class Person {
    public String name;
    public Integer age;
    public String address;

    private String height;
    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Person() {
        System.out.println("此是空參構造方法");
    }

    public Person(String name) {
        this.name = name;
        System.out.println("这是设置姓名的构造方法");
    }

    private Person(String name, Integer age) {
        this.name = name;
        this.age = age;
        System.out.println("这是设置姓名和年龄的私有构造方法");
    }

    public Person(String name, Integer age, String address) {
        this.name = name;
        this.age = age;
        this.address = address;
        System.out.println("这是设置姓名 年龄  和地址的构造方法");
    }

    public void methodOfNoParameterAndResult(){
        System.out.println("这是没有返回值和参数的构造方法");
    }

    public  void methodOfHaveParameterNoResult(Integer age){
        System.out.println("传入的参数为"+age);
    }

    public  Integer methodOfNoParameterHaveResult(){
        System.out.println("这是有返回值没有参数的构造方法");
        return  77;
    }

    private  String methodOfHaveParameterHaveResult(String name){
        System.out.println("传入的姓名为"+name);
        return  name;
    }

    public void getMethodOfPrivateNoParameterNoResult(){
        System.out.println("这是没有参数没有返回值的私有构造方法");
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                ", height='" + height + '\'' +
                '}';
    }
}
