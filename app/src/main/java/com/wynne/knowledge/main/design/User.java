package com.wynne.knowledge.main.design;

public class User implements Cloneable {
    int age;
    String name;
    String phoneNum;
    Address address;

    @Override
    public String toString() {
        return "User{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", address=" + address +
                '}';
    }

    @Override
    protected User clone() {
        User user = null;
        try {
            user = (User) super.clone();
            return user;
        } catch (Exception e) {

        }
        return null;
    }
}
