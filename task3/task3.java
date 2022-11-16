package task3;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class task3 {
    public static void main(String[] args) {
        System.out.println("1 task:");
        System.out.println(solutions(1,0,-1));
        System.out.println(solutions(1,0,0));
        System.out.println(solutions(1,0,1));
        System.out.println(" ");
        System.out.println("2 task:");
        System.out.println(findZip("all zip files are zipped"));
        System.out.println(findZip("all zip files are compressed"));
        System.out.println(" ");
        System.out.println("3 task:");
        System.out.println(checkPerfect(6));
        System.out.println(checkPerfect(28));
        System.out.println(checkPerfect(496));
        System.out.println(checkPerfect(12));
        System.out.println(checkPerfect(97));
        System.out.println(" ");
        System.out.println("4 task:");
        System.out.println(flipEndChars("Cat, dog, and mouse."));
        System.out.println(flipEndChars("ada"));
        System.out.println(flipEndChars("Ada"));
        System.out.println(flipEndChars("z"));
        System.out.println(" ");
        System.out.println("5 task:");
        System.out.println(isValidHexCode("#CD5C5C"));
        System.out.println(isValidHexCode("#EAECEE"));
        System.out.println(isValidHexCode("#eaecee"));
        System.out.println(isValidHexCode("#CD5C58C"));
        System.out.println(isValidHexCode("#CD5C5Z"));
        System.out.println(isValidHexCode("#CD5C&C"));
        System.out.println(isValidHexCode("CD5C5C"));
        System.out.println(" ");
        System.out.println("6 task:");
        System.out.println(same(new int[]{1, 3, 4, 4, 4}, new int[]{2, 5, 7}));
        System.out.println(same(new int[]{9, 8, 7, 6}, new int[]{4, 4, 3, 1}));
        System.out.println(same(new int[]{2}, new int[]{3, 3, 3, 3, 3}));
        System.out.println(" ");
        System.out.println("7 task:");
        System.out.println(isKaprekar(3));
        System.out.println(isKaprekar(5));
        System.out.println(isKaprekar(297));
        System.out.println(" ");
        System.out.println("8 task:");
        System.out.println(longestZero("01100001011000"));
        System.out.println(longestZero("100100100"));
        System.out.println(longestZero("11111"));
        System.out.println(" ");
        System.out.println("9 task:");
        System.out.println(nextPrime(12));
        System.out.println(nextPrime(24));
        System.out.println(nextPrime(11));
        System.out.println(" ");
        System.out.println("10 task:");
        System.out.println(rightTriangle(3,4,5));
        System.out.println(rightTriangle(145,105,100));
        System.out.println(rightTriangle(70,130,110));
    }

    public static int solutions(int a, int b, int c){
        int d  = b * b - 4*a*c;
        if (d>0){
            return 2;
        }
        else if (d==0){
            return 1;
        }
        else {
            return 0;
        }
    }

    public static int findZip(String s){
        if (s.length() == 0){
            return -1;
        }
        char[] symbols = s.toCharArray();
        int count = 0;
        for (int i = 0; i < symbols.length; i++) {
            if (symbols[i] == 'z' && symbols[i+1] == 'i' && symbols[i+2] == 'p'){
                count += 1;
                if (count == 2) {
                    return i;
                }
                i += 2;
            }
        }
        return -1;
    }

    public static boolean checkPerfect(int number){
        int sum = 0;
        for (int i = 1; i < number; i++) {
            if(number % i == 0){
                sum += i;
            }
        }
        return sum == number;
    }

    public static String flipEndChars(String s){
        char[] chars = s.toCharArray();
        if (s.length()<2){
            return "несовместимо";
        }
        else if (chars[0]==chars[chars.length-1]){
            return "два-это пара.";
        }
        else {
            char temp = chars[0];
            chars[0] = chars[chars.length-1];
            chars[chars.length-1]=temp;
            return new String(chars);
        }
    }

    public static boolean isValidHexCode(String s){
        char[] chars = s.toCharArray();
        if (chars.length !=7){
            return false;
        }
        Pattern pattern = Pattern.compile("#[0123456789ABCDEFabcdef]{6}");
        Matcher matcher = pattern.matcher(s);
        return matcher.matches();
    }

    public static boolean same(int[] firstArray, int[] secondArray){
        Set<Integer> firstSet = new HashSet<>();
        Set<Integer> secondSet = new HashSet<>();
        for(int el: firstArray){
            firstSet.add(el);
        }
        for(int el: secondArray){
            secondSet.add(el);
        }
        return firstSet.size() == secondSet.size();
    }

    public static boolean isKaprekar(int n){
        if (n ==0 || n == 1){
            return true;
        }
        if (n / 10 == 0){
            return false;
        }
        String s = Integer.toString(n * n);
        String leftS = s.substring(0,s.length()/2);
        String rightS = s.substring(s.length()/2);
        return (Integer.parseInt(leftS)+Integer.parseInt(rightS)) == n;
    }

    public static String longestZero(String s){
        char[] chars = s.toCharArray();
        StringBuilder builder = new StringBuilder();
        int max = 0;
        int count = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '0') {
                count += 1;
            } else {
                count = 0;
                continue;
            }
            if (count > max) {
                max = count;
            }
        }
        for (int i = 0; i < max; i++) {
            builder.append('0');
        }
        return builder.toString();
    }

    public static int nextPrime(int n){
        if (isPrime(n)) {
            return n;
        } else {
            int next = n + 1;
            while (!isPrime(next)) {
                next += 1;
            }
            return next;
        }
    }

    public static boolean isPrime(int n) {
        for (int i = 2; i < n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }


    public static boolean rightTriangle(int x, int y, int z) {
        if (x > y && x > z) {
            return x * x == z*z+y*y;
        } else if (y > x && y > z){
            return y * y == x*x+z*z;
        } else {
            return z * z == x*x+y*y;
        }
    }
}