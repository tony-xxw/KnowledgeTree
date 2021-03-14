package com.wynne.java.main;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;

class CollectionSample {
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void main(String[] args) {

//        practiceList();
//        practiceSet();
        practiceMap();

//        baseJava();

    }

    private static void baseJava() {
        String a = "A";
        String a1 = "a";

        System.out.println("intern " + a.intern());

        System.out.println(a.equalsIgnoreCase(a1));
        System.out.println(a.compareTo(a1));


        String join = String.join("-", "i", "want", "ali");
        System.out.println(join);

        List<String> strings = Arrays.asList("I", "Want", "Freedom");
        String join1 = String.join(",", strings);
        System.out.println(join1);
    }

    private static void practiceMap() {
        HashMap<String, Integer> hashMap = new HashMap<>();
        hashMap.put("1", 1);
        hashMap.put("1", 2);
        hashMap.put("2", 2);
        hashMap.put("3", 3);
        hashMap.put(null, 3);
        hashMap.put("4", null);
        hashMap.put("5", null);
        System.out.println("hashMap " + hashMap.toString());

        System.out.println("get " + hashMap.get("1"));

    }

    private static void practiceSet() {
        HashSet<String> hashSet = new HashSet<>();
        hashSet.add("1");
        hashSet.add("4");
        hashSet.add("2");
        hashSet.add("5");
        hashSet.add("3");
        hashSet.add(null);
        hashSet.add(null);
        System.out.println("HashSet " + hashSet.toString());

        TreeSet<String> treeSet = new TreeSet<>();
        treeSet.add("1");
        treeSet.add("4");
        treeSet.add("2");
        treeSet.add("5");
        treeSet.add("3");
        System.out.println("treeSet " + treeSet.toString());
    }

    private static void practiceList() {
        // 可重复,有序,为空
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("1");
        arrayList.add("1");
        arrayList.add("2");
        arrayList.add("3");
        arrayList.add(null);

        System.out.println("arrayList " + arrayList.toString());

    }
}
