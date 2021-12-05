package com.example.Task1.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Numbers {
    public static ArrayList<Integer> getNumbers() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("D:\\Task1\\src\\main\\resources\\input.txt"));
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
