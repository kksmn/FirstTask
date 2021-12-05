package com.example.Task1.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Numbers {
    public static ArrayList<Integer> getNumbers() throws FileNotFoundException {
        String path="D:\\Task1\\src\\main\\resources\\input.txt";
        Scanner scanner = new Scanner(new File(path));
        ArrayList<Integer> list=new ArrayList<>();
        while(scanner.hasNextInt()){
            list.add(scanner.nextInt());
        }
        return list;
    }
    public static ArrayList<Integer> getSortedNumbers() throws FileNotFoundException {
        ArrayList<Integer> list;
        list=getNumbers();
        Collections.sort(list);
        return list;
    }


}
