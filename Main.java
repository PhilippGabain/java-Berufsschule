package org.example;

/*
    A7.2.2
 */

public class Main {
    public static void main(String[] args) {
        // 1 und 2
        int[] ganzeZahlen = new int[100];
        // 3
        System.out.println("3. Aufgabe");
        printArr(ganzeZahlen);
        // 4
        for(int i = 0; i < ganzeZahlen.length; i++){
            ganzeZahlen[i] = i+1;
        }
        // 5
        System.out.println("5. Aufgabe");
        System.out.println(ganzeZahlen[89-1]);
        // 6
        ganzeZahlen[49] = 1060;
        ganzeZahlen[0] = 2023;
        ganzeZahlen[ganzeZahlen.length-1] = 2023;
        // 7
        printArr(ganzeZahlen);
        // 8
        double avg = durchschnitt(ganzeZahlen);
        System.out.println(avg + " ");
    }
    public static double durchschnitt(int[] arr){
        double avg = 0;
        for(int i = 0; i < arr.length; i++){
            avg += arr[i];
        }
        avg /= arr.length;
        return avg;
    }
    public static void printArr(int[] arr){
        for(int i = 0; i < arr.length; i++){
            System.out.print(arr[i] + " ");
            if(i % 10 == 0){
                System.out.println();
            }
        }
        System.out.println();
    }
}