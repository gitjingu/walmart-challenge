package com.company;
import java.nio.Buffer;
import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        BufferedReader in;
        if(args.length>0)
        {
            File file = new File(args[0]);
            in = new BufferedReader(new FileReader(file));
        }
        else {
            System.out.println("File input not found");
            in = new BufferedReader(new InputStreamReader(System.in));
        }
        try {
            System.out.println(in.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
