/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lista.pkg10;

/**
 *
 * @author jacek
 */


import java.io.*;
import java.util.*;


public class Lista10 {

    public static boolean isPrime (int number) {
        if (number % 2 == 0) {
            return number == 2;
        }

        int limit = (int)(1 + Math.sqrt(number));
        for (int i = 3; i <= limit; i += 2) {
            if (number % i == 0) {
                return false;
            }
        }

        return true;
    }


    public static void main(String[] args) {

        System.out.println("Part1:");
        boolean task = true;
        List<Integer> list1 = new ArrayList<Integer>();
        try (BufferedReader br = new BufferedReader(new FileReader("dane1.txt"))) {
            for (String ln = br.readLine(); ln != null; ln = br.readLine()) {
                if (!ln.matches("\\s*(\\d{1,9}\\s*)?(//.*)?")) {
                    System.out.println(ln);
                    task = false;
                    break;
                }

                else {
                    String temp = ln.replaceAll("(//.*)|(\\s*)", "");
                    if (!temp.equals(""))
                        list1.add(Integer.parseInt(temp));
                }
            }

            if (task) {
                System.out.println("\nAll numbers:");
                list1.stream()
                    .forEachOrdered(e -> System.out.println(e));

                System.out.println("\nPrime:");
                list1.stream()
                    .filter(Lista10::isPrime)
                    .forEachOrdered(e -> System.out.println(e));

                System.out.println("\nSorted:");
                list1.stream()
                    .sorted()
                    .forEachOrdered(e -> System.out.println(e));

                System.out.println("\nSum:");
                System.out.println(
                    list1.stream()
                        .filter(e -> e < 1000)
                        .mapToInt(Integer::intValue)
                        .sum()
                );
            }
            else
                System.out.println("Unregistered pattern.");
        }
        catch(IOException e){ 
            e.printStackTrace(); 
        }

        System.out.println("\nPart2:");


        List<Triangle> list2 = new ArrayList<Triangle>();

        try (BufferedReader br = new BufferedReader(new FileReader("dane2.txt"))) {
            for (String ln = br.readLine(); ln != null; ln = br.readLine()) {
                if (!ln.matches("\\s*([1-9]\\d*(\\.\\d+)?\\s*){3}?(//.*)?")) {
                    task = false;
                    break;
                }

                else {
                    String[] temp = ln.replaceAll("//.*", "").split("\\s+");

                    double[] arg = Arrays.asList(temp)
                        .stream()
                        .filter(e -> !e.matches("\\s*"))
                        .mapToDouble(Double::parseDouble)
                        .toArray();

                    Triangle t = new Triangle (arg[0], arg[1], arg[2]);
                    list2.add(t);
                }
            }

            if (task) {
                System.out.println("\nAll triangles:");
                list2.stream()
                    .forEachOrdered(e -> System.out.println(e));

                System.out.println("\nSorted by circumference:");
                list2.stream()
                    .sorted((x, y) -> Double.compare(Triangle.circumference(x), Triangle.circumference(y)))
                    .forEachOrdered(e -> System.out.println(e));

                System.out.println("\nRight triangles:");
                list2.stream()
                    .filter(e -> Triangle.right(e))
                    .forEachOrdered(e -> System.out.println(e));

                System.out.println("\nMinimum area:");
                list2.stream()
                    .sorted((x, y) -> Double.compare(Triangle.area(x), Triangle.area(y)))
                    .limit(1)
                    .forEachOrdered(e -> System.out.println(e));


                System.out.println("\nMaximum area:");
                list2.stream()
                    .sorted((x, y) -> Double.compare(Triangle.area(y), Triangle.area(x)))
                    .limit(1)
                    .forEachOrdered(e -> System.out.println(e));
            }
            else
                System.out.println("Unregistered pattern.");
        }
        catch (IOException e){ 
            e.printStackTrace(); 
        }

    }

}